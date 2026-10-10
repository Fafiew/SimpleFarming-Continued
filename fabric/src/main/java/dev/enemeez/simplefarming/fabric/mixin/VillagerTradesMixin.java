package dev.enemeez.simplefarming.fabric.mixin;

import dev.enemeez.simplefarming.common.CommonEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adds the mod's seed packets to the farmer's level-1 trades. Forge and NeoForge react to the
 * villager-trades event; Fabric has no such event, so the entries are appended right after the
 * vanilla trade table is built.
 */
@Mixin(VillagerTrades.class)
public class VillagerTradesMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void simplefarming$addFarmerTrades(CallbackInfo ci) {
        var trades = VillagerTrades.TRADES.get(VillagerProfession.FARMER);
        if (trades == null) {
            return;
        }

        var level1 = trades.get(1);
        var offers = CommonEvents.farmerSeedPacketTrades();
        var extended = java.util.Arrays.copyOf(level1, level1.length + offers.size());
        for (int i = 0; i < offers.size(); i++) {
            final var offer = offers.get(i);
            extended[level1.length + i] = (entity, random) -> offer.get();
        }
        trades.put(1, extended);
    }
}
