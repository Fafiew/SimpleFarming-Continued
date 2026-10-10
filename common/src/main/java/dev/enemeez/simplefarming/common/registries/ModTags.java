package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.SimpleFarming;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> MINEABLE_WITH_SCYTHE = TagKey.create(Registries.BLOCK,
        SimpleFarming.id("mineable/scythe"));
    public static final TagKey<Block> TRELLIS_BLACKLIST = TagKey.create(Registries.BLOCK,
        SimpleFarming.id("trellis_blacklist"));
    public static final TagKey<Item> FERMENTABLE = TagKey.create(Registries.ITEM,
        SimpleFarming.id("fermentable"));
}
