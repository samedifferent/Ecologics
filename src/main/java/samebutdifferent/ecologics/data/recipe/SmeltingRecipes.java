package samebutdifferent.ecologics.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import samebutdifferent.ecologics.Ecologics;

import java.util.function.Consumer;

public class SmeltingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {

    }

    private static void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = new ResourceLocation(Ecologics.MOD_ID, name).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
                        result, experience, 200)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, namePrefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, namePrefix + "_from_smoking");
    }
}
