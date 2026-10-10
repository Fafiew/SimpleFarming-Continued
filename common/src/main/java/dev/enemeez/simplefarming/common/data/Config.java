package dev.enemeez.simplefarming.common.data;

import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.platform.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Loader-agnostic settings for the nutrition values of the mod's foods.
 *
 * <p>The values live in {@code config/simplefarming.toml}. The file is created with defaults on
 * first launch and re-read on startup; a value that is missing or unparsable falls back to the
 * default. This keeps the settings identical on every loader without depending on a loader-specific
 * configuration API.</p>
 */
public final class Config {
    private Config() {
    }

    /** A single integer setting. */
    public static final class Value {
        private final String key;
        private final int defaultValue;
        private volatile int value;

        private Value(String key, int defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.value = defaultValue;
        }

        public int get() {
            return value;
        }

        public int getDefault() {
            return defaultValue;
        }

        public String key() {
            return key;
        }
    }

    private static final Map<String, Value> VALUES = new LinkedHashMap<>();

    private static final String SECTION = "Hunger values";

    public static final Value berry = new Value("berry", 2);
    public static final Value fruit = new Value("fruit", 4);
    public static final Value gourd_slice = new Value("gourd_slice", 2);
    public static final Value cassava = new Value("cassava", 3);
    public static final Value corn = new Value("corn", 6);
    public static final Value cucumber = new Value("cucumber", 3);
    public static final Value eggplant = new Value("eggplant", 3);
    public static final Value ginger = new Value("ginger", 2);
    public static final Value onion = new Value("onion", 3);
    public static final Value peanut = new Value("peanut", 1);
    public static final Value pepper = new Value("pepper", 2);
    public static final Value pea_pod = new Value("pea_pod", 2);
    public static final Value radish = new Value("radish", 1);
    public static final Value soybean = new Value("soybean", 2);
    public static final Value sorghum = new Value("sorghum", 4);
    public static final Value salad_greens = new Value("salad_greens", 2);
    public static final Value tomato = new Value("tomato", 3);
    public static final Value turnip = new Value("turnip", 2);
    public static final Value sweet_potato = new Value("sweet_potato", 2);
    public static final Value seeds = new Value("seeds", 1);
    public static final Value zucchini = new Value("zucchini", 3);
    public static final Value broccoli = new Value("broccoli", 3);
    public static final Value olives = new Value("olives", 2);
    public static final Value golden_habanero = new Value("golden_habanero", 2);
    public static final Value berry_pie = new Value("berry_pie", 4);
    public static final Value fruit_pie = new Value("fruit_pie", 6);
    public static final Value baked_sweet_potato = new Value("baked_sweet_potato", 6);
    public static final Value banana_bread = new Value("banana_bread", 7);
    public static final Value blt = new Value("blt", 14);
    public static final Value caesar_salad = new Value("caesar_salad", 11);
    public static final Value cassava_cake = new Value("cassava_cake", 5);
    public static final Value cheese_slice = new Value("cheese_slice", 3);
    public static final Value cheese_burger = new Value("cheese_burger", 21);
    public static final Value chicken_parmesan = new Value("chicken_parmesan", 15);
    public static final Value chili = new Value("chili", 13);
    public static final Value chocolate = new Value("chocolate", 3);
    public static final Value cooked_bacon = new Value("cooked_bacon", 4);
    public static final Value cooked_chicken_wings = new Value("cooked_chicken_wings", 3);
    public static final Value cooked_egg = new Value("cooked_egg", 5);
    public static final Value cooked_sausage = new Value("cooked_sausage", 4);
    public static final Value corn_salad = new Value("corn_salad", 9);
    public static final Value corn_bread = new Value("corn_bread", 7);
    public static final Value cucumber_soup = new Value("cucumber_soup", 6);
    public static final Value egg_sandwich = new Value("egg_sandwich", 14);
    public static final Value eggplant_parmesan = new Value("eggplant_parmesan", 16);
    public static final Value fish_fillet = new Value("fish_fillet", 8);
    public static final Value fish_sandwich = new Value("fish_sandwich", 15);
    public static final Value fried_calamari = new Value("fried_calamari", 5);
    public static final Value fruit_salad = new Value("fruit_salad", 8);
    public static final Value hamburger = new Value("hamburger", 18);
    public static final Value hotdog = new Value("hotdog", 9);
    public static final Value jaffa_cake = new Value("jaffa_cake", 8);
    public static final Value lasagna = new Value("lasagna", 24);
    public static final Value mac_and_cheese = new Value("mac_and_cheese", 10);
    public static final Value oatmeal = new Value("oatmeal", 3);
    public static final Value onion_soup = new Value("onion_soup", 6);
    public static final Value pancakes = new Value("pancakes", 4);
    public static final Value pasta = new Value("pasta", 10);
    public static final Value pickle = new Value("pickle", 5);
    public static final Value pickled_beetroot = new Value("pickled_beetroot", 3);
    public static final Value pizza = new Value("pizza", 10);
    public static final Value popcorn = new Value("popcorn", 1);
    public static final Value pulled_pork_sandwich = new Value("pulled_pork_sandwich", 18);
    public static final Value radish_soup = new Value("radish_soup", 6);
    public static final Value raw_bacon = new Value("raw_bacon", 2);
    public static final Value raw_calamari = new Value("raw_calamari", 2);
    public static final Value raw_chicken_wings = new Value("raw_chicken_wings", 1);
    public static final Value raw_sausage = new Value("raw_sausage", 2);
    public static final Value rice_bowl = new Value("rice_bowl", 3);
    public static final Value salad = new Value("salad", 12);
    public static final Value sandwich = new Value("sandwich", 16);
    public static final Value spaghetti = new Value("spaghetti", 10);
    public static final Value spinach_mushroom_quiche = new Value("spinach_mushroom_quiche", 10);
    public static final Value squash_casserole = new Value("squash_casserole", 9);
    public static final Value sushi = new Value("sushi", 8);
    public static final Value tofu = new Value("tofu", 6);
    public static final Value tomato_soup = new Value("tomato_soup", 6);
    public static final Value vegetable_medley = new Value("vegetable_medley", 6);
    public static final Value veggie_burger = new Value("veggie_burger", 16);
    public static final Value tofu_scramble = new Value("tofu_scramble", 12);
    public static final Value raisins = new Value("raisins", 8);
    public static final Value chicken_noodle_soup = new Value("chicken_noodle_soup", 13);
    public static final Value quinoa_salad = new Value("quinoa_salad", 5);
    public static final Value squash_soup = new Value("squash_soup", 12);
    public static final Value spinach_quinoa_quiche = new Value("spinach_quinoa_quiche", 10);
    public static final Value beef_curry = new Value("beef_curry", 18);
    public static final Value chicken_curry = new Value("chicken_curry", 16);
    public static final Value mutton_curry = new Value("mutton_curry", 16);
    public static final Value pork_curry = new Value("pork_curry", 18);
    public static final Value vegetable_curry = new Value("vegetable_curry", 12);
    public static final Value peanut_butter_pie = new Value("peanut_butter_pie", 3);
    public static final Value sorghum_bread = new Value("sorghum_bread", 7);
    public static final Value sorghum_porridge = new Value("sorghum_porridge", 8);
    public static final Value italian_beef = new Value("italian_beef", 18);
    public static final Value peanut_butter_cookie = new Value("peanut_butter_cookie", 2);
    public static final Value pbj = new Value("pbj", 8);
    public static final Value trail_mix = new Value("trail_mix", 12);
    public static final Value carrot_soup = new Value("carrot_soup", 6);
    public static final Value pumpkin_soup = new Value("pumpkin_soup", 6);
    public static final Value pea_soup = new Value("pea_soup", 4);
    public static final Value fried_rice = new Value("fried_rice", 9);
    public static final Value sweet_potato_quinoa_cakes = new Value("sweet_potato_quinoa_cakes", 6);
    public static final Value sausage_barley = new Value("sausage_barley", 10);
    public static final Value mushroom_barley = new Value("mushroom_barley", 4);
    public static final Value beef_and_broccoli = new Value("beef_and_broccoli", 14);
    public static final Value broccoli_cheese_soup = new Value("broccoli_cheese_soup", 6);
    public static final Value stuffed_corn_zucchini = new Value("stuffed_corn_zucchini", 14);
    public static final Value zucchini_bread = new Value("zucchini_bread", 6);
    public static final Value olive_tomato_salad = new Value("olive_tomato_salad", 5);
    public static final Value candy = new Value("candy", 5);
    public static final Value borscht = new Value("borscht", 6);
    public static final Value squid_ink_pasta = new Value("squid_ink_pasta", 12);
    public static final Value fish_and_chips = new Value("fish_and_chips", 9);
    public static final Value marshmallow = new Value("marshmallow", 3);
    public static final Value pad_thai = new Value("pad_thai", 18);
    public static final Value potato_knish = new Value("potato_knish", 8);
    public static final Value chicory_gratin = new Value("chicory_gratin", 9);
    public static final Value turnip_beetroot_gratin = new Value("turnip_beetroot_gratin", 10);
    public static final Value ice_cream_sundae = new Value("ice_cream_sundae", 7);
    public static final Value candy_cane = new Value("candy_cane", 4);

