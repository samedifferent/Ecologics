package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
       /* woodFromLogs(consumer, ModBlocks.COCONUT_WOOD.get(), ModBlocks.COCONUT_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_LOG.get());
        planksFromLogsTag(consumer, ModBlocks.COCONUT_PLANKS.get(), ModTags.Items.COCONUT_LOGS);
        slab(consumer, ModBlocks.COCONUT_SLAB.get(), ModBlocks.COCONUT_PLANKS.get());
        stair(consumer, ModBlocks.COCONUT_STAIRS.get(), ModBlocks.COCONUT_PLANKS.get());
        fence(consumer, ModBlocks.COCONUT_FENCE.get(), ModBlocks.COCONUT_PLANKS.get());
        fenceGate(consumer, ModBlocks.COCONUT_FENCE_GATE.get(), ModBlocks.COCONUT_PLANKS.get());
        door(consumer, ModBlocks.COCONUT_DOOR.get(), ModBlocks.COCONUT_PLANKS.get());
        trapdoor(consumer, ModBlocks.COCONUT_TRAPDOOR.get(), ModBlocks.COCONUT_PLANKS.get());
        button(consumer, ModBlocks.COCONUT_BUTTON.get(), ModBlocks.COCONUT_PLANKS.get());
        pressurePlate(consumer, ModBlocks.COCONUT_PRESSURE_PLATE.get(), ModBlocks.COCONUT_PLANKS.get());
        cookRecipes(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRAB_CLAW.get()), ModItems.CRAB_MEAT.get(), 0.35F, 200).unlockedBy("has_crab_claw", has(ModItems.CRAB_CLAW.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.TROPICAL_STEW.get()).requires(ModItems.COCONUT_SLICE.get()).requires(ModItems.CRAB_MEAT.get()).unlockedBy("has_cooked_claw", has(ModItems.CRAB_MEAT.get())).save(consumer);
*/
//        ShapedRecipeBuilder.shaped(ModBlocks.SANDCASTLE.get()).define('A', Blocks.SAND).define('B', ModBlocks.SEASHELL.get()).define('C', Items.STICK).pattern(" C ").pattern("ABA").pattern("AAA").unlockedBy(getHasName(ModBlocks.SEASHELL.get()), has(ModBlocks.SEASHELL.get())).save(consumer);
/*        nineBlockStorageRecipes(consumer, ModBlocks.SEASHELL.get(), ModBlocks.SEASHELL_BLOCK.get());
        polished(consumer, ModBlocks.SEASHELL_TILES.get(), ModBlocks.SEASHELL.get());
        stair(consumer, ModBlocks.SEASHELL_TILE_STAIRS.get(), ModBlocks.SEASHELL_TILES.get());
        stonecutting(consumer, ModBlocks.SEASHELL_TILE_STAIRS.get(), ModBlocks.SEASHELL_TILES.get());
        slab(consumer, ModBlocks.SEASHELL_TILE_SLAB.get(), ModBlocks.SEASHELL_TILES.get());
        stonecutting(consumer, ModBlocks.SEASHELL_TILE_SLAB.get(), ModBlocks.SEASHELL_TILES.get());
        wall(consumer, ModBlocks.SEASHELL_TILE_WALL.get(), ModBlocks.SEASHELL_TILES.get());
        stonecutting(consumer, ModBlocks.SEASHELL_TILE_WALL.get(), ModBlocks.SEASHELL_TILES.get());*/
