package dev.enemeez.simplefarming.fabric.mixin.client;

import dev.enemeez.simplefarming.fabric.client.SimpleFarmingCreativeTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Provides the extra creative-inventory page and its arrows on Fabric. See
 * {@link SimpleFarmingCreativeTabs} for why paging is needed at all.
 *
 * <p>The tab list itself is filtered by {@code CreativeModeTabsMixin}; this mixin only adds the
 * buttons that switch pages and keeps the selected tab on the page being shown.</p>
 */
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeTabPagingMixin extends Screen {

    @Shadow
    private static CreativeModeTab selectedTab;

    @Shadow
    protected abstract void selectTab(CreativeModeTab tab);

    protected CreativeTabPagingMixin() {
        super(Component.empty());
    }

    // The creative screen's fixed background size (AbstractContainerScreen defaults are overridden
    // in the constructor). leftPos/topPos live on AbstractContainerScreen and cannot be shadowed
    // from this mixin, so the same top-left corner is derived from the screen size.
    private static final int CREATIVE_IMAGE_WIDTH = 195;
    private static final int CREATIVE_IMAGE_HEIGHT = 136;

    @Inject(method = "init", at = @At("TAIL"))
    private void simplefarming$addPagingButtons(CallbackInfo ci) {
        if (SimpleFarmingCreativeTabs.totalPages() <= 1) {
            return;
        }

        // Keep the selected tab on the page being shown; vanilla reselects whichever tab was last
        // open, which may belong to the other page.
        if (!SimpleFarmingCreativeTabs.isVisible(selectedTab)) {
            CreativeModeTab fallback = SimpleFarmingCreativeTabs.defaultTabForCurrentPage();
            if (fallback != null) {
                selectTab(fallback);
            }
        }

        // Matches Fabric API's placement: just below the search box, left of the scrollbar.
        int x = (this.width - CREATIVE_IMAGE_WIDTH) / 2 + 171;
        int y = (this.height - CREATIVE_IMAGE_HEIGHT) / 2 + 4;
        this.addRenderableWidget(this.simplefarming$pageButton(x, y, "<", -1));
        this.addRenderableWidget(this.simplefarming$pageButton(x + 18, y, ">", 1));
    }

    private Button simplefarming$pageButton(int x, int y, String label, int direction) {
        Button button = Button.builder(Component.literal(label), b -> this.simplefarming$switchPage(direction))
            .bounds(x, y, 16, 14)
            .build();
        button.active = direction < 0
            ? SimpleFarmingCreativeTabs.currentPage() > 0
            : SimpleFarmingCreativeTabs.currentPage() < SimpleFarmingCreativeTabs.totalPages() - 1;
        button.setTooltip(Tooltip.create(Component.translatable("gui.simplefarming.creative_page")));
        return button;
    }

    private void simplefarming$switchPage(int direction) {
        SimpleFarmingCreativeTabs.switchPage(direction);
        Minecraft minecraft = this.minecraft;
        if (minecraft == null || minecraft.player == null) {
            return;
        }
        minecraft.setScreen(new CreativeModeInventoryScreen(
            minecraft.player,
            minecraft.player.connection.enabledFeatures(),
            minecraft.options.operatorItemsTab().get()
        ));
    }
}
