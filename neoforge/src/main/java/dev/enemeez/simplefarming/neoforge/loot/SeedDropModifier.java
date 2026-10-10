package dev.enemeez.simplefarming.neoforge.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.enemeez.simplefarming.common.loot.SeedDrops;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/** NeoForge global loot modifier that adds a random seed to the matching block drops. */
public class SeedDropModifier extends LootModifier {

    public static final Supplier<MapCodec<SeedDropModifier>> CODEC = Suppliers.memoize(
        () -> RecordCodecBuilder.mapCodec(instance -> codecStart(instance).apply(instance, SeedDropModifier::new))
    );

    protected SeedDropModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        SeedDrops.apply(generatedLoot, context.getRandom());
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
