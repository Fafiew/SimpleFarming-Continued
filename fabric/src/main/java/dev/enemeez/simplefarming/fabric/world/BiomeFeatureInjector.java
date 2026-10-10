package dev.enemeez.simplefarming.fabric.world;

import com.google.common.base.Suppliers;
import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.common.registries.ModPlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds the mod's placed features to the matching biomes by rewriting each biome's generation
 * settings and the feature order of every dimension's chunk generator.
 *
 * <p>Forge and NeoForge inject the same features through {@code biome_modifier} data. Fabric has no
 * such data mechanism without the Fabric API, so this class performs the injection in code as the
 * server starts. Placement matches the data versions: every overworld biome receives the mod's
 * flora, and the desert cactus is limited to the desert tag.</p>
 *
 * <p>The {@code features} and {@code featuresPerStep} fields are widened by the mod's access
 * widener, which is exact because official Mojang mappings are used (no intermediary remapping).</p>
 */
public final class BiomeFeatureInjector {

    private BiomeFeatureInjector() {
    }

    // Placed in every overworld biome; mirrors the "minecraft:is_overworld" biome_modifier data.
    private static final List<ResourceKey<PlacedFeature>> OVERWORLD_FEATURES = List.of(
        ModPlacements.APPLE_TREE_CHECKED,
        ModPlacements.APRICOT_TREE_CHECKED,
        ModPlacements.BANANA_TREE_CHECKED,
        ModPlacements.CHERRY_TREE_CHECKED,
        ModPlacements.MANGO_TREE_CHECKED,
        ModPlacements.OLIVE_TREE_CHECKED,
        ModPlacements.ORANGE_TREE_CHECKED,
        ModPlacements.PEAR_TREE_CHECKED,
        ModPlacements.PLUM_TREE_CHECKED,
        ModPlacements.BLACKBERRY_BUSH_CHECKED,
        ModPlacements.BLUEBERRY_BUSH_CHECKED,
        ModPlacements.RASPBERRY_BUSH_CHECKED,
        ModPlacements.STRAWBERRY_BUSH_CHECKED,
        ModPlacements.CHICORY_CHECKED,
        ModPlacements.CUMIN_CHECKED,
        ModPlacements.MARSHMALLOW_CHECKED,
        ModPlacements.QUINOA_CHECKED
    );

    // Limited to the vanilla desert biome; mirrors the "#c:is_desert"/"#forge:is_desert" data.
    private static final List<ResourceKey<PlacedFeature>> DESERT_FEATURES = List.of(
        ModPlacements.OPUNTIA_CHECKED
    );

    /** Injects the mod's features. Runs once, before any level is created. */
    public static void inject(RegistryAccess registryAccess) {
        Registry<Biome> biomes = registryAccess.registryOrThrow(Registries.BIOME);
        Registry<PlacedFeature> placedFeatures = registryAccess.registryOrThrow(Registries.PLACED_FEATURE);
        TagKey<Biome> desert = TagKey.create(Registries.BIOME, SimpleFarming.id("fabric_desert"));
        int changed = 0;
        for (Holder.Reference<Biome> holder : biomes.holders().toList()) {
            List<ResourceKey<PlacedFeature>> additions = new ArrayList<>();
            if (holder.is(BiomeTags.IS_OVERWORLD)) {
                additions.addAll(OVERWORLD_FEATURES);
            }
            if (holder.is(desert)) {
                additions.addAll(DESERT_FEATURES);
            }
            if (!additions.isEmpty()) {
                addFeatures(holder.value().getGenerationSettings(), placedFeatures, additions);
                changed++;
            }
        }

        if (changed > 0) {
            rebuildFeatureOrder(registryAccess);
            SimpleFarming.LOGGER.info("Injected Simple Farming world generation into {} biomes", changed);
        }
    }

    /**
     * Appends the features to the vegetal-decoration step of a biome's generation settings. The
     * settings object is mutated in place, so holders keep resolving to the updated biome.
     */
    private static void addFeatures(
        BiomeGenerationSettings settings,
        Registry<PlacedFeature> placedFeatures,
        List<ResourceKey<PlacedFeature>> additions
    ) {
        List<HolderSet<PlacedFeature>> featureSteps = new ArrayList<>(settings.features);
        int step = GenerationStep.Decoration.VEGETAL_DECORATION.ordinal();

        // Feature lists are only dense up to the highest populated step; fill the gap first.
        while (step >= featureSteps.size()) {
            featureSteps.add(HolderSet.empty());
        }

        List<Holder<PlacedFeature>> merged = new ArrayList<>(featureSteps.get(step).stream().toList());
        for (ResourceKey<PlacedFeature> key : additions) {
            placedFeatures.getHolder(key).ifPresent(feature -> {
                if (!merged.contains(feature)) {
                    merged.add(feature);
                }
            });
        }
        featureSteps.set(step, HolderSet.direct(merged));
        settings.features = featureSteps;
    }

    /**
     * Recomputes the per-step feature ordering for every dimension, since the biome feature lists
     * were rewritten after the chunk generators were built.
     */
    private static void rebuildFeatureOrder(RegistryAccess registryAccess) {
        Registry<LevelStem> stems = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
        for (Holder.Reference<LevelStem> holder : stems.holders().toList()) {
            ChunkGenerator generator = holder.value().generator();
            generator.featuresPerStep = Suppliers.memoize(() -> FeatureSorter.buildFeaturesPerStep(
                List.copyOf(generator.getBiomeSource().possibleBiomes()),
                biome -> generator.getBiomeGenerationSettings(biome).features(),
                true
            ));
        }
    }
}
