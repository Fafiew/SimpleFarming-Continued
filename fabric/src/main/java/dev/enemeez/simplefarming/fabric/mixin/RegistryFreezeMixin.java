package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.fabric.FabricRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Registers the mod's built-in content while the block and item registries are still open.
 *
 * <p>Fabric Loader invokes the {@code main} entrypoint from inside {@code Bootstrap.bootStrap()},
 * immediately after {@code BuiltInRegistries.bootStrap()} has created and frozen the built-in
 * registries. Constructing a vanilla {@code Block} requires an intrusive holder, and a frozen
 * registry refuses to hand one out, so registering there fails with
 * "This registry can't create intrusive holders".</p>
 *
 * <p>This mixin runs the mod's registration right before the freeze instead, which is where Forge
 * and NeoForge perform it (through {@code DeferredRegister}). The entrypoint itself still runs so
 * the mod is reported as loaded, but its registration is idempotent and skips this hook.</p>
 */
@Mixin(BuiltInRegistries.class)
public class RegistryFreezeMixin {

    @Unique
    private static boolean simplefarming$registered = false;

    @Inject(method = "freeze", at = @At("HEAD"))
    private static void simplefarming$registerBeforeFreeze(CallbackInfo ci) {
        if (!simplefarming$registered) {
            simplefarming$registered = true;
            FabricRegistration.register();
        }
    }
}
