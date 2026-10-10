package dev.enemeez.simplefarming.forge;

import dev.enemeez.simplefarming.client.ClientColors;
import dev.enemeez.simplefarming.client.gui.FermenterScreen;
import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.common.registries.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-only mod-bus listeners.
 *
 * <p>These must live in a class flagged {@link Dist#CLIENT}: registering a colour-handler listener
 * loads the colour event types, which in turn reference client-only classes such as
 * {@code BlockColors}. Doing that from the mod constructor crashes a dedicated server, because Forge
 * strips client classes from the server distribution.</p>
 */
@Mod.EventBusSubscriber(modid = SimpleFarming.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ForgeClientEvents {

    private ForgeClientEvents() {
    }

    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenus.FERMENTER_MENU.get(), FermenterScreen::new));
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        ClientColors.registerBlockColors(event.getBlockColors());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ClientColors.registerItemColors(event.getItemColors());
    }
}