    static {
        register(berry);
        register(fruit);
        register(gourd_slice);
        register(cassava);
        register(corn);
        register(cucumber);
        register(eggplant);
        register(ginger);
        register(onion);
        register(peanut);
        register(pepper);
        register(pea_pod);
        register(radish);
        register(soybean);
        register(sorghum);
        register(salad_greens);
        register(tomato);
        register(turnip);
        register(sweet_potato);
        register(seeds);
        register(zucchini);
        register(broccoli);
        register(olives);
        register(golden_habanero);
        register(berry_pie);
        register(fruit_pie);
        register(baked_sweet_potato);
        register(banana_bread);
        register(blt);
        register(caesar_salad);
        register(cassava_cake);
        register(cheese_slice);
        register(cheese_burger);
        register(chicken_parmesan);
        register(chili);
        register(chocolate);
        register(cooked_bacon);
        register(cooked_chicken_wings);
        register(cooked_egg);
        register(cooked_sausage);
        register(corn_salad);
        register(corn_bread);
        register(cucumber_soup);
        register(egg_sandwich);
        register(eggplant_parmesan);
        register(fish_fillet);
        register(fish_sandwich);
        register(fried_calamari);
        register(fruit_salad);
        register(hamburger);
        register(hotdog);
        register(jaffa_cake);
        register(lasagna);
        register(mac_and_cheese);
        register(oatmeal);
        register(onion_soup);
        register(pancakes);
        register(pasta);
        register(pickle);
        register(pickled_beetroot);
        register(pizza);
        register(popcorn);
        register(pulled_pork_sandwich);
        register(radish_soup);
        register(raw_bacon);
        register(raw_calamari);
        register(raw_chicken_wings);
        register(raw_sausage);
        register(rice_bowl);
        register(salad);
        register(sandwich);
        register(spaghetti);
        register(spinach_mushroom_quiche);
        register(squash_casserole);
        register(sushi);
        register(tofu);
        register(tomato_soup);
        register(vegetable_medley);
        register(veggie_burger);
        register(tofu_scramble);
        register(raisins);
        register(chicken_noodle_soup);
        register(quinoa_salad);
        register(squash_soup);
        register(spinach_quinoa_quiche);
        register(beef_curry);
        register(chicken_curry);
        register(mutton_curry);
        register(pork_curry);
        register(vegetable_curry);
        register(peanut_butter_pie);
        register(sorghum_bread);
        register(sorghum_porridge);
        register(italian_beef);
        register(peanut_butter_cookie);
        register(pbj);
        register(trail_mix);
        register(carrot_soup);
        register(pumpkin_soup);
        register(pea_soup);
        register(fried_rice);
        register(sweet_potato_quinoa_cakes);
        register(sausage_barley);
        register(mushroom_barley);
        register(beef_and_broccoli);
        register(broccoli_cheese_soup);
        register(stuffed_corn_zucchini);
        register(zucchini_bread);
        register(olive_tomato_salad);
        register(candy);
        register(borscht);
        register(squid_ink_pasta);
        register(fish_and_chips);
        register(marshmallow);
        register(pad_thai);
        register(potato_knish);
        register(chicory_gratin);
        register(turnip_beetroot_gratin);
        register(ice_cream_sundae);
        register(candy_cane);
        load();
    }

