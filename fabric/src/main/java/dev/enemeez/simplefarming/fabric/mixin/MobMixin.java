package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.common.CommonEvents;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adds the mod's tempting goals to chickens, cows, sheep and pigs. Forge and NeoForge react to the
 * entity-join event; Fabric has no such event, so the goals are added from the mob's own goal setup.
 */
@Mixin(Mob.class)
public class MobMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void simplefarming$addTemptingGoals(CallbackInfo ci) {
        CommonEvents.addEntityGoal(((Mob) (Object) this));
    }
}
