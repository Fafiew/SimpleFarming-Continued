package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.fabric.world.ModPackSource;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds the mod's data pack to every data-pack repository the game builds.
 *
 * <p>Fabric Loader does not mount a mod's {@code data/} on its own and this mod intentionally avoids
 * Fabric API, so it registers its own source here. A repository holding {@link ServerPacksSource}
 * is a data-pack repository (the dedicated server, the in-memory test server and the client's
 * integrated server all use one), so it receives the mod's data. The client's own resource-pack
 * repository is handled separately by the client mixin.</p>
 */
@Mixin(PackRepository.class)
public abstract class PackRepositoryMixin {

    @Shadow
    @Final
    @Mutable
    private Set<RepositorySource> sources;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void simplefarming$addModPackSources(RepositorySource[] providers, CallbackInfo ci) {
        boolean data = false;
        for (RepositorySource source : providers) {
            if (source instanceof ServerPacksSource) {
                data = true;
                break;
            }
        }
        if (!data) {
            return;
        }

        Set<RepositorySource> extended = new HashSet<>(this.sources);
        extended.add(new ModPackSource(PackType.SERVER_DATA));
        this.sources = extended;
    }
}
