package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.common.SimpleFarming;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Adds the mod's seed drop loot to short grass, ferns, tall grass and large ferns when a survival
 * player breaks them.
 *
 * <p>Forge and NeoForge express this as a global loot modifier. Fabric has no equivalent without
 * the Fabric API, so the same 1/16 roll (skipped when shears are used) is applied here at the point
 * the block is actually destroyed.</p>
 */
@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {

    @Shadow
    protected ServerLevel level;

    @Shadow
    protected ServerPlayer player;

    @Inject(method = "destroyBlock", at = @At("RETURN"))
    private void simplefarming$dropSeeds(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ() || this.player.isCreative()) {
            return;
        }

        BlockState state = this.level.getBlockState(pos);
        if (!isSeedSource(state.getBlock()) || this.player.getMainHandItem().is(Items.SHEARS)) {
            return;
        }

        if (this.level.random.nextFloat() < 0.0625F) {
            Block.popResourceFromFace(this.level, pos, this.player.getDirection(),
                SimpleFarming.randomSeed(this.level.random));
        }
    }

    private static boolean isSeedSource(Block block) {
        return block == Blocks.SHORT_GRASS || block == Blocks.FERN
            || block == Blocks.TALL_GRASS || block == Blocks.LARGE_FERN;
    }
}

