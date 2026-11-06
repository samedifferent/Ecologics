package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Quartet;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.config.ConfigCommon;

public class ModCreativeModeTabContents 
{
    public static final ResourceKey<CreativeModeTab> ECOLOGICS_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "tab"));

    public static enum Operation {
        BEFORE,
        AFTER,
        INSERT;
    }
    
    public static final ArrayList<Quartet<ItemLike, ResourceKey<CreativeModeTab>, Operation, @Nullable ItemLike>> TAB_ITEMS = new ArrayList<>();
    
    public static void populateTabDatabase() {
        addToList(ModBlocks.COCONUT_LOG);
        addToList(ModBlocks.COCONUT_WOOD);
        addToList(ModBlocks.STRIPPED_COCONUT_LOG);
        addToList(ModBlocks.STRIPPED_COCONUT_WOOD);
        addToList(ModBlocks.COCONUT_PLANKS);
        addToList(ModBlocks.COCONUT_STAIRS);
        addToList(ModBlocks.COCONUT_SLAB);
        addToList(ModBlocks.COCONUT_FENCE);
        addToList(ModBlocks.COCONUT_FENCE_GATE);
        addToList(ModBlocks.COCONUT_DOOR);
        addToList(ModBlocks.COCONUT_TRAPDOOR);
        addToList(ModBlocks.COCONUT_PRESSURE_PLATE);
        addToList(ModBlocks.COCONUT_BUTTON);

        addToList(ModBlocks.MAPLE_LOG);
        addToList(ModBlocks.SAPPY_MAPLE_LOG);
        addToList(ModBlocks.MAPLE_WOOD);
        addToList(ModBlocks.STRIPPED_MAPLE_LOG);
        addToList(ModBlocks.STRIPPED_MAPLE_WOOD);
        addToList(ModBlocks.MAPLE_PLANKS);
        addToList(ModBlocks.MAPLE_STAIRS);
        addToList(ModBlocks.MAPLE_SLAB);
        addToList(ModBlocks.MAPLE_FENCE);
        addToList(ModBlocks.MAPLE_FENCE_GATE);
        addToList(ModBlocks.MAPLE_DOOR);
        addToList(ModBlocks.MAPLE_TRAPDOOR);
        addToList(ModBlocks.MAPLE_PRESSURE_PLATE);
        addToList(ModBlocks.MAPLE_BUTTON);

        addToList(ModBlocks.WALNUT_LOG);
        addToList(ModBlocks.WALNUT_WOOD);
        addToList(ModBlocks.STRIPPED_WALNUT_LOG);
        addToList(ModBlocks.STRIPPED_WALNUT_WOOD);
        addToList(ModBlocks.WALNUT_PLANKS);
        addToList(ModBlocks.WALNUT_STAIRS);
        addToList(ModBlocks.WALNUT_SLAB);
        addToList(ModBlocks.WALNUT_FENCE);
        addToList(ModBlocks.WALNUT_FENCE_GATE);
        addToList(ModBlocks.WALNUT_DOOR);
        addToList(ModBlocks.WALNUT_TRAPDOOR);
        addToList(ModBlocks.WALNUT_PRESSURE_PLATE);
        addToList(ModBlocks.WALNUT_BUTTON);

        addToList(ModBlocks.AZALEA_LOG);
        addToList(ModBlocks.AZALEA_WOOD);
        addToList(ModBlocks.STRIPPED_AZALEA_LOG);
        addToList(ModBlocks.STRIPPED_AZALEA_WOOD);
        addToList(ModBlocks.AZALEA_PLANKS);
        addToList(ModBlocks.AZALEA_STAIRS);
        addToList(ModBlocks.AZALEA_SLAB);
        addToList(ModBlocks.AZALEA_FENCE);
        addToList(ModBlocks.AZALEA_FENCE_GATE);
        addToList(ModBlocks.AZALEA_DOOR);
        addToList(ModBlocks.AZALEA_TRAPDOOR);
        addToList(ModBlocks.AZALEA_PRESSURE_PLATE);
        addToList(ModBlocks.AZALEA_BUTTON);

        addToList(ModBlocks.FLOWERING_AZALEA_LOG);
        addToList(ModBlocks.FLOWERING_AZALEA_WOOD);
        addToList(ModBlocks.FLOWERING_AZALEA_PLANKS);
        addToList(ModBlocks.FLOWERING_AZALEA_STAIRS);
        addToList(ModBlocks.FLOWERING_AZALEA_SLAB);
        addToList(ModBlocks.FLOWERING_AZALEA_FENCE);
        addToList(ModBlocks.FLOWERING_AZALEA_FENCE_GATE);
        addToList(ModBlocks.FLOWERING_AZALEA_DOOR);
        addToList(ModBlocks.FLOWERING_AZALEA_TRAPDOOR);

        addToList(ModItems.COCONUT_SIGN);
        addToList(ModItems.COCONUT_HANGING_SIGN);
        addToList(ModItems.MAPLE_SIGN);
        addToList(ModItems.MAPLE_HANGING_SIGN);
        addToList(ModItems.WALNUT_SIGN);
        addToList(ModItems.WALNUT_HANGING_SIGN);
        addToList(ModItems.AZALEA_SIGN);
        addToList(ModItems.AZALEA_HANGING_SIGN);
        addToList(ModItems.FLOWERING_AZALEA_SIGN);
        addToList(ModItems.FLOWERING_AZALEA_HANGING_SIGN);

        addToList(ModBlocks.COCONUT_SHELF);
        addToList(ModBlocks.MAPLE_SHELF);
        addToList(ModBlocks.WALNUT_SHELF);
        addToList(ModBlocks.AZALEA_SHELF);

        addToList(ModItems.COCONUT_BOAT);
        addToList(ModItems.COCONUT_CHEST_BOAT);
        addToList(ModItems.MAPLE_BOAT);
        addToList(ModItems.MAPLE_CHEST_BOAT);
        addToList(ModItems.WALNUT_BOAT);
        addToList(ModItems.WALNUT_CHEST_BOAT);
        addToList(ModItems.AZALEA_BOAT);
        addToList(ModItems.AZALEA_CHEST_BOAT);
        addToList(ModItems.FLOWERING_AZALEA_BOAT);
        addToList(ModItems.FLOWERING_AZALEA_CHEST_BOAT);

        addToList(ModBlocks.COCONUT_LEAVES);
        addToList(ModBlocks.WALNUT_LEAVES);
        addToList(ModBlocks.RED_MAPLE_LEAVES);
        addToList(ModBlocks.ORANGE_MAPLE_LEAVES);
        addToList(ModBlocks.YELLOW_MAPLE_LEAVES);
        addToList(ModBlocks.GREEN_MAPLE_LEAVES);
        addToList(ModBlocks.COCONUT_SEEDLING);
        addToList(ModBlocks.MAPLE_SAPLING);
        addToList(ModBlocks.WALNUT_SAPLING);

        addToList(ModBlocks.COCONUT);
        addToList(ModBlocks.SEASHELL);
        addToList(ModBlocks.SEASHELL_BLOCK);
        addToList(ModBlocks.SEASHELL_TILES);
        addToList(ModBlocks.SEASHELL_TILE_STAIRS);
        addToList(ModBlocks.SEASHELL_TILE_SLAB);
        addToList(ModBlocks.SEASHELL_TILE_WALL);
        addToList(ModBlocks.POT);
        addToList(ModBlocks.THIN_ICE);
        addToList(ModBlocks.ICE_BRICKS);
        addToList(ModBlocks.ICE_BRICK_STAIRS);
        addToList(ModBlocks.ICE_BRICK_SLAB);
        addToList(ModBlocks.ICE_BRICK_WALL);
        addToList(ModBlocks.SNOW_BRICKS);
        addToList(ModBlocks.SNOW_BRICK_STAIRS);
        addToList(ModBlocks.SNOW_BRICK_SLAB);
        addToList(ModBlocks.SNOW_BRICK_WALL);

        addToList(ModBlocks.AZALEA_FLOWER);
        addToList(ModBlocks.SURFACE_MOSS);
        addToList(ModBlocks.MOSS_LAYER);
        addToList(ModBlocks.PALE_MOSS_LAYER);
        addToList(ModItems.COCONUT_SLICE);
        addToList(ModItems.COCONUT_HUSK);
        addToList(ModItems.CRAB_CLAW);
        addToList(ModItems.CRAB_MEAT);
        addToList(ModItems.TROPICAL_STEW);
        addToList(ModItems.MAPLE_SAP_BUCKET);
        addToList(ModItems.MAPLE_SYRUP_BOTTLE);
        addToList(ModItems.MAPLE_COOKIE);
        addToList(ModItems.MAPLE_PIE);
        addToList(ModItems.COCONUT_CRAB_SPAWN_EGG);
        addToList(ModItems.PENGUIN_SPAWN_EGG);
        addToList(ModItems.SQUIRREL_SPAWN_EGG);
        addToList(ModBlocks.SANDCASTLE);
        addToList(ModItems.MUSIC_DISC_COCONUT);
        addToList(ModItems.PRICKLY_PEAR);
        addToList(ModItems.COOKED_PRICKLY_PEAR);
        addToList(ModItems.PENGUIN_FEATHER);
        addToList(ModItems.WALNUT);
        addToList(ModBlocks.SPILE);
        
        // Vanilla Tab
        if (ConfigCommon.getUseVanillaTabs()) {
            addToList(ModBlocks.COCONUT_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.BEFORE, Items.BAMBOO_BLOCK);
            addToList(ModBlocks.COCONUT_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_LOG);
            addToList(ModBlocks.STRIPPED_COCONUT_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_WOOD);
            addToList(ModBlocks.STRIPPED_COCONUT_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_COCONUT_LOG);
            addToList(ModBlocks.COCONUT_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_COCONUT_WOOD);
            addToList(ModBlocks.COCONUT_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_PLANKS);
            addToList(ModBlocks.COCONUT_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_STAIRS);
            addToList(ModBlocks.COCONUT_FENCE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_SLAB);
            addToList(ModBlocks.COCONUT_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_FENCE);
            addToList(ModBlocks.COCONUT_DOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_FENCE_GATE);
            addToList(ModBlocks.COCONUT_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_DOOR);
            addToList(ModBlocks.COCONUT_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_TRAPDOOR);
            addToList(ModBlocks.COCONUT_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_PRESSURE_PLATE);
    
            addToList(ModBlocks.MAPLE_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_BUTTON);
            addToList(ModBlocks.MAPLE_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_LOG);
            addToList(ModBlocks.STRIPPED_MAPLE_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_WOOD);
            addToList(ModBlocks.STRIPPED_MAPLE_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_MAPLE_LOG);
            addToList(ModBlocks.MAPLE_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_MAPLE_WOOD);
            addToList(ModBlocks.MAPLE_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_PLANKS);
            addToList(ModBlocks.MAPLE_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_STAIRS);
            addToList(ModBlocks.MAPLE_FENCE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_SLAB);
            addToList(ModBlocks.MAPLE_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_FENCE);
            addToList(ModBlocks.MAPLE_DOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_FENCE_GATE);
            addToList(ModBlocks.MAPLE_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_DOOR);
            addToList(ModBlocks.MAPLE_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_TRAPDOOR);
            addToList(ModBlocks.MAPLE_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_PRESSURE_PLATE);
    
            addToList(ModBlocks.WALNUT_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_BUTTON);
            addToList(ModBlocks.WALNUT_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_LOG);
            addToList(ModBlocks.STRIPPED_WALNUT_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_WOOD);
            addToList(ModBlocks.STRIPPED_WALNUT_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_WALNUT_LOG);
            addToList(ModBlocks.WALNUT_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_WALNUT_WOOD);
            addToList(ModBlocks.WALNUT_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_PLANKS);
            addToList(ModBlocks.WALNUT_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_STAIRS);
            addToList(ModBlocks.WALNUT_FENCE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_SLAB);
            addToList(ModBlocks.WALNUT_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_FENCE);
            addToList(ModBlocks.WALNUT_DOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_FENCE_GATE);
            addToList(ModBlocks.WALNUT_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_DOOR);
            addToList(ModBlocks.WALNUT_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_TRAPDOOR);
            addToList(ModBlocks.WALNUT_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_PRESSURE_PLATE);
    
            addToList(ModBlocks.AZALEA_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_BUTTON);
            addToList(ModBlocks.AZALEA_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_LOG);
            addToList(ModBlocks.STRIPPED_AZALEA_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_WOOD);
            addToList(ModBlocks.STRIPPED_AZALEA_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_AZALEA_LOG);
            addToList(ModBlocks.AZALEA_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.STRIPPED_AZALEA_WOOD);
            addToList(ModBlocks.AZALEA_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_PLANKS);
            addToList(ModBlocks.AZALEA_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_STAIRS);
            addToList(ModBlocks.AZALEA_FENCE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_SLAB);
            addToList(ModBlocks.AZALEA_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_FENCE);
            addToList(ModBlocks.AZALEA_DOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_FENCE_GATE);
            addToList(ModBlocks.AZALEA_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_DOOR);
            addToList(ModBlocks.AZALEA_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_TRAPDOOR);
            addToList(ModBlocks.AZALEA_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_PRESSURE_PLATE);
    
            addToList(ModBlocks.FLOWERING_AZALEA_LOG, CreativeModeTabs.BUILDING_BLOCKS, Operation.BEFORE, ModBlocks.AZALEA_BUTTON);
            addToList(ModBlocks.FLOWERING_AZALEA_WOOD, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_LOG);
            addToList(ModBlocks.FLOWERING_AZALEA_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_WOOD);
            addToList(ModBlocks.FLOWERING_AZALEA_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_PLANKS);
            addToList(ModBlocks.FLOWERING_AZALEA_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_STAIRS);
            addToList(ModBlocks.FLOWERING_AZALEA_FENCE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_SLAB);
            addToList(ModBlocks.FLOWERING_AZALEA_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_FENCE);
            addToList(ModBlocks.FLOWERING_AZALEA_DOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_FENCE_GATE);
            addToList(ModBlocks.FLOWERING_AZALEA_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.FLOWERING_AZALEA_DOOR);
            
            addToList(ModItems.COCONUT_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.BEFORE, Items.BAMBOO_SIGN);
            addToList(ModItems.COCONUT_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.COCONUT_SIGN);
            addToList(ModItems.MAPLE_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.COCONUT_HANGING_SIGN);
            addToList(ModItems.MAPLE_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.MAPLE_SIGN);
            addToList(ModItems.WALNUT_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.MAPLE_HANGING_SIGN);
            addToList(ModItems.WALNUT_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.WALNUT_SIGN);
            addToList(ModItems.AZALEA_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.WALNUT_HANGING_SIGN);
            addToList(ModItems.AZALEA_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.AZALEA_SIGN);
            addToList(ModItems.FLOWERING_AZALEA_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.AZALEA_HANGING_SIGN);
            addToList(ModItems.FLOWERING_AZALEA_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModItems.FLOWERING_AZALEA_SIGN);

            addToList(ModBlocks.COCONUT_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.BEFORE, Items.BAMBOO_SHELF);
            addToList(ModBlocks.MAPLE_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_SHELF);
            addToList(ModBlocks.WALNUT_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_SHELF);
            addToList(ModBlocks.AZALEA_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_SHELF);
            
            addToList(ModItems.COCONUT_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.BEFORE, Items.BAMBOO_RAFT);
            addToList(ModItems.COCONUT_CHEST_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.COCONUT_BOAT);
            addToList(ModItems.MAPLE_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.COCONUT_CHEST_BOAT);
            addToList(ModItems.MAPLE_CHEST_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.MAPLE_BOAT);
            addToList(ModItems.WALNUT_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.MAPLE_CHEST_BOAT);
            addToList(ModItems.WALNUT_CHEST_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.WALNUT_BOAT);
            addToList(ModItems.AZALEA_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.WALNUT_CHEST_BOAT);
            addToList(ModItems.AZALEA_CHEST_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.AZALEA_BOAT);
            addToList(ModItems.FLOWERING_AZALEA_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.AZALEA_CHEST_BOAT);
            addToList(ModItems.FLOWERING_AZALEA_CHEST_BOAT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, ModItems.FLOWERING_AZALEA_BOAT);
            
            addToList(ModBlocks.COCONUT_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.BEFORE, Items.MUSHROOM_STEM);
            addToList(ModBlocks.MAPLE_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_LOG);
            addToList(ModBlocks.SAPPY_MAPLE_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_LOG);
            addToList(ModBlocks.WALNUT_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.SAPPY_MAPLE_LOG);
            addToList(ModBlocks.AZALEA_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_LOG);
            addToList(ModBlocks.FLOWERING_AZALEA_LOG, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.AZALEA_LOG);
    
            addToList(ModBlocks.COCONUT_SEEDLING, CreativeModeTabs.NATURAL_BLOCKS, Operation.BEFORE, Items.AZALEA);
            addToList(ModBlocks.MAPLE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_SEEDLING);
            addToList(ModBlocks.WALNUT_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.MAPLE_SAPLING);

            addToList(ModBlocks.COCONUT_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.BEFORE, Items.AZALEA_LEAVES);
            addToList(ModBlocks.WALNUT_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.COCONUT_LEAVES);
            addToList(ModBlocks.RED_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.WALNUT_LEAVES);
            addToList(ModBlocks.ORANGE_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.RED_MAPLE_LEAVES);
            addToList(ModBlocks.YELLOW_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.ORANGE_MAPLE_LEAVES);
            addToList(ModBlocks.GREEN_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.YELLOW_MAPLE_LEAVES);

            addToList(ModBlocks.SEASHELL_TILES, CreativeModeTabs.BUILDING_BLOCKS, Operation.INSERT, null);
            addToList(ModBlocks.SEASHELL_TILE_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SEASHELL_TILES);
            addToList(ModBlocks.SEASHELL_TILE_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SEASHELL_TILE_STAIRS);
            addToList(ModBlocks.SEASHELL_TILE_WALL, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SEASHELL_TILE_SLAB);
            
            addToList(ModBlocks.ICE_BRICKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.INSERT, null);
            addToList(ModBlocks.ICE_BRICK_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.ICE_BRICKS);
            addToList(ModBlocks.ICE_BRICK_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.ICE_BRICK_STAIRS);
            addToList(ModBlocks.ICE_BRICK_WALL, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.ICE_BRICK_SLAB);

            addToList(ModBlocks.SNOW_BRICKS, CreativeModeTabs.BUILDING_BLOCKS, Operation.INSERT, null);
            addToList(ModBlocks.SNOW_BRICK_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SNOW_BRICKS);
            addToList(ModBlocks.SNOW_BRICK_SLAB, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SNOW_BRICK_STAIRS);
            addToList(ModBlocks.SNOW_BRICK_WALL, CreativeModeTabs.BUILDING_BLOCKS, Operation.AFTER, ModBlocks.SNOW_BRICK_SLAB);
            
            addToList(ModBlocks.SURFACE_MOSS, CreativeModeTabs.NATURAL_BLOCKS, Operation.BEFORE, Items.GLOW_LICHEN);
            addToList(ModBlocks.MOSS_LAYER, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, Items.MOSS_CARPET);
            addToList(ModBlocks.PALE_MOSS_LAYER, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, Items.PALE_MOSS_CARPET);
            addToList(ModBlocks.THIN_ICE, CreativeModeTabs.NATURAL_BLOCKS, Operation.BEFORE, Items.ICE);
            addToList(ModBlocks.COCONUT, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, Items.CACTUS);
            addToList(ModBlocks.AZALEA_FLOWER, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, Items.TORCHFLOWER);
            addToList(ModBlocks.SEASHELL, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, Items.DRIED_KELP_BLOCK);
            addToList(ModBlocks.SEASHELL_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.SEASHELL);
            addToList(ModBlocks.POT, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, Items.DECORATED_POT);
            addToList(ModBlocks.SANDCASTLE, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.INSERT, null);
            addToList(ModBlocks.SPILE, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.INSERT, null);

            addToList(ModItems.CRAB_MEAT, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.PUFFERFISH);
            addToList(ModItems.TROPICAL_STEW, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.RABBIT_STEW);
            addToList(ModItems.PRICKLY_PEAR, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.ENCHANTED_GOLDEN_APPLE);
            addToList(ModItems.WALNUT, CreativeModeTabs.FOOD_AND_DRINKS, Operation.BEFORE, Items.MELON_SLICE);
            addToList(ModItems.COCONUT_SLICE, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, ModItems.WALNUT);
            addToList(ModItems.COOKED_PRICKLY_PEAR, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, ModItems.PRICKLY_PEAR);
            addToList(ModItems.MAPLE_SYRUP_BOTTLE, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.HONEY_BOTTLE);
            addToList(ModItems.MAPLE_COOKIE, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.COOKIE);
            addToList(ModItems.MAPLE_PIE, CreativeModeTabs.FOOD_AND_DRINKS, Operation.AFTER, Items.PUMPKIN_PIE);
            addToList(ModItems.MAPLE_SAP_BUCKET, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, Items.MILK_BUCKET);
            addToList(ModItems.MAPLE_SAP_BUCKET, CreativeModeTabs.INGREDIENTS, Operation.AFTER, Items.HONEYCOMB);
            
            addToList(ModItems.CRAB_CLAW, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, Items.SHEARS);
            addToList(ModItems.PENGUIN_FEATHER, CreativeModeTabs.INGREDIENTS, Operation.AFTER, Items.FEATHER);
            addToList(ModItems.COCONUT_HUSK, CreativeModeTabs.INGREDIENTS, Operation.BEFORE, Items.BOWL);
            addToList(ModItems.MUSIC_DISC_COCONUT, CreativeModeTabs.TOOLS_AND_UTILITIES, Operation.AFTER, Items.MUSIC_DISC_11);

            addToList(ModItems.COCONUT_CRAB_SPAWN_EGG, CreativeModeTabs.SPAWN_EGGS, Operation.INSERT, null);
            addToList(ModItems.PENGUIN_SPAWN_EGG, CreativeModeTabs.SPAWN_EGGS, Operation.INSERT, null);
            addToList(ModItems.SQUIRREL_SPAWN_EGG, CreativeModeTabs.SPAWN_EGGS, Operation.INSERT, null);
        }
    }
    
    public static void addToList(ItemLike item) {
        addToList(item, ECOLOGICS_TAB, Operation.INSERT, null);
    }
    
    public static void addToList(ItemLike item, ResourceKey<CreativeModeTab> tab, Operation operation, @Nullable ItemLike adjacentItem) {
        TAB_ITEMS.add(new Quartet<ItemLike, ResourceKey<CreativeModeTab>, Operation, ItemLike>(item, tab, operation, adjacentItem));
    }
}
