package dev.enemeez.simplefarming.fabric.client;

import dev.enemeez.simplefarming.common.registries.ModCreativeTabs;
import dev.enemeez.simplefarming.fabric.mixin.client.CreativeModeTabAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import java.util.ArrayList;
import java.util.List;

/**
 * Client-side creative-inventory paging for Fabric.
 *
 * <p>1.21.1 fills all fourteen vanilla tab slots (two rows of seven), and vanilla provides no second
 * page, so a loader-supplied tab has nowhere to go. Forge and NeoForge paginate the tab list; on
 * Fabric this class reproduces just enough of that for the mod's own tab. It keeps a current page,
 * and the {@code CreativeModeTabs} mixin returns only the tabs belonging to that page. The mod's tab
 * is pinned to the first slot of page 1, keeping its plain model/library identity on Forge and
 * NeoForge unchanged.</p>
 */
public final class SimpleFarmingCreativeTabs {

    /** Page the mod's tab lives on. Vanilla tabs occupy page 0. */
    public static final int MOD_PAGE = 1;

    private static final int TOTAL_PAGES = 2;

    private static volatile CreativeModeTab modTab;
    private static int currentPage;

    private SimpleFarmingCreativeTabs() {
    }

    public static CreativeModeTab modTab() {
        CreativeModeTab tab = modTab;
        if (tab == null) {
            tab = ModCreativeTabs.TAB.get();
            if (tab != null) {
                // The registration column is computed from whatever mods are present and can land
                // outside the visible grid. On Fabric the tab owns a page of its own, so pin it to
                // the first slot there, matching the slot Forge and NeoForge would pick.
                CreativeModeTabAccessor accessor = (CreativeModeTabAccessor) tab;
                accessor.setRow(CreativeModeTab.Row.TOP);
                accessor.setColumn(0);
            }
            modTab = tab;
        }
        return tab;
    }

    public static int currentPage() {
        return currentPage;
    }

    public static int totalPages() {
        return TOTAL_PAGES;
    }

    private static int pageOf(CreativeModeTab tab) {
        return tab == modTab() ? MOD_PAGE : 0;
    }

    /** Replaces vanilla's tab list with the tabs belonging to the active page. */
    public static List<CreativeModeTab> decoratedTabs() {
        if (currentPage == MOD_PAGE) {
            CreativeModeTab tab = modTab();
            return tab != null && tab.shouldDisplay() ? List.of(tab) : List.of();
        }
        List<CreativeModeTab> vanilla = new ArrayList<>();
        for (CreativeModeTab tab : CreativeModeTabs.allTabs()) {
            if (tab.shouldDisplay() && isVanilla(tab)) {
                vanilla.add(tab);
            }
        }
        return vanilla;
    }

    /** Whether the tab belongs on the page currently being viewed. */
    public static boolean isVisible(CreativeModeTab tab) {
        return tab != null && tab.shouldDisplay() && pageOf(tab) == currentPage;
    }

    private static boolean isVanilla(CreativeModeTab tab) {
        ResourceLocation key = BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab);
        return key != null && key.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE);
    }

    /** The tab to select when the page changes and the previous selection is not on it. */
    public static CreativeModeTab defaultTabForCurrentPage() {
        if (currentPage == MOD_PAGE) {
            return modTab();
        }
        return CreativeModeTabs.getDefaultTab();
    }

    public static void setCurrentPage(int page) {
        if (page >= 0 && page < TOTAL_PAGES) {
            currentPage = page;
        }
    }

    public static void switchPage(int direction) {
        setCurrentPage(currentPage + direction);
    }
}
