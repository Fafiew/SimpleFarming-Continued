package dev.enemeez.simplefarming.neoforge;

import dev.enemeez.simplefarming.client.ClientColors;
import dev.enemeez.simplefarming.client.gui.FermenterScreen;
import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.common.registries.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * Client-only mod-bus listeners.
 *
 * <p>Flagged {@link Dist#CLIENT} so NeoForge only loads it on the client. Registering these from
 * the mod constructor would load client-only classes such as {@code BlockColors} on a dedicated
 * server, where they do not exist.</p>
 */
@EventBusSubscriber(modid = SimpleFarming.MOD_ID, value = Dist.CLIENT)
public final class NeoForgeClientEvents {

    private NeoForgeClientEvents() {
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.FERMENTER_MENU.get(), FermenterScreen::new);
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