//        cookRecipes(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, ModItems.PRICKLY_PEAR.get(), ModItems.COOKED_PRICKLY_PEAR.get());
//        cookRecipes(consumer, "campfire_cooking", RecipeSerializer.SMOKING_RECIPE, 600, ModItems.PRICKLY_PEAR.get(), ModItems.COOKED_PRICKLY_PEAR.get());
//        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.PRICKLY_PEAR.get()), ModItems.COOKED_PRICKLY_PEAR.get(), 0.35F, 200).unlockedBy("has_prickly_pear", has(ModItems.PRICKLY_PEAR.get())).save(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.POT.get()).define('#', ItemTags.TERRACOTTA).pattern("# #").pattern("# #").pattern("###").unlockedBy("has_terracotta", has(ItemTags.TERRACOTTA)).save(consumer);
/*        polished(consumer, ModBlocks.SNOW_BRICKS.get(), Blocks.SNOW_BLOCK);
        slab(consumer, ModBlocks.SNOW_BRICK_SLAB.get(), ModBlocks.SNOW_BRICKS.get());
        stonecutting(consumer, ModBlocks.SNOW_BRICK_SLAB.get(), ModBlocks.SNOW_BRICKS.get());
        stair(consumer, ModBlocks.SNOW_BRICK_STAIRS.get(), ModBlocks.SNOW_BRICKS.get());
        stonecutting(consumer, ModBlocks.SNOW_BRICK_STAIRS.get(), ModBlocks.SNOW_BRICKS.get());
        wall(consumer, ModBlocks.SNOW_BRICK_WALL.get(), ModBlocks.SNOW_BRICKS.get());
        stonecutting(consumer, ModBlocks.SNOW_BRICK_WALL.get(), ModBlocks.SNOW_BRICKS.get());
        polished(consumer, ModBlocks.ICE_BRICKS.get(), Blocks.PACKED_ICE);
        slab(consumer, ModBlocks.ICE_BRICK_SLAB.get(), ModBlocks.ICE_BRICKS.get());
        stonecutting(consumer, ModBlocks.ICE_BRICK_SLAB.get(), ModBlocks.ICE_BRICKS.get());
        stair(consumer, ModBlocks.ICE_BRICK_STAIRS.get(), ModBlocks.ICE_BRICKS.get());
        stonecutting(consumer, ModBlocks.ICE_BRICK_STAIRS.get(), ModBlocks.ICE_BRICKS.get());
        wall(consumer, ModBlocks.ICE_BRICK_WALL.get(), ModBlocks.ICE_BRICKS.get());
        stonecutting(consumer, ModBlocks.ICE_BRICK_WALL.get(), ModBlocks.ICE_BRICKS.get());*/
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked) {
        nineBlockStorageRecipes(pFinishedRecipeConsumer, pUnpacked, pPacked, pPacked.asItem().getRegistryName().getPath(), null, pUnpacked.asItem().getRegistryName().getPath(), null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, @Nullable String pPackingRecipeGroup, String pUnpackingRecipeName, @Nullable String pUnpackingRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpacked, 9).requires(pPacked).group(pUnpackingRecipeGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pUnpackingRecipeName));
        ShapedRecipeBuilder.shaped(pPacked).define('#', pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackingRecipeGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pPackingRecipeName));
    }

    private static void stonecutting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pResult, 1).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, getConversionRecipeName(pResult, pMaterial) + "_stonecutting");
    }

    protected static String getConversionRecipeName(ItemLike pResult, ItemLike pIngredient) {
        return pResult.asItem().getRegistryName().getPath() + "_from_" + pIngredient.asItem().getRegistryName().getPath();
    }

    private static void cookRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pCookingMethod, SimpleCookingSerializer<?> pCookingSerializer, int pCookingTime, Item ingredient, Item output) {
        simpleCookingRecipe(pFinishedRecipeConsumer, pCookingMethod, pCookingSerializer, pCookingTime, ingredient, output, 0.35F);
    }

    protected static void simpleCookingRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pCookingMethod, SimpleCookingSerializer<?> pCookingSerializer, int pCookingTime, ItemLike pIngredient, ItemLike pResult, float pExperience) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(pIngredient), pResult, pExperience, pCookingTime, pCookingSerializer).unlockedBy(getHasName(pIngredient), has(pIngredient)).save(pFinishedRecipeConsumer, getHasName(pResult) + "_from_" + pCookingMethod);
    }

    protected static void woodFromLogs(Consumer<FinishedRecipe> consumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(wood, 3).define('#', log).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(log)).save(consumer);
    }

    private static void button(Consumer<FinishedRecipe> consumer, ItemLike button, ItemLike planks) {
        buttonBuilder(button, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder buttonBuilder(ItemLike button, Ingredient planks) {
        return ShapelessRecipeBuilder.shapeless(button).requires(planks);
    }

    private static void door(Consumer<FinishedRecipe> consumer, ItemLike door, ItemLike planks) {
        doorBuilder(door, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder doorBuilder(ItemLike door, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(door, 3).define('#', planks).pattern("##").pattern("##").pattern("##");
    }

    private static void fence(Consumer<FinishedRecipe> consumer, ItemLike fence, ItemLike planks) {
        fenceBuilder(fence, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder fenceBuilder(ItemLike fence, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(fence, 3).define('W', planks).define('#', Items.STICK).pattern("W#W").pattern("W#W");
    }

    private static void fenceGate(Consumer<FinishedRecipe> consumer, ItemLike fenceGate, ItemLike planks) {
        fenceGateBuilder(fenceGate, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder fenceGateBuilder(ItemLike fenceGate, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(fenceGate).define('#', Items.STICK).define('W', planks).pattern("#W#").pattern("#W#");
    }

    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, ItemLike pressurePlate, ItemLike planks) {
        pressurePlateBuilder(pressurePlate, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder pressurePlateBuilder(ItemLike pressurePlate, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(pressurePlate).define('#', planks).pattern("##");
    }

    protected static void slab(Consumer<FinishedRecipe> consumer, ItemLike slab, ItemLike planks) {
        slabBuilder(slab, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder slabBuilder(ItemLike slab, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(slab, 6).define('#', planks).pattern("###");
    }

    private static void stair(Consumer<FinishedRecipe> consumer, ItemLike stair, ItemLike planks) {
        stairBuilder(stair, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder stairBuilder(ItemLike stairs, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(stairs, 4).define('#', planks).pattern("#  ").pattern("## ").pattern("###");
    }

    private static void trapdoor(Consumer<FinishedRecipe> consumer, ItemLike trapdoor, ItemLike planks) {
        trapdoorBuilder(trapdoor, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    protected static RecipeBuilder trapdoorBuilder(ItemLike trapdoor, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(trapdoor, 2).define('#', planks).pattern("###").pattern("###");
    }

    protected static String getHasName(ItemLike item) {
        return "has_" + item.asItem().getRegistryName().getPath();
    }
}
