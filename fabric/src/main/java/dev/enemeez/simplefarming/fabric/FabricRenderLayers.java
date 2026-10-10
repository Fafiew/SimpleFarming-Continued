package dev.enemeez.simplefarming.fabric;

import dev.enemeez.simplefarming.common.registries.ModBlocks;
import dev.enemeez.simplefarming.fabric.mixin.client.ItemBlockRenderTypesAccessor;
import dev.enemeez.simplefarming.platform.Registration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.Map;

/**
 * Applies the cutout render layer to the mod's foliage on Fabric.
 *
 * <p>Forge and NeoForge read a model's {@code render_type} attribute, which vanilla has no concept
 * of. Vanilla (and therefore Fabric) decides a block's layer from the hardcoded
 * {@code ItemBlockRenderTypes} table instead, into which Forge and NeoForge merge the attribute when
 * they load models. This reproduces that merge for Fabric by walking the blocks that
 * {@link ModBlocks} marked as cutout and inserting them into that same table.</p>
 */
@Environment(EnvType.CLIENT)
public final class FabricRenderLayers {

    private FabricRenderLayers() {
    }

    public static void registerCutoutBlocks() {
        Map<Block, RenderType> layerByBlock = ItemBlockRenderTypesAccessor.getTypeByBlock();
        for (Registration<Block> registration : ModBlocks.CUTOUT_LAYER_BLOCKS) {
            Block block = registration.get();
            if (block != null) {
                layerByBlock.put(block, RenderType.cutout());
            }
        }
    }
}
