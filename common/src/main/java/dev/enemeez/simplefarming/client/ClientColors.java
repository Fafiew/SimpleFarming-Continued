package dev.enemeez.simplefarming.client;

import dev.enemeez.simplefarming.common.registries.ModBlocks;
import dev.enemeez.simplefarming.common.registries.ModItems;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;

/**
 * The mod's block and item colours, shared by every loader. Each loader feeds the appropriate
 * colour registry from its own colour-registration event, so the {@link BlockColors} /
 * {@link ItemColors} instances come in as parameters rather than from the client singleton.
 */
public final class ClientColors {
    private ClientColors() {
    }

    public static final BlockColor FOLIAGE = (state, level, pos, tintIndex) -> level != null && pos != null
        ? BiomeColors.getAverageFoliageColor(level, pos)
        : FoliageColor.getDefaultColor();

    public static final ItemColor FOLIAGE_ITEM = (stack, tintIndex) -> FoliageColor.getDefaultColor();

    /** Registers every block colour the mod uses. */
    public static void registerBlockColors(BlockColors colors) {
        colors.register(FOLIAGE,
            ModBlocks.FOLIAGE_COLOR_BLOCKS.stream().map(Registration::get).toArray(Block[]::new)
        );

        colors.register((state, level, pos, tintIndex) -> {
                int age = state.getValue(StemBlock.AGE);
                int red = age * 32;
                int green = 255 - age * 8;
                int blue = age * 4;
                return red << 16 | green << 8 | blue;
            }, ModBlocks.CANTALOUPE_STEM.get(), ModBlocks.HONEYDEW_STEM.get(), ModBlocks.SQUASH_STEM.get()
        );

        colors.register((state, level, pos, tintIndex) -> 14_731_036,
            ModBlocks.ATTACHED_CANTALOUPE_STEM.get(), ModBlocks.ATTACHED_HONEYDEW_STEM.get(), ModBlocks.ATTACHED_SQUASH_STEM.get()
        );
    }

    /** Registers every item colour the mod uses. */
    public static void registerItemColors(ItemColors colors) {
        colors.register(FOLIAGE_ITEM,
            ModItems.BLACKBERRY_BUSH.get(), ModItems.BLUEBERRY_BUSH.get(), ModItems.RASPBERRY_BUSH.get(), ModItems.STRAWBERRY_BUSH.get()
        );
    }
}
