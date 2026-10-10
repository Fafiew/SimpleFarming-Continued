package dev.enemeez.simplefarming.fabric.mixin.client;

import dev.enemeez.simplefarming.fabric.world.ModPackSource;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;
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
 * Adds the mod's assets to the client's resource-pack repository.
 *
 * <p>This is the client half of the pair of pack-repository mixins. The client builds its own
 * resource-pack repository with a {@link ClientPackSource}, so that is what identifies it here.
 * Like the server side, Fabric Loader would not expose the mod's {@code assets/} without Fabric API,
 * so the mod mounts its own assets to keep models, textures, sounds and language files present.</p>
 */
@Mixin(PackRepository.class)
public abstract class ClientPackRepositoryMixin {

    @Shadow
    @Final
    @Mutable
    private Set<RepositorySource> sources;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void simplefarming$addModResourcePack(RepositorySource[] providers, CallbackInfo ci) {
        boolean client = false;
        for (RepositorySource source : providers) {
            if (source instanceof ClientPackSource) {
                client = true;
                break;
            }
        }
        if (!client) {
            return;
        }

        Set<RepositorySource> extended = new HashSet<>(this.sources);
        extended.add(new ModPackSource(PackType.CLIENT_RESOURCES));
        this.sources = extended;
    }
}
