package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.fabric.world.BiomeFeatureInjector;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Runs the code-based biome injection at the very end of the server constructor, once the dynamic
 * registries exist but before any level is created. This is the Fabric counterpart to the
 * {@code biome_modifier} data used on Forge and NeoForge.
 */
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "loadLevel", at = @At("HEAD"))
    private void simplefarming$injectBiomeFeatures(CallbackInfo ci) {
        BiomeFeatureInjector.inject(((MinecraftServer) (Object) this).registryAccess());
    }
}
