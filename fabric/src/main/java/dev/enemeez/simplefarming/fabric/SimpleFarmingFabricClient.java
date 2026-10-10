package dev.enemeez.simplefarming.fabric;

import dev.enemeez.simplefarming.client.gui.FermenterScreen;
import dev.enemeez.simplefarming.common.registries.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

/**
 * Fabric client entrypoint. Registers the fermenter menu screen. Block and item colours are
 * registered later, from a mixin on the client constructor, because the client has not created its
 * colour registries at the point this entrypoint runs.
 */
@Environment(EnvType.CLIENT)
public class SimpleFarmingFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricRenderLayers.registerCutoutBlocks();
        MenuScreens.register(ModMenus.FERMENTER_MENU.get(), FermenterScreen::new);
    }
}

