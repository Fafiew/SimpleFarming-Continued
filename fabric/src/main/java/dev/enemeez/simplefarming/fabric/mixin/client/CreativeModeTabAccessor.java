package dev.enemeez.simplefarming.fabric.mixin.client;

import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Lets the Fabric paging code move the mod's tab to a slot on its own page. The tab's row and column
 * are set at registration time and are final by the time the creative inventory is shown, so the
 * page-1 code needs a way to pin the tab to a visible slot.
 */
@Mixin(CreativeModeTab.class)
public interface CreativeModeTabAccessor {

    @Mutable
    @Accessor("row")
    void setRow(CreativeModeTab.Row row);

    @Mutable
    @Accessor("column")
    void setColumn(int column);
}
