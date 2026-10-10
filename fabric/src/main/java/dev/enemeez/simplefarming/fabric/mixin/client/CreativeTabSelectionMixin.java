package dev.enemeez.simplefarming.fabric.mixin.client;

import dev.enemeez.simplefarming.fabric.client.SimpleFarmingCreativeTabs;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Prevents the creative inventory from selecting a tab that belongs to a page other than the one
 * currently shown on Fabric. This keeps the selected tab from pointing at an off-screen tab when
 * vanilla reselects one, for example while restoring state or refreshing invalidated tabs.
 */
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeTabSelectionMixin {

    @Inject(method = "selectTab", at = @At("HEAD"), cancellable = true)
    private void simplefarming$blockHiddenTab(CreativeModeTab tab, CallbackInfo ci) {
        if (!SimpleFarmingCreativeTabs.isVisible(tab)) {
            ci.cancel();
        }
    }
}
