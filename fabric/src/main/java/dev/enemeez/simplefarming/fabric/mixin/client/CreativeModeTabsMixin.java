package dev.enemeez.simplefarming.fabric.mixin.client;

import dev.enemeez.simplefarming.fabric.client.SimpleFarmingCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Restricts {@code CreativeModeTabs.tabs()} to the active creative-inventory page on Fabric.
 *
 * <p>Vanilla uses this list to draw the tabs, hit-test their clicks, decide which item tooltips list
 * them and pick a fallback tab when the inventory closes. Returning only the current page's tabs
 * makes all of those behave as if the page were the whole inventory, without changing any tab's
 * registered position (so the shared code stays identical to Forge and NeoForge).</p>
 */
@Mixin(CreativeModeTabs.class)
public abstract class CreativeModeTabsMixin {

    @Inject(method = "tabs", at = @At("HEAD"), cancellable = true)
    private static void simplefarming$pageTabs(CallbackInfoReturnable<List<CreativeModeTab>> cir) {
        cir.setReturnValue(SimpleFarmingCreativeTabs.decoratedTabs());
    }
}
