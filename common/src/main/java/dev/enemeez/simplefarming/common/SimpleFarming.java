package dev.enemeez.simplefarming.common;

import com.mojang.logging.LogUtils;
import dev.enemeez.simplefarming.common.registries.ModBlockEntities;
import dev.enemeez.simplefarming.common.registries.ModBlocks;
import dev.enemeez.simplefarming.common.registries.ModCreativeTabs;
import dev.enemeez.simplefarming.common.registries.ModFeatures;
import dev.enemeez.simplefarming.common.registries.ModItems;
import dev.enemeez.simplefarming.common.registries.ModMenus;
import dev.enemeez.simplefarming.common.registries.ModPlacements;
import dev.enemeez.simplefarming.common.registries.ModRecipes;
import dev.enemeez.simplefarming.common.registries.ModSoundEvents;
import dev.enemeez.simplefarming.platform.ModRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import org.slf4j.Logger;

import java.util.function.Consumer;

/**
 * Loader-agnostic mod initialisation. Each loader's entrypoint hands every populated
 * {@link ModRegistry} to its own deferred-register implementation and drives the remaining hooks
 * from its lifecycle events.
 */
public final class SimpleFarming {
    public static final String MOD_ID = "simplefarming";
    public static final Logger LOGGER = LogUtils.getLogger();

    private SimpleFarming() {
    }

    /**
     * Receives each populated {@link ModRegistry} so the loader can register its entries. The
     * parameter is wildcarded because a lambda cannot implement a generic method.
     */
    @FunctionalInterface
    public interface RegistrySink {
        void accept(ModRegistry<?> registry);
    }

    /** Hands every populated registry to the loader. Call once, during mod construction. */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void register(RegistrySink sink) {
        sink.accept(ModBlocks.BLOCKS);
        sink.accept(ModItems.ITEMS);
        sink.accept(ModFeatures.FOLIAGE_PLACER_TYPES);
        sink.accept(ModPlacements.PLACED_FEATURES);
        sink.accept(ModSoundEvents.SOUND_EVENTS);
        sink.accept(ModBlockEntities.BLOCK_ENTITY_TYPES);
        sink.accept(ModMenus.MENU_TYPES);
        sink.accept(ModRecipes.RECIPE_SERIALIZERS);
        sink.accept(ModCreativeTabs.CREATIVE_TABS);
        LOGGER.info("Simple Farming: Continued registration queued");
    }

    /** Fills the mod's creative tab with every item. Called from the tab's display-items hook. */
    public static void acceptCreativeTab(Consumer<ItemStack> output) {
        ModItems.ITEM_LIST.stream().map(registration -> new ItemStack(registration.get())).forEach(output);
    }

    /** Applies the mod's food values to the composter. */
    public static void commonSetup() {
        ModItems.COMPOSTABLES.forEach((item, chance) -> ComposterBlock.COMPOSTABLES.put(item.get(), chance));
    }

    /** @return a random one of the mod's seeds, used by the global loot modifier. */
    public static ItemStack randomSeed(RandomSource random) {
        return new ItemStack(ModItems.SEEDS.get(random.nextInt(ModItems.SEEDS.size())).get());
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
