package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.item.crafting.FermenterRecipe;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipes {
    public static final ModRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = new ModRegistry<>(Registries.RECIPE_SERIALIZER);

    public static final Registration<RecipeSerializer<FermenterRecipe>> FERMENTING_SERIALIZER = RECIPE_SERIALIZERS.register(
        FermenterRecipe.Type.ID, () -> FermenterRecipe.Serializer.INSTANCE
    );
}
