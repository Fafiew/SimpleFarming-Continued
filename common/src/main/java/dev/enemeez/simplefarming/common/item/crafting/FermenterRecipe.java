package dev.enemeez.simplefarming.common.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class FermenterRecipe implements Recipe<RecipeInput> {

    public static class Type implements RecipeType<FermenterRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "fermenting";
    }

    public static class Serializer implements RecipeSerializer<FermenterRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public MapCodec<FermenterRecipe> codec() {
            return FermenterRecipe.CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FermenterRecipe> streamCodec() {
            return FermenterRecipe.STREAM_CODEC;
        }
    }

    public static final MapCodec<FermenterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->
        inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(r -> r.INGREDIENT),
            ItemStack.CODEC.fieldOf("result").forGetter(r -> r.RESULT)
        ).apply(inst, FermenterRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FermenterRecipe> STREAM_CODEC = StreamCodec.composite(
        Ingredient.CONTENTS_STREAM_CODEC, r -> r.INGREDIENT,
        ItemStack.STREAM_CODEC, r -> r.RESULT,
        FermenterRecipe::new
    );

    private final Ingredient INGREDIENT;
    private final ItemStack RESULT;

    public FermenterRecipe(Ingredient ingredient, ItemStack result) {
        this.RESULT = result;
        this.INGREDIENT = ingredient;
    }

    @Override
    public boolean matches(RecipeInput pContainer, Level pLevel) {
        return this.INGREDIENT.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeInput pContainer, HolderLookup.Provider access) {
        return RESULT.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider access) {
        return RESULT;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(null, INGREDIENT, Ingredient.of(Items.GLASS_BOTTLE));
    }
}
