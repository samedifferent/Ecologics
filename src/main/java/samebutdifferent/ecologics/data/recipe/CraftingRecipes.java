package samebutdifferent.ecologics.data.recipe;

import com.google.common.collect.ImmutableMap;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.data.ModBlockFamilies;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModTags;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CraftingRecipes {
    protected static final Map<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> shapeBuilders = ImmutableMap.<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder().put(BlockFamily.Variant.BUTTON, (p_176733_, p_176734_) -> {
        return buttonBuilder(p_176733_, Ingredient.of(p_176734_));
    }).put(BlockFamily.Variant.DOOR, (p_176714_, p_176715_) -> {
        return doorBuilder(p_176714_, Ingredient.of(p_176715_));
    }).put(BlockFamily.Variant.FENCE, (p_176708_, p_176709_) -> {
        return fenceBuilder(p_176708_, Ingredient.of(p_176709_));
    }).put(BlockFamily.Variant.FENCE_GATE, (p_176698_, p_176699_) -> {
        return fenceGateBuilder(p_176698_, Ingredient.of(p_176699_));
    }).put(BlockFamily.Variant.SIGN, (p_176688_, p_176689_) -> {
        return signBuilder(p_176688_, Ingredient.of(p_176689_));
    }).put(BlockFamily.Variant.SLAB, (p_176682_, p_176683_) -> {
        return slabBuilder(p_176682_, Ingredient.of(p_176683_));
    }).put(BlockFamily.Variant.STAIRS, (p_176674_, p_176675_) -> {
        return stairBuilder(p_176674_, Ingredient.of(p_176675_));
    }).put(BlockFamily.Variant.PRESSURE_PLATE, (p_176662_, p_176663_) -> {
        return pressurePlateBuilder(p_176662_, Ingredient.of(p_176663_));
    }).put(BlockFamily.Variant.POLISHED, (p_176650_, p_176651_) -> {
        return polishedBuilder(p_176650_, Ingredient.of(p_176651_));
    }).put(BlockFamily.Variant.TRAPDOOR, (p_176638_, p_176639_) -> {
        return trapdoorBuilder(p_176638_, Ingredient.of(p_176639_));
    }).put(BlockFamily.Variant.WALL, (p_176608_, p_176609_) -> {
        return wallBuilder(p_176608_, Ingredient.of(p_176609_));
    }).build();

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) -> {
            generateRecipes(pFinishedRecipeConsumer, family);
        });

        planksFromLogs(pFinishedRecipeConsumer, ModBlocks.COCONUT_PLANKS.get(), ModTags.ModItemTags.COCONUT_LOGS);
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.COCONUT_WOOD.get(), ModBlocks.COCONUT_LOG.get());
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.STRIPPED_COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_LOG.get());
        woodenBoat(pFinishedRecipeConsumer, ModItems.COCONUT_BOAT.get(), ModBlocks.COCONUT_PLANKS.get());
        chestBoat(pFinishedRecipeConsumer, ModItems.COCONUT_CHEST_BOAT.get(), ModItems.COCONUT_BOAT.get());

        planksFromLogs(pFinishedRecipeConsumer, ModBlocks.WALNUT_PLANKS.get(), ModTags.ModItemTags.WALNUT_LOGS);
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.WALNUT_WOOD.get(), ModBlocks.WALNUT_LOG.get());
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.STRIPPED_WALNUT_WOOD.get(), ModBlocks.STRIPPED_WALNUT_LOG.get());
        woodenBoat(pFinishedRecipeConsumer, ModItems.WALNUT_BOAT.get(), ModBlocks.WALNUT_PLANKS.get());
        chestBoat(pFinishedRecipeConsumer, ModItems.WALNUT_CHEST_BOAT.get(), ModItems.WALNUT_BOAT.get());

        planksFromLogs(pFinishedRecipeConsumer, ModBlocks.AZALEA_PLANKS.get(), ModTags.ModItemTags.AZALEA_LOGS);
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.AZALEA_WOOD.get(), ModBlocks.AZALEA_LOG.get());
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.STRIPPED_AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_LOG.get());
        woodenBoat(pFinishedRecipeConsumer, ModItems.AZALEA_BOAT.get(), ModBlocks.AZALEA_PLANKS.get());
        chestBoat(pFinishedRecipeConsumer, ModItems.AZALEA_CHEST_BOAT.get(), ModItems.AZALEA_BOAT.get());

        planksFromLogs(pFinishedRecipeConsumer, ModBlocks.FLOWERING_AZALEA_PLANKS.get(), ModTags.ModItemTags.FLOWERING_AZALEA_LOGS);
        woodFromLogs(pFinishedRecipeConsumer, ModBlocks.FLOWERING_AZALEA_WOOD.get(), ModBlocks.FLOWERING_AZALEA_LOG.get());
        woodenBoat(pFinishedRecipeConsumer, ModItems.FLOWERING_AZALEA_BOAT.get(), ModBlocks.FLOWERING_AZALEA_PLANKS.get());
        chestBoat(pFinishedRecipeConsumer, ModItems.FLOWERING_AZALEA_CHEST_BOAT.get(), ModItems.FLOWERING_AZALEA_BOAT.get());

        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SEASHELL_TILE_STAIRS.get(), ModBlocks.SEASHELL_TILES.get());
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SEASHELL_TILE_SLAB.get(), ModBlocks.SEASHELL_TILES.get(), 2);
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SEASHELL_TILE_WALL.get(), ModBlocks.SEASHELL_TILES.get());

        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.ICE_BRICK_STAIRS.get(), ModBlocks.ICE_BRICKS.get());
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.ICE_BRICK_SLAB.get(), ModBlocks.ICE_BRICKS.get(), 2);
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.ICE_BRICK_WALL.get(), ModBlocks.ICE_BRICKS.get());

        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SNOW_BRICK_STAIRS.get(), ModBlocks.SNOW_BRICKS.get());
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SNOW_BRICK_SLAB.get(), ModBlocks.SNOW_BRICKS.get(), 2);
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.SNOW_BRICK_WALL.get(), ModBlocks.SNOW_BRICKS.get());

        nineBlockStorageRecipes(pFinishedRecipeConsumer, ModBlocks.SEASHELL.get(), ModBlocks.SEASHELL_BLOCK.get());
        nineBlockStorageRecipes(pFinishedRecipeConsumer, ModBlocks.SURFACE_MOSS.get(), Blocks.MOSS_BLOCK);

        ShapelessRecipeBuilder.shapeless(ModItems.TROPICAL_STEW.get(), 1)
                .requires(ModItems.COCONUT_SLICE.get())
                .requires(ModItems.CRAB_MEAT.get())
                .unlockedBy("has_crab_meat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRAB_MEAT.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.POT.get()).define('#', ItemTags.TERRACOTTA).pattern("# #").pattern("# #").pattern("###").unlockedBy("has_terracotta", has(ItemTags.TERRACOTTA)).save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModBlocks.SANDCASTLE.get()).define('A', Items.SAND).define('B', ModBlocks.SEASHELL.get()).define('C', Items.STICK).pattern(" C ").pattern("ABA").pattern("AAA").unlockedBy("has_seashell", has(ModBlocks.SEASHELL.get())).save(pFinishedRecipeConsumer);
    }

    protected static void generateRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, BlockFamily pFamily) {
        pFamily.getVariants().forEach((variant, block) -> {
            BiFunction<ItemLike, ItemLike, RecipeBuilder> bifunction = shapeBuilders.get(variant);
            ItemLike itemlike = pFamily.getBaseBlock();
            if (bifunction != null) {
                RecipeBuilder recipebuilder = bifunction.apply(block, itemlike);
                pFamily.getRecipeGroupPrefix().ifPresent((p_176601_) -> {
                    recipebuilder.group(p_176601_ + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getName()));
                });
                recipebuilder.unlockedBy(pFamily.getRecipeUnlockedBy().orElseGet(() -> {
                    return getHasName(itemlike);
                }), has(itemlike));
                recipebuilder.save(pFinishedRecipeConsumer, getDefaultRecipeId(block));
            }
        });
    }

    protected static void planksFromLogs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pPlanks, TagKey<Item> pLogs) {
        ShapelessRecipeBuilder.shapeless(pPlanks, 4).requires(pLogs).group("planks").unlockedBy("has_logs", has(pLogs)).save(pFinishedRecipeConsumer);
    }

    protected static void woodFromLogs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pWood, ItemLike pLog) {
        ShapedRecipeBuilder.shaped(pWood, 3).define('#', pLog).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(pLog)).save(pFinishedRecipeConsumer);
    }

    protected static void woodenBoat(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pBoat, ItemLike pMaterial) {
        ShapedRecipeBuilder.shaped(pBoat).define('#', pMaterial).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(pFinishedRecipeConsumer);
    }

    private static void chestBoat(Consumer<FinishedRecipe> p_236372_, ItemLike p_236373_, ItemLike p_236374_) {
        ShapelessRecipeBuilder.shapeless(p_236373_).requires(Blocks.CHEST).requires(p_236374_).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(p_236372_);
    }

    protected static RecipeBuilder buttonBuilder(ItemLike pButton, Ingredient pMaterial) {
        return ShapelessRecipeBuilder.shapeless(pButton).requires(pMaterial);
    }

    protected static RecipeBuilder doorBuilder(ItemLike pDoor, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pDoor, 3).define('#', pMaterial).pattern("##").pattern("##").pattern("##");
    }

    protected static RecipeBuilder fenceBuilder(ItemLike pFence, Ingredient pMaterial) {
        int i = pFence == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = pFence == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : Items.STICK;
        return ShapedRecipeBuilder.shaped(pFence, i).define('W', pMaterial).define('#', item).pattern("W#W").pattern("W#W");
    }

    protected static RecipeBuilder fenceGateBuilder(ItemLike pFenceGate, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pFenceGate).define('#', Items.STICK).define('W', pMaterial).pattern("#W#").pattern("#W#");
    }

    protected static void pressurePlate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pPressurePlate, ItemLike pMaterial) {
        pressurePlateBuilder(pPressurePlate, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    protected static RecipeBuilder pressurePlateBuilder(ItemLike pPressurePlate, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pPressurePlate).define('#', pMaterial).pattern("##");
    }

    protected static void slab(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pSlab, ItemLike pMaterial) {
        slabBuilder(pSlab, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    protected static RecipeBuilder slabBuilder(ItemLike pSlab, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pSlab, 6).define('#', pMaterial).pattern("###");
    }

    protected static RecipeBuilder stairBuilder(ItemLike pStairs, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pStairs, 4).define('#', pMaterial).pattern("#  ").pattern("## ").pattern("###");
    }

    protected static RecipeBuilder trapdoorBuilder(ItemLike pTrapdoor, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pTrapdoor, 2).define('#', pMaterial).pattern("###").pattern("###");
    }

    protected static RecipeBuilder signBuilder(ItemLike pSign, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pSign, 3).group("sign").define('#', pMaterial).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ");
    }

    protected static EnterBlockTrigger.TriggerInstance insideOf(Block pBlock) {
        return new EnterBlockTrigger.TriggerInstance(EntityPredicate.Composite.ANY, pBlock, StatePropertiesPredicate.ANY);
    }

    public static void wall(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pWall, ItemLike pMaterial) {
        wallBuilder(pWall, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    public static RecipeBuilder wallBuilder(ItemLike pWall, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pWall, 6).define('#', pMaterial).pattern("###").pattern("###");
    }

    public static void polished(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial) {
        polishedBuilder(pResult, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    public static RecipeBuilder polishedBuilder(ItemLike pResult, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pResult, 4).define('S', pMaterial).pattern("SS").pattern("SS");
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, pResult, pMaterial, 1);
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Ecologics.MOD_ID, getConversionRecipeName(pResult, pMaterial) + "_stonecutting"));
    }

    protected static String getConversionRecipeName(ItemLike pResult, ItemLike pIngredient) {
        return getItemName(pResult) + "_from_" + getItemName(pIngredient);
    }
    
    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked) {
        nineBlockStorageRecipes(pFinishedRecipeConsumer, pUnpacked, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, @Nullable String pPackingRecipeGroup, String pUnpackingRecipeName, @Nullable String pUnpackingRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpacked, 9).requires(pPacked).group(pUnpackingRecipeGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(Ecologics.MOD_ID, pUnpackingRecipeName));
        ShapedRecipeBuilder.shaped(pPacked).define('#', pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackingRecipeGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(Ecologics.MOD_ID, pPackingRecipeName));
    }

    protected static String getSimpleRecipeName(ItemLike pItemLike) {
        return getItemName(pItemLike);
    }

    protected static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    protected static String getItemName(ItemLike pItemLike) {
        return Registry.ITEM.getKey(pItemLike.asItem()).getPath();
    }

    protected static InventoryChangeTrigger.TriggerInstance has(MinMaxBounds.Ints pCount, ItemLike pItem) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItem).withCount(pCount).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> pTag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... pPredicates) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, pPredicates);
    }

    static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return Registry.ITEM.getKey(pItemLike.asItem());
    }
}
