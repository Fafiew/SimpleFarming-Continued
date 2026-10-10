package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final ModRegistry<CreativeModeTab> CREATIVE_TABS = new ModRegistry<>(Registries.CREATIVE_MODE_TAB);

    public static final Registration<CreativeModeTab> TAB = CREATIVE_TABS.register("tab", () -> CreativeModeTab
            .builder(CreativeModeTab.Row.TOP, freeTopColumn())
            .title(Component.translatable("item_group." + SimpleFarming.MOD_ID))
            .icon(() -> new ItemStack(ModItems.TOMATO.get()))
            .displayItems((parameters, output) -> SimpleFarming.acceptCreativeTab(output::accept))
            .build()
    );

    /** First top-row column past the vanilla tabs (building blocks through search). */
    private static final int FIRST_FREE_VANILLA_COLUMN = 7;

    /**
     * Vanilla's tab builder requires an explicit position and validates it against the tabs that
     * are already registered, so a fixed column would clash with a vanilla tab. Forge and NeoForge
     * expose a positionless builder that finds a free slot automatically; this reproduces that by
     * picking the first unoccupied top-row column.
     */
    private static int freeTopColumn() {
        try {
            boolean[] used = new boolean[16];
            int highest = -1;
            for (CreativeModeTab tab : BuiltInRegistries.CREATIVE_MODE_TAB) {
                if (tab.row() == CreativeModeTab.Row.TOP && tab.column() >= 0) {
                    if (tab.column() < used.length) {
                        used[tab.column()] = true;
                    }
                    highest = Math.max(highest, tab.column());
                }
            }
            for (int column = 0; column < used.length; column++) {
                if (!used[column]) {
                    return column;
                }
            }
            return highest + 1;
        } catch (IllegalStateException e) {
            // Fabric registers this tab while the built-in registries are still being created, so
            // the tab registry cannot be iterated yet. The vanilla tabs are already known, so fall
            // back to the column right after them.
            return FIRST_FREE_VANILLA_COLUMN;
        }
    }
}
