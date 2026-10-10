package dev.enemeez.simplefarming.fabric.mixin.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

/**
 * Exposes vanilla's block-to-render-layer table, which is private and, unlike Forge and NeoForge,
 * has no public setter in vanilla.
 */
@Mixin(ItemBlockRenderTypes.class)
public interface ItemBlockRenderTypesAccessor {

    @Accessor("TYPE_BY_BLOCK")
    static Map<Block, RenderType> getTypeByBlock() {
        throw new AssertionError();
    }
}
