package dev.enemeez.simplefarming.common.loot;

import dev.enemeez.simplefarming.common.SimpleFarming;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

/**
 * The shared behaviour behind the mod's "seed drops" loot modifier: occasionally add one random
 * seed to the drops. The loader-specific wrapper applies it once the modifier's conditions match.
 */
public final class SeedDrops {
    private SeedDrops() {
    }

    public static void apply(ObjectArrayList<ItemStack> generatedLoot, RandomSource random) {
        generatedLoot.add(SimpleFarming.randomSeed(random));
    }
}
