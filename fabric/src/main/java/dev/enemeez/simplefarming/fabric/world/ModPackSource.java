package dev.enemeez.simplefarming.fabric.world;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Contributes the mod's resource pack to a pack repository for one pack type.
 *
 * <p>The pack is marked required, so it is always selected and cannot be turned off on a world; that
 * matches how Forge and NeoForge mount a mod's data. The id is namespaced so it can never clash with
 * another pack in the repository.</p>
 */
public final class ModPackSource implements RepositorySource {

    private static final PackSelectionConfig SELECTION = new PackSelectionConfig(true, Pack.Position.TOP, false);

    private final PackType type;

    public ModPackSource(PackType type) {
        this.type = type;
    }

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        PackLocationInfo location = new PackLocationInfo(
            "simplefarming_" + type.getDirectory(),
            Component.literal("Simple Farming: Continued"),
            PackSource.BUILT_IN,
            Optional.empty()
        );
        Pack pack = Pack.readMetaAndCreate(
            location,
            new ModPackSupplier(type),
            type,
            SELECTION
        );
        if (pack != null) {
            consumer.accept(pack);
        }
    }
}
