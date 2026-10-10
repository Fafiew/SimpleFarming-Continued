package dev.enemeez.simplefarming.fabric.world;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Exposes the mod's own {@code data/} and {@code assets/} trees as a vanilla resource pack.
 *
 * <p>Fabric Loader starts a mod's code and mounts its classes, but it does <em>not</em> mount the
 * mod as a resource pack: that has always been the job of the Fabric API module
 * {@code fabric-resource-loader-v0}. There is no loader-level equivalent, so without it a mod's
 * recipes, loot tables, tags, world generation and textures never reach the game. Because this mod
 * deliberately ships without any additional installed API, it mounts its own resources instead.</p>
 *
 * <p>{@code pack.mcmeta} is served synthetically, like the Fabric resource loader does, so that a
 * single mod jar works as both a data pack and a resource pack for the current game version even
 * though the physical file describes the Forge formats.</p>
 */
public final class ModPackResources extends AbstractPackResources {

    private static final Gson GSON = new Gson();
    private static final String MOD_ID = "simplefarming";

    private final List<Path> roots;
    private final PackType type;
    private final Set<String> namespaces;

    public ModPackResources(PackLocationInfo location, PackType type) {
        super(location);
        this.type = type;
        ModContainer container = FabricLoader.getInstance()
            .getModContainer(MOD_ID)
            .orElseThrow(() -> new IllegalStateException("Simple Farming mod container is not present"));
        List<Path> found = new ArrayList<>();
        for (Path root : container.getRootPaths()) {
            if (Files.isDirectory(root)) {
                found.add(root);
            }
        }
        this.roots = found;
        this.namespaces = discoverNamespaces(type);
    }

    @Override
    public IoSupplier<InputStream> getRootResource(String... pathSegments) {
        String name = String.join("/", pathSegments);
        if ("pack.mcmeta".equals(name)) {
            return () -> syntheticPackMeta();
        }
        Path path = find(name);
        return path == null ? null : IoSupplier.create(path);
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
        if (type != this.type) {
            return null;
        }
        Path path = find(type.getDirectory() + "/" + location.getNamespace() + "/" + location.getPath());
        return path == null ? null : IoSupplier.create(path);
    }

    @Override
    public void listResources(PackType type, String namespace, String path, ResourceOutput output) {
        if (type != this.type || !namespaces.contains(namespace)) {
            return;
        }
        for (Path root : roots) {
            Path namespaceDir = root.resolve(type.getDirectory()).resolve(namespace);
            Path directory = namespaceDir.resolve(path);
            if (!Files.isDirectory(directory)) {
                continue;
            }
            try (Stream<Path> files = Files.walk(directory)) {
                files.filter(Files::isRegularFile).forEach(file -> {
                    String relative = namespaceDir.relativize(file).toString().replace(file.getFileSystem().getSeparator(), "/");
                    ResourceLocation id = ResourceLocation.tryBuild(namespace, relative);
                    if (id != null) {
                        output.accept(id, IoSupplier.create(file));
                    }
                });
            } catch (IOException ignored) {
                // A missing directory only means this loader cannot list those resources.
            }
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return type == this.type ? namespaces : Collections.emptySet();
    }

    @Override
    public void close() {
    }

    private Path find(String relativePath) {
        for (Path root : roots) {
            Path candidate = root.resolve(relativePath).normalize();
            if (candidate.startsWith(root) && Files.isRegularFile(candidate)) {
                return candidate;
            }
        }
        return null;
    }

    private Set<String> discoverNamespaces(PackType type) {
        Set<String> found = new java.util.TreeSet<>();
        for (Path root : roots) {
            Path dir = root.resolve(type.getDirectory());
            if (!Files.isDirectory(dir)) {
                continue;
            }
            try (Stream<Path> entries = Files.list(dir)) {
                entries.filter(Files::isDirectory)
                    .map(entry -> entry.getFileName().toString().replace(entry.getFileSystem().getSeparator(), ""))
                    .forEach(found::add);
            } catch (IOException ignored) {
                // A missing directory only means this loader has no namespace to expose.
            }
        }
        return found;
    }

    private InputStream syntheticPackMeta() {
        JsonObject pack = new JsonObject();
        pack.addProperty("description", "Simple Farming: Continued");
        pack.addProperty("pack_format", SharedConstants.getCurrentVersion().getPackVersion(this.type));
        JsonObject root = new JsonObject();
        root.add("pack", pack);
        return new ByteArrayInputStream(GSON.toJson(root).getBytes(StandardCharsets.UTF_8));
    }
}
