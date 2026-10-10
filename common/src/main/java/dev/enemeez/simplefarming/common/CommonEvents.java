package dev.enemeez.simplefarming.common;

import dev.enemeez.simplefarming.common.registries.ModBlocks;
import dev.enemeez.simplefarming.common.registries.ModItems;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.LevelAccessor;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Loader-agnostic gameplay tweaks. Each loader's entrypoint forwards its own events here.
 */
public final class CommonEvents {
    private CommonEvents() {
    }

    /** Adds a tempting goal to the animals that should follow the mod's food. */
    public static void addEntityGoal(Entity entity) {
        EntityType<?> entityType = entity.getType();

        if (entityType == EntityType.CHICKEN) {
            Ingredient seeds = Ingredient.of(ModItems.SEEDS.stream().map(Registration::get).map(ItemStack::new));
            ((Chicken) entity).goalSelector.addGoal(3, new TemptGoal((Chicken) entity, 1.0D, seeds, false));
        } else if (entityType == EntityType.COW) {
            ((Cow) entity).goalSelector.addGoal(3, new TemptGoal((Cow) entity, 1.25D, grains(), false));
        } else if (entityType == EntityType.SHEEP) {
            ((Sheep) entity).goalSelector.addGoal(3, new TemptGoal((Sheep) entity, 1.1D, grains(), false));
        } else if (entityType == EntityType.PIG) {
            ((Pig) entity).goalSelector.addGoal(3, new TemptGoal((Pig) entity, 1.2D, roots(), false));
        }
    }

    /** @return whether a scarecrow stands near {@code pos}, protecting the farmland from trampling. */
    public static boolean scarecrowPreventsTrample(LevelAccessor level, BlockPos pos) {
        return BlockPos.betweenClosedStream(pos.offset(new Vec3i(-5, 1, -5)), pos.offset(new Vec3i(5, 1, 5)))
            .anyMatch(scanPos -> level.getBlockState(scanPos).is(ModBlocks.SCARECROW.get()));
    }

    /**
     * The mod's seed packets offered as the farmer's level-1 trade. Each entry builds its offer
     * lazily so the items are resolved only once the game is running.
     */
    public static List<Supplier<MerchantOffer>> farmerSeedPacketTrades() {
        return Stream.of(
            ModItems.FIBER_SEED_PACKET,
            ModItems.FRUIT_SEED_PACKET,
            ModItems.GOURD_SEED_PACKET,
            ModItems.GRAIN_SEED_PACKET,
            ModItems.LEAFY_SEED_PACKET,
            ModItems.LEGUME_SEED_PACKET,
            ModItems.ROOT_SEED_PACKET,
            ModItems.TUBER_SEED_PACKET
        ).map(packet -> (Supplier<MerchantOffer>) () -> new MerchantOffer(
            new ItemCost(Items.EMERALD, 1),
            new ItemStack(packet.get(), 1),
            16,
            2,
            0.05F
        )).toList();
    }

    private static Ingredient grains() {
        return Ingredient.of(
            ModItems.BARLEY.get(),
            ModItems.OAT.get(),
            ModItems.RYE.get(),
            ModItems.RICE.get(),
            ModItems.CORN.get(),
            ModItems.SORGHUM.get()
        );
    }

    private static Ingredient roots() {
        return Ingredient.of(
            ModItems.ONION.get(),
            ModItems.SWEET_POTATO.get(),
            ModItems.RADISH.get(),
            ModItems.TURNIP.get(),
            ModItems.YAM.get()
        );
    }
}
