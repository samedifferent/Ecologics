package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.world.level.ItemLike;

public class ModCreativeModeTabContents 
{
	public static final ArrayList<ItemLike> TAB_ITEMS = new ArrayList();
	
	public static void populateTabDatabase() {
        addToList(ModBlocks.COCONUT_LOG);
        addToList(ModBlocks.STRIPPED_COCONUT_LOG);
        addToList(ModBlocks.COCONUT_WOOD);
        addToList(ModBlocks.STRIPPED_COCONUT_WOOD);
        addToList(ModBlocks.COCONUT_PLANKS);
        addToList(ModBlocks.COCONUT_STAIRS);
        addToList(ModBlocks.COCONUT_SLAB);
        addToList(ModBlocks.COCONUT_FENCE);
        addToList(ModBlocks.COCONUT_FENCE_GATE);
        addToList(ModBlocks.COCONUT_DOOR);
        addToList(ModBlocks.COCONUT_TRAPDOOR);
        addToList(ModBlocks.COCONUT_BUTTON);
        addToList(ModBlocks.COCONUT_PRESSURE_PLATE);
            
        addToList(ModBlocks.WALNUT_LOG);
        addToList(ModBlocks.STRIPPED_WALNUT_LOG);
        addToList(ModBlocks.WALNUT_WOOD);
        addToList(ModBlocks.STRIPPED_WALNUT_WOOD);
        addToList(ModBlocks.WALNUT_PLANKS);
        addToList(ModBlocks.WALNUT_STAIRS);
        addToList(ModBlocks.WALNUT_SLAB);
        addToList(ModBlocks.WALNUT_FENCE);
        addToList(ModBlocks.WALNUT_FENCE_GATE);
        addToList(ModBlocks.WALNUT_DOOR);
        addToList(ModBlocks.WALNUT_TRAPDOOR);
        addToList(ModBlocks.WALNUT_BUTTON);
        addToList(ModBlocks.WALNUT_PRESSURE_PLATE);
            
        addToList(ModBlocks.AZALEA_LOG);
        addToList(ModBlocks.STRIPPED_AZALEA_LOG);
        addToList(ModBlocks.AZALEA_WOOD);
        addToList(ModBlocks.STRIPPED_AZALEA_WOOD);
        addToList(ModBlocks.AZALEA_PLANKS);
        addToList(ModBlocks.AZALEA_STAIRS);
        addToList(ModBlocks.AZALEA_SLAB);
        addToList(ModBlocks.AZALEA_FENCE);
        addToList(ModBlocks.AZALEA_FENCE_GATE);
        addToList(ModBlocks.AZALEA_DOOR);
        addToList(ModBlocks.AZALEA_TRAPDOOR);
        addToList(ModBlocks.AZALEA_BUTTON);
        addToList(ModBlocks.AZALEA_PRESSURE_PLATE);

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
        addToList(ModItems.WALNUT_SIGN);
        addToList(ModItems.WALNUT_HANGING_SIGN);
        addToList(ModItems.AZALEA_SIGN);
        addToList(ModItems.AZALEA_HANGING_SIGN);
        addToList(ModItems.FLOWERING_AZALEA_SIGN);
        addToList(ModItems.FLOWERING_AZALEA_HANGING_SIGN);
            
        addToList(ModItems.COCONUT_BOAT);
        addToList(ModItems.COCONUT_CHEST_BOAT);
        addToList(ModItems.WALNUT_BOAT);
        addToList(ModItems.WALNUT_CHEST_BOAT);
        addToList(ModItems.AZALEA_BOAT);
        addToList(ModItems.AZALEA_CHEST_BOAT);
        addToList(ModItems.FLOWERING_AZALEA_BOAT);
        addToList(ModItems.FLOWERING_AZALEA_CHEST_BOAT);
            
        addToList(ModBlocks.COCONUT_LEAVES);
        addToList(ModBlocks.WALNUT_LEAVES);
        addToList(ModBlocks.COCONUT_SEEDLING);
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
        addToList(ModItems.COCONUT_SLICE);
        addToList(ModItems.COCONUT_HUSK);
        addToList(ModItems.CRAB_CLAW);
        addToList(ModItems.CRAB_MEAT);
        addToList(ModItems.TROPICAL_STEW);
        addToList(ModItems.COCONUT_CRAB_SPAWN_EGG);
        addToList(ModItems.PENGUIN_SPAWN_EGG);
        addToList(ModItems.SQUIRREL_SPAWN_EGG);
        addToList(ModItems.SANDCASTLE);
        addToList(ModItems.MUSIC_DISC_COCONUT);
        addToList(ModItems.PRICKLY_PEAR);
        addToList(ModItems.COOKED_PRICKLY_PEAR);
        addToList(ModItems.PENGUIN_FEATHER);
        addToList(ModItems.WALNUT);
	}
	
	private static void addToList(ItemLike item) {
		TAB_ITEMS.add(item);
	}
}
