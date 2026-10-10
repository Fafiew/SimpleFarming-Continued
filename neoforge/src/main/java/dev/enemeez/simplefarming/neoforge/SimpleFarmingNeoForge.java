package dev.enemeez.simplefarming.neoforge;

import com.mojang.serialization.MapCodec;
import dev.enemeez.simplefarming.common.CommonEvents;
import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.neoforge.loot.SeedDropModifier;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import dev.enemeez.simplefarming.platform.SimpleRegistration;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * NeoForge entrypoint. Content ids and behaviour match the Forge build.
 */
@Mod(SimpleFarming.MOD_ID)
public class SimpleFarmingNeoForge {

    public SimpleFarmingNeoForge(IEventBus modBus) {
        SimpleFarming.register(registry -> deferred(registry, modBus));

        DeferredRegister<MapCodec<? extends IGlobalLootModifier>> seedDrop =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SimpleFarming.MOD_ID);
        seedDrop.register(modBus);
        seedDrop.register("seed_drops", () -> SeedDropModifier.CODEC.get());

        modBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(SimpleFarming::commonSetup));
        // Client-only listeners (screens, colours) are registered by NeoForgeClientEvents, which
        // NeoForge only loads on the client.

        NeoForge.EVENT_BUS.register(this);
        SimpleFarming.LOGGER.info("Simple Farming: Continued (NeoForge) setup complete");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> void deferred(ModRegistry<T> registry, IEventBus modBus) {
        DeferredRegister<T> deferred = DeferredRegister.create(
            (ResourceKey<? extends Registry<T>>) registry.registryKey(),
            SimpleFarming.MOD_ID
        );
        deferred.register(modBus);
        for (Registration<T> registration : registry.registrations()) {
            var holder = deferred.register(registration.id(), registration.factory());
            if (registration instanceof SimpleRegistration<T> simple) {
                simple.setDelegate(holder::get);
            }
        }
    }

    @SubscribeEvent
    public void cancelTrample(BlockEvent.FarmlandTrampleEvent event) {
        event.setCanceled(CommonEvents.scarecrowPreventsTrample(event.getLevel(), event.getPos()));
    }

    @SubscribeEvent
    public void addEntityGoal(EntityJoinLevelEvent event) {
        CommonEvents.addEntityGoal(event.getEntity());
    }

    @SubscribeEvent
    public void addTrade(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            CommonEvents.farmerSeedPacketTrades().forEach(offer -> event.getTrades().get(1).add((entity, rand) -> offer.get()));
        }
    }
}
