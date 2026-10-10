package dev.enemeez.simplefarming.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A copy of the vanilla {@code CropBlock#getGrowthSpeed} soil algorithm, kept here because the
 * loaders disagree on that method's signature (Forge takes a {@link Block}, NeoForge a
 * {@link BlockState}). Using one shared implementation keeps crop growth identical everywhere.
 */
public final class GrowthSpeed {
    private GrowthSpeed() {
    }

    public static float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        float speed = 1.0F;
        BlockPos below = pos.below();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                float neighbour = 0.0F;
                BlockState state = level.getBlockState(below.offset(dx, 0, dz));
                if (state.getBlock() instanceof FarmBlock) {
                    neighbour = 1.0F;
                    if (state.getValue(FarmBlock.MOISTURE) > 0) {
                        neighbour = 3.0F;
                    }
                }
                if (dx != 0 || dz != 0) {
                    neighbour /= 4.0F;
                }
                speed += neighbour;
            }
        }

        boolean alongX = level.getBlockState(pos.west()).is(block) || level.getBlockState(pos.east()).is(block);
        boolean alongZ = level.getBlockState(pos.north()).is(block) || level.getBlockState(pos.south()).is(block);
        if (alongX && alongZ) {
            speed /= 2.0F;
        } else {
            boolean diagonal = level.getBlockState(pos.west().north()).is(block)
                || level.getBlockState(pos.east().north()).is(block)
                || level.getBlockState(pos.east().south()).is(block)
                || level.getBlockState(pos.west().south()).is(block);
            if (diagonal) {
                speed /= 2.0F;
            }
        }

        return speed;
    }
}