    private static void register(Value value) {
        VALUES.put(value.key(), value);
    }

    public static List<Value> values() {
        return List.copyOf(VALUES.values());
    }

    private static void load() {
        Path file = Services.platform().configDir().resolve(SimpleFarming.MOD_ID + ".toml");
        try {
            if (Files.notExists(file)) {
                writeDefaults(file);
                return;
            }
            for (String line : Files.readAllLines(file)) {
                parseLine(line);
            }
        } catch (IOException e) {
            SimpleFarming.LOGGER.warn("Could not read {}; using default nutrition values", file, e);
        }
    }

    private static void parseLine(String rawLine) {
        String line = rawLine.trim();
        if (line.isEmpty() || line.startsWith("#") || line.startsWith("[")) {
            return;
        }
        int separator = line.indexOf('=');
        if (separator < 0) {
            return;
        }
        String key = line.substring(0, separator).trim();
        if (key.length() >= 2 && key.startsWith("\"") && key.endsWith("\"")) {
            key = key.substring(1, key.length() - 1);
        }
        String valuePart = line.substring(separator + 1).trim();
        // Strip an inline comment.
        int comment = valuePart.indexOf('#');
        if (comment >= 0) {
            valuePart = valuePart.substring(0, comment).trim();
        }
        Value value = VALUES.get(key);
        if (value == null) {
            return;
        }
        try {
            value.value = Integer.parseInt(valuePart);
        } catch (NumberFormatException ignored) {
            // Keep the default.
        }
    }

    private static void writeDefaults(Path file) throws IOException {
        Files.createDirectories(file.getParent());
        StringBuilder builder = new StringBuilder();
        builder.append("# Simple Farming: Continued nutrition settings.\n");
        builder.append("# Values are the hunger points restored by each food.\n\n");
        builder.append('[').append(SECTION).append("]\n");
        for (Value value : VALUES.values()) {
            builder.append(value.key())
                .append(" = ")
                .append(value.defaultValue)
                .append("\n");
        }
        Files.writeString(file, builder.toString());
    }
}
