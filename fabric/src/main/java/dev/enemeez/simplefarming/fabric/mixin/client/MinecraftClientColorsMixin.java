package dev.enemeez.simplefarming.fabric.mixin.client;

import dev.enemeez.simplefarming.client.ClientColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Registers the mod's block and item colours once the client has built its colour registries.
 *
 * <p>The colours cannot be registered from the client entrypoint: that runs part-way through the
 * client's construction, before its block-colour registry exists and while its item-colour registry
 * is still null. Injecting at the end of the constructor is the point at which both registries are
 * populated, which is the same window the loader-specific colour events fire in.</p>
 */
@Mixin(Minecraft.class)
public abstract class MinecraftClientColorsMixin {

    @Shadow
    private BlockColors blockColors;

    @Shadow
    private ItemColors itemColors;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void simplefarming$registerColors(GameConfig config, CallbackInfo ci) {
        ClientColors.registerBlockColors(this.blockColors);
        ClientColors.registerItemColors(this.itemColors);
    }
}
