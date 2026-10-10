package dev.enemeez.simplefarming.forge;

import dev.enemeez.simplefarming.common.CommonEvents;
import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.forge.loot.SeedDropModifier;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import dev.enemeez.simplefarming.platform.SimpleRegistration;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.mojang.serialization.MapCodec;

/**
 * Forge entrypoint. Content ids and behaviour match the original single-loader implementation.
 */
@Mod(SimpleFarming.MOD_ID)
public class SimpleFarmingForge {

    public SimpleFarmingForge(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();

        SimpleFarming.register(registry -> deferred(registry, modBus));

        DeferredRegister<MapCodec<? extends IGlobalLootModifier>> seedDrop =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SimpleFarming.MOD_ID);
        seedDrop.register(modBus);
        seedDrop.register("seed_drops", () -> SeedDropModifier.CODEC.get());

        modBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(SimpleFarming::commonSetup));
        // Client-only listeners (screens, colours) are registered by ForgeClientEvents, which Forge
        // only loads on the client.

        MinecraftForge.EVENT_BUS.register(this);
        SimpleFarming.LOGGER.info("Simple Farming: Continued (Forge) setup complete");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> void deferred(ModRegistry<T> registry, IEventBus modBus) {
        DeferredRegister<T> deferred = DeferredRegister.create(
            (ResourceKey<? extends Registry<T>>) registry.registryKey(),
            SimpleFarming.MOD_ID
        );
        deferred.register(modBus);
        for (Registration<T> registration : registry.registrations()) {
            RegistryObject<T> object = deferred.register(registration.id(), registration.factory());
            if (registration instanceof SimpleRegistration<T> simple) {
                simple.setDelegate(object);
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
