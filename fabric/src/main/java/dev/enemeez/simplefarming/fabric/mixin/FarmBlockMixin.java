package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.common.CommonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Stops farmland from turning to dirt when a scarecrow stands nearby. Forge and NeoForge cancel
 * their farmland-trample event; Fabric has no such event, so the check runs before the vanilla
 * conversion instead.
 */
@Mixin(FarmBlock.class)
public class FarmBlockMixin {

    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    private void simplefarming$preventTrample(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (!level.isClientSide && CommonEvents.scarecrowPreventsTrample(level, pos)) {
            ci.cancel();
        }
    }
}
