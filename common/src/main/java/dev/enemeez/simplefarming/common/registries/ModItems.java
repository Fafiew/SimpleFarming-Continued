package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.common.item.AlcoholItem;
import dev.enemeez.simplefarming.common.item.GrainCropItem;
import dev.enemeez.simplefarming.common.item.RootCropItem;
import dev.enemeez.simplefarming.common.item.ScytheItem;
import dev.enemeez.simplefarming.common.item.SeedItem;
import dev.enemeez.simplefarming.common.item.SeedPacketItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

import net.minecraft.world.item.JukeboxSong;

import net.minecraft.world.item.Rarity;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final ModRegistry<Item> ITEMS = new ModRegistry<>(net.minecraft.core.registries.Registries.ITEM);
    public static final LinkedList<Registration<Item>> ITEM_LIST = new LinkedList<>();
    public static final Map<Registration<Item>, Float> COMPOSTABLES = new HashMap<>();
    public static final LinkedList<Registration<Item>> SEEDS = new LinkedList<>();

    public static final ResourceKey<JukeboxSong> NOURISH_SONG = ResourceKey.create(
        Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(SimpleFarming.MOD_ID, "nourish")
    );

    // ******************** Blocks ********************
    // Wild bushes
    public static final Registration<Item> BLACKBERRY_BUSH = registerBlockItem("blackberry_bush", ModBlocks.BLACKBERRY_BUSH, 0.3F);
    public static final Registration<Item> BLUEBERRY_BUSH = registerBlockItem("blueberry_bush", ModBlocks.BLUEBERRY_BUSH, 0.3F);
    public static final Registration<Item> RASPBERRY_BUSH = registerBlockItem("raspberry_bush", ModBlocks.RASPBERRY_BUSH, 0.3F);
    public static final Registration<Item> STRAWBERRY_BUSH = registerBlockItem("strawberry_bush", ModBlocks.STRAWBERRY_BUSH, 0.3F);
    public static final Registration<Item> OPUNTIA = registerBlockItem("opuntia", ModBlocks.OPUNTIA, 0.5F);

    // Hay bales
    public static final Registration<Item> BARLEY_HAY_BLOCK = registerBlockItem("barley_hay_block", ModBlocks.BARLEY_HAY_BLOCK, 0.85F);
    public static final Registration<Item> OAT_HAY_BLOCK = registerBlockItem("oat_hay_block", ModBlocks.OAT_HAY_BLOCK, 0.85F);
    public static final Registration<Item> RICE_HAY_BLOCK = registerBlockItem("rice_hay_block", ModBlocks.RICE_HAY_BLOCK, 0.85F);
    public static final Registration<Item> RYE_HAY_BLOCK = registerBlockItem("rye_hay_block", ModBlocks.RYE_HAY_BLOCK, 0.85F);

    // Fermenter
    public static final Registration<Item> FERMENTER = registerBlockItem("fermenter", ModBlocks.FERMENTER);

    // Gourd-like
    public static final Registration<Item> CANTALOUPE_BLOCK = registerBlockItem("cantaloupe_block", ModBlocks.CANTALOUPE, 1.0F);
    public static final Registration<Item> HONEYDEW_BLOCK = registerBlockItem("honeydew_block", ModBlocks.HONEYDEW, 1.0F);
    public static final Registration<Item> SQUASH_BLOCK = registerBlockItem("squash_block", ModBlocks.SQUASH, 1.0F);

    // Saplings
    public static final Registration<Item> APPLE_SAPLING = registerBlockItem("apple_sapling", ModBlocks.APPLE_SAPLING, 0.3F);
    public static final Registration<Item> APRICOT_SAPLING = registerBlockItem("apricot_sapling", ModBlocks.APRICOT_SAPLING, 0.3F);
    public static final Registration<Item> BANANA_SAPLING = registerBlockItem("banana_sapling", ModBlocks.BANANA_SAPLING, 0.3F);
    public static final Registration<Item> CHERRY_SAPLING = registerBlockItem("cherry_sapling", ModBlocks.CHERRY_SAPLING, 0.3F);
    public static final Registration<Item> MANGO_SAPLING = registerBlockItem("mango_sapling", ModBlocks.MANGO_SAPLING, 0.3F);
    public static final Registration<Item> OLIVE_SAPLING = registerBlockItem("olive_sapling", ModBlocks.OLIVE_SAPLING, 0.3F);
    public static final Registration<Item> ORANGE_SAPLING = registerBlockItem("orange_sapling", ModBlocks.ORANGE_SAPLING, 0.3F);
    public static final Registration<Item> PEAR_SAPLING = registerBlockItem("pear_sapling", ModBlocks.PEAR_SAPLING, 0.3F);
    public static final Registration<Item> PLUM_SAPLING = registerBlockItem("plum_sapling", ModBlocks.PLUM_SAPLING, 0.3F);

    // Scarecrow
    public static final Registration<Item> SCARECROW = registerBlockItem("scarecrow", ModBlocks.SCARECROW);

    // Trellis
    public static final Registration<Item> TRELLIS = registerBlockItem("trellis", ModBlocks.TRELLIS);

    // Cake
    public static final Registration<Item> CHOCOLATE_CAKE = registerCakeLike("chocolate_cake", ModBlocks.CHOCOLATE_CAKE);
    public static final Registration<Item> SPRINKLE_CAKE = registerCakeLike("sprinkle_cake", ModBlocks.SPRINKLE_CAKE);
    public static final Registration<Item> CHEESE_WHEEL = registerCakeLike("cheese_wheel", ModBlocks.CHEESE_WHEEL);
    // ************************************************

    // Scythes
    public static final Registration<Item> WOODEN_SCYTHE = registerScythe("wooden_scythe", Tiers.WOOD);
    public static final Registration<Item> STONE_SCYTHE = registerScythe("stone_scythe", Tiers.STONE);
    public static final Registration<Item> IRON_SCYTHE = registerScythe("iron_scythe", Tiers.IRON);
    public static final Registration<Item> GOLDEN_SCYTHE = registerScythe("golden_scythe", Tiers.GOLD);
    public static final Registration<Item> DIAMOND_SCYTHE = registerScythe("diamond_scythe", Tiers.DIAMOND);
    public static final Registration<Item> NETHERITE_SCYTHE = registerScythe("netherite_scythe", Tiers.NETHERITE);

    // Music disc
    public static final Registration<Item> MUSIC_DISC_NOURISH = registerMusicDisc("music_disc_nourish", 2340);

    // Seed packets
    public static final Registration<Item> FIBER_SEED_PACKET = registerSeedPacket("fiber_seed_packet", SeedPacketItem.Type.FIBER);
    public static final Registration<Item> FRUIT_SEED_PACKET = registerSeedPacket("fruit_seed_packet", SeedPacketItem.Type.FRUIT);
    public static final Registration<Item> GOURD_SEED_PACKET = registerSeedPacket("gourd_seed_packet", SeedPacketItem.Type.GOURD);
    public static final Registration<Item> GRAIN_SEED_PACKET = registerSeedPacket("grain_seed_packet", SeedPacketItem.Type.GRAIN);
    public static final Registration<Item> LEAFY_SEED_PACKET = registerSeedPacket("leafy_seed_packet", SeedPacketItem.Type.LEAFY);
    public static final Registration<Item> LEGUME_SEED_PACKET = registerSeedPacket("legume_seed_packet", SeedPacketItem.Type.LEGUME);
    public static final Registration<Item> ROOT_SEED_PACKET = registerSeedPacket("root_seed_packet", SeedPacketItem.Type.ROOT);
    public static final Registration<Item> TUBER_SEED_PACKET = registerSeedPacket("tuber_seed_packet", SeedPacketItem.Type.TUBER);

    // Grapes
    public static final Registration<Item> GRAPES = registerFood("grapes", ModFoods.FRUIT, 0.65F);

    // Fruit tree
    public static final Registration<Item> APRICOT = registerFood("apricot", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> BANANA = registerFood("banana", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> CHERRIES = registerFood("cherries", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> MANGO = registerFood("mango", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> OLIVES = registerFood("olives", ModFoods.OLIVES, 0.65F);
    public static final Registration<Item> ORANGE = registerFood("orange", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> PEAR = registerFood("pear", ModFoods.FRUIT, 0.65F);
    public static final Registration<Item> PLUM = registerFood("plum", ModFoods.FRUIT, 0.65F);

    // Gourds
    public static final Registration<Item> CANTALOUPE = registerFood("cantaloupe", ModFoods.GOURD_SLICE, 0.3F);
    public static final Registration<Item> HONEYDEW = registerFood("honeydew", ModFoods.GOURD_SLICE, 0.3F);
    public static final Registration<Item> SQUASH = registerFood("squash", ModFoods.GOURD_SLICE, 0.3F);

    // Berries
    public static final Registration<Item> BLACKBERRIES = registerFood("blackberries", ModFoods.BERRY, 0.3F);
    public static final Registration<Item> BLUEBERRIES = registerFood("blueberries", ModFoods.BERRY, 0.3F);
    public static final Registration<Item> RASPBERRIES = registerFood("raspberries", ModFoods.BERRY, 0.3F);
    public static final Registration<Item> STRAWBERRIES = registerFood("strawberries", ModFoods.BERRY, 0.3F);
    public static final Registration<Item> CACTUS_FRUIT = registerFood("cactus_fruit", ModFoods.BERRY, 0.3F);

    // Vegetables
    public static final Registration<Item> BROCCOLI = registerFood("broccoli", ModFoods.BROCCOLI, 0.65F);
    public static final Registration<Item> CASSAVA = registerFood("cassava", ModFoods.CASSAVA, 0.65F);
    public static final Registration<Item> CORN = registerGrainCropItem("corn", ModFoods.CORN);
    public static final Registration<Item> CUCUMBER = registerFood("cucumber", ModFoods.CUCUMBER, 0.65F);
    public static final Registration<Item> EGGPLANT = registerFood("eggplant", ModFoods.EGGPLANT, 0.65F);
    public static final Registration<Item> HABANERO = registerFood("habanero", ModFoods.PEPPER, 0.65F);
    public static final Registration<Item> GINGER = registerFood("ginger", ModFoods.GINGER, 0.65F);
    public static final Registration<Item> LETTUCE = registerFood("lettuce", ModFoods.SALAD_GREENS, 0.65F);
    public static final Registration<Item> ONION = registerRootCropItem("onion", ModFoods.ONION);
    public static final Registration<Item> PEA_POD = registerFood("pea_pod", ModFoods.PEA_POD, 0.65F);
    public static final Registration<Item> PEANUT = registerFood("peanut", ModFoods.PEANUT, 0.65F);
    public static final Registration<Item> PEPPER = registerFood("pepper", ModFoods.PEPPER, 0.65F);
    public static final Registration<Item> RADISH = registerRootCropItem("radish", ModFoods.RADISH);
    public static final Registration<Item> SORGHUM = registerGrainCropItem("sorghum", ModFoods.SORGHUM);
    public static final Registration<Item> SOYBEAN = registerFood("soybean", ModFoods.SOYBEAN, 0.65F);
    public static final Registration<Item> SPINACH = registerFood("spinach", ModFoods.SALAD_GREENS, 0.65F);
    public static final Registration<Item> SWEET_POTATO = registerRootCropItem("sweet_potato", ModFoods.SWEET_POTATO);
    public static final Registration<Item> TOMATO = registerFood("tomato", ModFoods.TOMATO, 0.65F);
    public static final Registration<Item> TURNIP = registerRootCropItem("turnip", ModFoods.TURNIP);
    public static final Registration<Item> YAM = registerRootCropItem("yam", Foods.POTATO);
    public static final Registration<Item> ZUCCHINI = registerFood("zucchini", ModFoods.ZUCCHINI, 0.65F);

    // Misc. crops
    public static final Registration<Item> COTTON = registerItem("cotton", 0.65F);
    public static final Registration<Item> KENAF_FIBER = registerItem("kenaf_fiber", 0.65F);
    public static final Registration<Item> BARLEY = registerGrainCropItem("barley");
    public static final Registration<Item> OAT = registerGrainCropItem("oat");
    public static final Registration<Item> RYE = registerGrainCropItem("rye");
    public static final Registration<Item> RICE = registerGrainCropItem("rice");

    // Ingredients
    public static final Registration<Item> CHEESE_SLICE = registerFood("cheese_slice", ModFoods.CHEESE_SLICE, 0.3F);
    public static final Registration<Item> CURRY_POWDER = registerItem("curry_powder", 0.85F);
    public static final Registration<Item> JAM = registerItem("jam");
    public static final Registration<Item> OLIVE_OIL = registerItem("olive_oil");
    public static final Registration<Item> VINEGAR = registerItem("vinegar");
    public static final Registration<Item> SPRINKLES = registerItem("sprinkles", 0.3F);
    public static final Registration<Item> NOODLES = registerItem("noodles", 0.85F);

    // Beverages
    public static final Registration<Item> BEER = registerAlcohol("beer", MobEffects.DAMAGE_BOOST);
    public static final Registration<Item> CAUIM = registerAlcohol("cauim", MobEffects.JUMP);
    public static final Registration<Item> CIDER = registerAlcohol("cider", MobEffects.LUCK);
    public static final Registration<Item> MEAD = registerAlcohol("mead", MobEffects.DIG_SPEED);
    public static final Registration<Item> SAKE = registerAlcohol("sake", MobEffects.MOVEMENT_SPEED);
    public static final Registration<Item> TISWIN = registerAlcohol("tiswin", MobEffects.HEALTH_BOOST);
    public static final Registration<Item> VODKA = registerAlcohol("vodka", MobEffects.DAMAGE_RESISTANCE);
    public static final Registration<Item> WHISKEY = registerAlcohol("whiskey", MobEffects.ABSORPTION);
    public static final Registration<Item> WINE = registerAlcohol("wine", MobEffects.REGENERATION);

    // Seeds
    public static final Registration<Item> BARLEY_SEEDS = registerSeed("barley_seeds", ModBlocks.BARLEY);
    public static final Registration<Item> BROCCOLI_SEEDS = registerSeed("broccoli_seeds", ModBlocks.BROCCOLI);
    public static final Registration<Item> CANTALOUPE_SEEDS = registerSeed("cantaloupe_seeds", ModBlocks.CANTALOUPE_STEM);
    public static final Registration<Item> CORN_SEEDS = registerSeed("corn_seeds", ModBlocks.CORN);
    public static final Registration<Item> COTTON_SEEDS = registerSeed("cotton_seeds", ModBlocks.COTTON);
    public static final Registration<Item> CASSAVA_SEEDS = registerSeed("cassava_seeds", ModBlocks.CASSAVA);
    public static final Registration<Item> CUCUMBER_SEEDS = registerSeed("cucumber_seeds", ModBlocks.CUCUMBERS);
    public static final Registration<Item> EGGPLANT_SEEDS = registerSeed("eggplant_seeds", ModBlocks.EGGPLANTS);
    public static final Registration<Item> GINGER_SEEDS = registerSeed("ginger_seeds", ModBlocks.GINGER);
    public static final Registration<Item> GRAPE_SEEDS = registerSeed("grape_seeds", ModBlocks.GRAPE_TRUNK);
    public static final Registration<Item> HONEYDEW_SEEDS = registerSeed("honeydew_seeds", ModBlocks.HONEYDEW_STEM);
    public static final Registration<Item> KENAF_SEEDS = registerSeed("kenaf_seeds", ModBlocks.KENAF);
    public static final Registration<Item> LETTUCE_SEEDS = registerSeed("lettuce_seeds", ModBlocks.LETTUCE);
    public static final Registration<Item> OAT_SEEDS = registerSeed("oat_seeds", ModBlocks.OAT);
    public static final Registration<Item> ONION_SEEDS = registerSeed("onion_seeds", ModBlocks.ONIONS);
    public static final Registration<Item> PEA_SEEDS = registerSeed("pea_seeds", ModBlocks.PEAS);
    public static final Registration<Item> PEANUT_SEEDS = registerSeed("peanut_seeds", ModBlocks.PEANUTS);
    public static final Registration<Item> PEPPER_SEEDS = registerSeed("pepper_seeds", ModBlocks.PEPPERS);
    public static final Registration<Item> RADISH_SEEDS = registerSeed("radish_seeds", ModBlocks.RADISHES);
    public static final Registration<Item> RICE_SEEDS = registerSeed("rice_seeds", ModBlocks.RICE);
    public static final Registration<Item> RYE_SEEDS = registerSeed("rye_seeds", ModBlocks.RYE);
    public static final Registration<Item> SORGHUM_SEEDS = registerSeed("sorghum_seeds", ModBlocks.SORGHUM);
    public static final Registration<Item> SOYBEAN_SEEDS = registerSeed("soybean_seeds", ModBlocks.SOYBEANS);
    public static final Registration<Item> SPINACH_SEEDS = registerSeed("spinach_seeds", ModBlocks.SPINACH);
    public static final Registration<Item> SQUASH_SEEDS = registerSeed("squash_seeds", ModBlocks.SQUASH_STEM);
    public static final Registration<Item> SWEET_POTATO_SEEDS = registerSeed("sweet_potato_seeds", ModBlocks.SWEET_POTATOES);
    public static final Registration<Item> TOMATO_SEEDS = registerSeed("tomato_seeds", ModBlocks.TOMATOES);
    public static final Registration<Item> TURNIP_SEEDS = registerSeed("turnip_seeds", ModBlocks.TURNIPS);
    public static final Registration<Item> YAM_SEEDS = registerSeed("yam_seeds", ModBlocks.YAMS);
    public static final Registration<Item> ZUCCHINI_SEEDS = registerSeed("zucchini_seeds", ModBlocks.ZUCCHINIS);

    // Edible seeds
    public static final Registration<Item> CHICORY_ROOT = registerEdibleSeed("chicory_root", ModBlocks.CHICORY);
    public static final Registration<Item> CUMIN_SEEDS = registerEdibleSeed("cumin_seeds", ModBlocks.CUMIN);
    public static final Registration<Item> MARSHMALLOW_ROOT = registerEdibleSeed("marshmallow_root", ModBlocks.MARSHMALLOW);
    public static final Registration<Item> QUINOA_SEEDS = registerEdibleSeed("quinoa_seeds", ModBlocks.QUINOA);

    // Pies
    public static final Registration<Item> APPLE_PIE = registerFood("apple_pie", ModFoods.FRUIT_PIE, 1.0F);
    public static final Registration<Item> APRICOT_PIE = registerFood("apricot_pie", ModFoods.FRUIT_PIE, 1.0F);
    public static final Registration<Item> BLACKBERRY_PIE = registerFood("blackberry_pie", ModFoods.BERRY_PIE, 1.0F);
    public static final Registration<Item> BLUEBERRY_PIE = registerFood("blueberry_pie", ModFoods.BERRY_PIE, 1.0F);
    public static final Registration<Item> CHERRY_PIE = registerFood("cherry_pie", ModFoods.FRUIT_PIE, 1.0F);
    public static final Registration<Item> PEANUT_BUTTER_PIE = registerFood("peanut_butter_pie", ModFoods.PEANUT_BUTTER_PIE, 1.0F);
    public static final Registration<Item> PEAR_PIE = registerFood("pear_pie", ModFoods.FRUIT_PIE, 1.0F);
    public static final Registration<Item> PLUM_PIE = registerFood("plum_pie", ModFoods.FRUIT_PIE, 1.0F);
    public static final Registration<Item> RASPBERRY_PIE = registerFood("raspberry_pie", ModFoods.BERRY_PIE, 1.0F);
    public static final Registration<Item> STRAWBERRY_PIE = registerFood("strawberry_pie", ModFoods.BERRY_PIE, 1.0F);

    // Bowl foods
    public static final Registration<Item> BORSCHT = registerBowlFood("borscht", ModFoods.BORSCHT);
    public static final Registration<Item> BROCCOLI_CHEESE_SOUP = registerBowlFood("broccoli_cheese_soup", ModFoods.BROCCOLI_CHEESE_SOUP);
    public static final Registration<Item> CAESAR_SALAD = registerBowlFood("caesar_salad", ModFoods.CAESAR_SALAD);
    public static final Registration<Item> CARROT_SOUP = registerBowlFood("carrot_soup", ModFoods.CARROT_SOUP);
    public static final Registration<Item> CHICKEN_NOODLE_SOUP = registerBowlFood("chicken_noodle_soup", ModFoods.CHICKEN_NOODLE_SOUP);
    public static final Registration<Item> CHILI = registerBowlFood("chili", ModFoods.CHILI);
    public static final Registration<Item> CORN_SALAD = registerBowlFood("corn_salad", ModFoods.CORN_SALAD);
    public static final Registration<Item> CUCUMBER_SOUP = registerBowlFood("cucumber_soup", ModFoods.CUCUMBER_SOUP);
    public static final Registration<Item> FRUIT_SALAD = registerBowlFood("fruit_salad", ModFoods.FRUIT_SALAD);
    public static final Registration<Item> OATMEAL = registerBowlFood("oatmeal", ModFoods.OATMEAL);
    public static final Registration<Item> OLIVE_TOMATO_SALAD = registerBowlFood("olive_tomato_salad", ModFoods.OLIVE_TOMATO_SALAD);
    public static final Registration<Item> ONION_SOUP = registerBowlFood("onion_soup", ModFoods.ONION_SOUP);
    public static final Registration<Item> PEA_SOUP = registerBowlFood("pea_soup", ModFoods.PEA_SOUP);
    public static final Registration<Item> PUMPKIN_SOUP = registerBowlFood("pumpkin_soup", ModFoods.PUMPKIN_SOUP);
    public static final Registration<Item> QUINOA_SALAD = registerBowlFood("quinoa_salad", ModFoods.QUINOA_SALAD);
    public static final Registration<Item> RADISH_SOUP = registerBowlFood("radish_soup", ModFoods.RADISH_SOUP);
    public static final Registration<Item> RICE_BOWL = registerBowlFood("rice_bowl", ModFoods.RICE_BOWL);
    public static final Registration<Item> SALAD = registerBowlFood("salad", ModFoods.SALAD);
    public static final Registration<Item> SAUSAGE_BARLEY_SOUP = registerBowlFood("sausage_barley_soup", ModFoods.SAUSAGE_BARLEY);
    public static final Registration<Item> SORGHUM_PORRIDGE = registerBowlFood("sorghum_porridge", ModFoods.SORGHUM_PORRIDGE);
    public static final Registration<Item> SQUASH_SOUP = registerBowlFood("squash_soup", ModFoods.SQUASH_SOUP);
    public static final Registration<Item> TOMATO_SOUP = registerBowlFood("tomato_soup", ModFoods.TOMATO_SOUP);
    public static final Registration<Item> VEGETABLE_MEDLEY = registerBowlFood("vegetable_medley", ModFoods.VEGETABLE_MEDLEY);

    // Bread
    public static final Registration<Item> BANANA_BREAD = registerFood("banana_bread", ModFoods.BANANA_BREAD, 0.85F);
    public static final Registration<Item> BARLEY_BREAD = registerFood("barley_bread", Foods.BREAD, 0.85F);
    public static final Registration<Item> CORNBREAD = registerFood("cornbread", ModFoods.CORN_BREAD, 0.85F);
    public static final Registration<Item> OAT_BREAD = registerFood("oat_bread", Foods.BREAD, 0.85F);
    public static final Registration<Item> RICE_BREAD = registerFood("rice_bread", Foods.BREAD, 0.85F);
    public static final Registration<Item> RYE_BREAD = registerFood("rye_bread", Foods.BREAD, 0.85F);
    public static final Registration<Item> SORGHUM_BREAD = registerFood("sorghum_bread", ModFoods.SORGHUM_BREAD, 0.85F);
    public static final Registration<Item> ZUCCHINI_BREAD = registerFood("zucchini_bread", ModFoods.ZUCCHINI_BREAD, 0.85F);

    // Sandwiches
    public static final Registration<Item> BLT = registerFood("blt", ModFoods.BLT);
    public static final Registration<Item> CHEESEBURGER = registerFood("cheeseburger", ModFoods.CHEESE_BURGER);
    public static final Registration<Item> EGG_SANDWICH = registerFood("egg_sandwich", ModFoods.EGG_SANDWICH);
    public static final Registration<Item> FISH_SANDWICH = registerFood("fish_sandwich", ModFoods.FISH_SANDWICH);
    public static final Registration<Item> HAMBURGER = registerFood("hamburger", ModFoods.HAMBURGER);
    public static final Registration<Item> ITALIAN_BEEF = registerFood("italian_beef", ModFoods.ITALIAN_BEEF);
    public static final Registration<Item> HOTDOG = registerFood("hotdog", ModFoods.HOTDOG);
    public static final Registration<Item> PBJ = registerFood("pbj", ModFoods.PBJ, 1.0F);
    public static final Registration<Item> PULLED_PORK_SANDWICH = registerFood("pulled_pork_sandwich", ModFoods.PULLED_PORK_SANDWICH);
    public static final Registration<Item> SANDWICH = registerFood("sandwich", ModFoods.SANDWICH, 1.0F);
    public static final Registration<Item> VEGGIE_BURGER = registerFood("veggie_burger", ModFoods.VEGGIE_BURGER, 1.0F);

    // Animal-related
    public static final Registration<Item> RAW_BACON = registerFood("raw_bacon", ModFoods.RAW_BACON);
    public static final Registration<Item> RAW_CHICKEN_WINGS = registerFood("raw_chicken_wings", ModFoods.RAW_CHICKEN_WINGS);
    public static final Registration<Item> RAW_SAUSAGE = registerFood("raw_sausage", ModFoods.RAW_SAUSAGE);
    public static final Registration<Item> COOKED_BACON = registerFood("cooked_bacon", ModFoods.COOKED_BACON);
    public static final Registration<Item> COOKED_CHICKEN_WINGS = registerFood("cooked_chicken_wings", ModFoods.COOKED_CHICKEN_WINGS);
    public static final Registration<Item> COOKED_EGG = registerFood("cooked_egg", ModFoods.COOKED_EGG);
    public static final Registration<Item> COOKED_SAUSAGE = registerFood("cooked_sausage", ModFoods.COOKED_SAUSAGE);

    // Curry
    public static final Registration<Item> BEEF_CURRY = registerFood("beef_curry", ModFoods.BEEF_CURRY);
    public static final Registration<Item> CHICKEN_CURRY = registerFood("chicken_curry", ModFoods.CHICKEN_CURRY);
    public static final Registration<Item> MUTTON_CURRY = registerFood("mutton_curry", ModFoods.MUTTON_CURRY);
    public static final Registration<Item> PORK_CURRY = registerFood("pork_curry", ModFoods.PORK_CURRY);
    public static final Registration<Item> VEGETABLE_CURRY = registerFood("vegetable_curry", ModFoods.VEGETABLE_CURRY, 1.0F);

    // Dishes
    public static final Registration<Item> BEEF_AND_BROCCOLI = registerFood("beef_and_broccoli", ModFoods.BEEF_AND_BROCCOLI);
    public static final Registration<Item> CHICORY_GRATIN = registerFood("chicory_gratin", ModFoods.CHICORY_GRATIN, 1.0F);
    public static final Registration<Item> FISH_AND_CHIPS = registerFood("fish_and_chips", ModFoods.FISH_AND_CHIPS);
    public static final Registration<Item> FRIED_RICE = registerFood("fried_rice", ModFoods.FRIED_RICE, 1.0F);
    public static final Registration<Item> MAC_AND_CHEESE = registerFood("mac_and_cheese", ModFoods.MAC_AND_CHEESE, 1.0F);
    public static final Registration<Item> MUSHROOM_BARLEY = registerFood("mushroom_barley", ModFoods.MUSHROOM_BARLEY, 1.0F);
    public static final Registration<Item> PAD_THAI = registerFood("pad_thai", ModFoods.PAD_THAI, 1.0F);
    public static final Registration<Item> PASTA = registerFood("pasta", ModFoods.PASTA, 1.0F);
    public static final Registration<Item> SPAGHETTI = registerFood("spaghetti", ModFoods.SPAGHETTI, 1.0F);
    public static final Registration<Item> SPINACH_MUSHROOM_QUICHE = registerFood("spinach_mushroom_quiche", ModFoods.SPINACH_MUSHROOM_QUICHE, 1.0F);
    public static final Registration<Item> SPINACH_QUINOA_QUICHE = registerFood("spinach_quinoa_quiche", ModFoods.SPINACH_QUINOA_QUICHE, 1.0F);
    public static final Registration<Item> SQUASH_CASSEROLE = registerFood("squash_casserole", ModFoods.SQUASH_CASSEROLE, 1.0F);
    public static final Registration<Item> TOFU_SCRAMBLE = registerFood("tofu_scramble", ModFoods.TOFU_SCRAMBLE, 1.0F);
    public static final Registration<Item> TURNIP_BEETROOT_GRATIN = registerFood("turnip_beetroot_gratin", ModFoods.TURNIP_BEETROOT_GRATIN, 1.0F);

    // Misc.
    public static final Registration<Item> BAKED_SWEET_POTATO = registerFood("baked_sweet_potato", ModFoods.BAKED_SWEET_POTATO, 0.85F);
    public static final Registration<Item> BAKED_YAM = registerFood("baked_yam", Foods.BAKED_POTATO, 0.85f);
    public static final Registration<Item> CHICKEN_PARMESAN = registerFood("chicken_parmesan", ModFoods.CHICKEN_PARMESAN);
    public static final Registration<Item> EGGPLANT_PARMESAN = registerFood("eggplant_parmesan", ModFoods.EGGPLANT_PARMESAN, 1.0F);
    public static final Registration<Item> FISH_FILLET = registerFood("fish_fillet", ModFoods.FISH_FILLET);
    public static final Registration<Item> GOLDEN_HABANERO = registerGoldenHabanero("golden_habanero", ModFoods.GOLDEN_HABANERO);
    public static final Registration<Item> LASAGNA = registerFood("lasagna", ModFoods.LASAGNA);
    public static final Registration<Item> PANCAKES = registerFood("pancakes", ModFoods.PANCAKES, 1.0F);
    public static final Registration<Item> PICKLE = registerFood("pickle", ModFoods.PICKLE, 1.0F);
    public static final Registration<Item> PICKLED_BEETROOT = registerFood("pickled_beetroot", ModFoods.PICKLED_BEETROOT, 1.0F);
    public static final Registration<Item> PIZZA = registerFood("pizza", ModFoods.PIZZA, 1.0F);
    public static final Registration<Item> POPCORN = registerFood("popcorn", ModFoods.POPCORN, 1.0F);
    public static final Registration<Item> POTATO_KNISH = registerFood("potato_knish", ModFoods.POTATO_KNISH, 1.0F);
    public static final Registration<Item> RAISINS = registerFood("raisins", ModFoods.RAISINS, 1.0F);
    public static final Registration<Item> STUFFED_CORN_ZUCCHINI = registerFood("stuffed_corn_zucchini", ModFoods.STUFFED_CORN_ZUCCHINI, 1.0F);
    public static final Registration<Item> SUSHI = registerFood("sushi", ModFoods.SUSHI);
    public static final Registration<Item> SWEET_POTATO_QUINOA_CAKES = registerFood("sweet_potato_quinoa_cakes", ModFoods.SWEET_POTATO_QUINOA_CAKES, 1.0F);
    public static final Registration<Item> TOFU = registerFood("tofu", ModFoods.TOFU, 0.85F);
    public static final Registration<Item> TRAIL_MIX = registerFood("trail_mix", ModFoods.TRAIL_MIX, 1.0F);

    // Desserts
    public static final Registration<Item> CANDY = registerFood("candy", ModFoods.CANDY, 1.0F);
    public static final Registration<Item> CANDY_CANE = registerFood("candy_cane", ModFoods.CANDY_CANE, 1.0F);
    public static final Registration<Item> CASSAVA_CAKE = registerFood("cassava_cake", ModFoods.CASSAVA_CAKE, 1.0F);
    public static final Registration<Item> CHOCOLATE = registerFood("chocolate", ModFoods.CHOCOLATE, 1.0F);
    public static final Registration<Item> ICE_CREAM_SUNDAE = registerFood("ice_cream_sundae", ModFoods.ICE_CREAM_SUNDAE, 1.0F);
    public static final Registration<Item> JAFFA_CAKE = registerFood("jaffa_cake", ModFoods.JAFFA_CAKE, 1.0F);
    public static final Registration<Item> MARSHMALLOW = registerFood("marshmallow", ModFoods.MARSHMALLOW, 1.0F);
    public static final Registration<Item> PEANUT_BUTTER_COOKIE = registerFood("peanut_butter_cookie", ModFoods.PEANUT_BUTTER_COOKIE, 1.0F);


    private static Registration<Item> registerItem(String name) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new Item(new Item.Properties()));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerItem(String name, float compostChance) {
        final var ITEM = registerItem(name);
        COMPOSTABLES.put(ITEM, compostChance);
        return ITEM;
    }

    private static Registration<Item> registerFood(String name, FoodProperties pFood) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new Item(new Item.Properties().food(pFood)));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerFood(String name, FoodProperties pFood, float compostChance) {
        final var ITEM = registerFood(name, pFood);
        COMPOSTABLES.put(ITEM, compostChance);
        return ITEM;
    }

    private static Registration<Item> registerGoldenHabanero(String name, FoodProperties pFood) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.RARE).food(pFood)));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerBowlFood(String name, FoodProperties pFood) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new Item(
            new Item.Properties().stacksTo(1).food(pFood)
        ));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerSeed(String name, Supplier<Block> crop) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new SeedItem(crop.get(), new Item.Properties()));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 0.3F);
        SEEDS.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerEdibleSeed(String name, Supplier<Block> flower) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new ItemNameBlockItem(flower.get(), new Item.Properties()
            .food(ModFoods.SEEDS)
        ));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 0.3F);
        return ITEM;
    }

    private static Registration<Item> registerGrainCropItem(String name) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new GrainCropItem(new Item.Properties()));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 0.65F);
        return ITEM;
    }

    private static Registration<Item> registerGrainCropItem(String name, FoodProperties pFood) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new GrainCropItem(new Item.Properties().food(pFood)));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 0.65F);
        return ITEM;
    }

    private static Registration<Item> registerRootCropItem(String name, FoodProperties pFood) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new RootCropItem(new Item.Properties().food(pFood)));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 0.65F);
        return ITEM;
    }

    private static Registration<Item> registerBlockItem(String name, Supplier<Block> block) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerBlockItem(String name, Supplier<Block> block, float compostChance) {
        final var ITEM = registerBlockItem(name, block);
        COMPOSTABLES.put(ITEM, compostChance);
        return ITEM;
    }

    private static Registration<Item> registerCakeLike(String name, Supplier<Block> block) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(1)));
        ITEM_LIST.add(ITEM);
        COMPOSTABLES.put(ITEM, 1.0F);
        return ITEM;
    }

    private static Registration<Item> registerAlcohol(String name, Holder<MobEffect> effect) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new AlcoholItem(new Item.Properties().stacksTo(1), effect));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerScythe(String name, Tier tier) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new ScytheItem(tier, 2, -3.0F, new Item.Properties()));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerMusicDisc(String name, int tickLength) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new Item(
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(NOURISH_SONG))
        );
        ITEM_LIST.add(ITEM);
        return ITEM;
    }

    private static Registration<Item> registerSeedPacket(String name, SeedPacketItem.Type type) {
        final Registration<Item> ITEM = ITEMS.register(name, () -> new SeedPacketItem(new Item.Properties(), type));
        ITEM_LIST.add(ITEM);
        return ITEM;
    }
}
