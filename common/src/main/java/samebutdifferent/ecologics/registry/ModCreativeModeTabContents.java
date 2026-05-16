package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.world.level.ItemLike;

public class ModCreativeModeTabContents 
{
	public static final ArrayList<ItemLike> TAB_ITEMS = new ArrayList<>();
	
	public static void populateTabDatabase() {
        addToList(ModBlocks.COCONUT_LOG.get());
        addToList(ModBlocks.STRIPPED_COCONUT_LOG.get());
        addToList(ModBlocks.COCONUT_WOOD.get());
        addToList(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        addToList(ModBlocks.COCONUT_PLANKS.get());
        addToList(ModBlocks.COCONUT_STAIRS.get());
        addToList(ModBlocks.COCONUT_SLAB.get());
        addToList(ModBlocks.COCONUT_FENCE.get());
        addToList(ModBlocks.COCONUT_FENCE_GATE.get());
        addToList(ModBlocks.COCONUT_DOOR.get());
        addToList(ModBlocks.COCONUT_TRAPDOOR.get());
        addToList(ModBlocks.COCONUT_BUTTON.get());
        addToList(ModBlocks.COCONUT_PRESSURE_PLATE.get());
            
        addToList(ModBlocks.WALNUT_LOG.get());
        addToList(ModBlocks.STRIPPED_WALNUT_LOG.get());
        addToList(ModBlocks.WALNUT_WOOD.get());
        addToList(ModBlocks.STRIPPED_WALNUT_WOOD.get());
        addToList(ModBlocks.WALNUT_PLANKS.get());
        addToList(ModBlocks.WALNUT_STAIRS.get());
        addToList(ModBlocks.WALNUT_SLAB.get());
        addToList(ModBlocks.WALNUT_FENCE.get());
        addToList(ModBlocks.WALNUT_FENCE_GATE.get());
        addToList(ModBlocks.WALNUT_DOOR.get());
        addToList(ModBlocks.WALNUT_TRAPDOOR.get());
        addToList(ModBlocks.WALNUT_BUTTON.get());
        addToList(ModBlocks.WALNUT_PRESSURE_PLATE.get());
            
        addToList(ModBlocks.AZALEA_LOG.get());
        addToList(ModBlocks.STRIPPED_AZALEA_LOG.get());
        addToList(ModBlocks.AZALEA_WOOD.get());
        addToList(ModBlocks.STRIPPED_AZALEA_WOOD.get());
        addToList(ModBlocks.AZALEA_PLANKS.get());
        addToList(ModBlocks.AZALEA_STAIRS.get());
        addToList(ModBlocks.AZALEA_SLAB.get());
        addToList(ModBlocks.AZALEA_FENCE.get());
        addToList(ModBlocks.AZALEA_FENCE_GATE.get());
        addToList(ModBlocks.AZALEA_DOOR.get());
        addToList(ModBlocks.AZALEA_TRAPDOOR.get());
        addToList(ModBlocks.AZALEA_BUTTON.get());
        addToList(ModBlocks.AZALEA_PRESSURE_PLATE.get());

        addToList(ModBlocks.FLOWERING_AZALEA_LOG.get());
        addToList(ModBlocks.FLOWERING_AZALEA_WOOD.get());
        addToList(ModBlocks.FLOWERING_AZALEA_PLANKS.get());
        addToList(ModBlocks.FLOWERING_AZALEA_STAIRS.get());
        addToList(ModBlocks.FLOWERING_AZALEA_SLAB.get());
        addToList(ModBlocks.FLOWERING_AZALEA_FENCE.get());
        addToList(ModBlocks.FLOWERING_AZALEA_FENCE_GATE.get());
        addToList(ModBlocks.FLOWERING_AZALEA_DOOR.get());
        addToList(ModBlocks.FLOWERING_AZALEA_TRAPDOOR.get());
            
        addToList(ModItems.COCONUT_SIGN.get());
        addToList(ModItems.COCONUT_HANGING_SIGN.get());
        addToList(ModItems.WALNUT_SIGN.get());
        addToList(ModItems.WALNUT_HANGING_SIGN.get());
        addToList(ModItems.AZALEA_SIGN.get());
        addToList(ModItems.AZALEA_HANGING_SIGN.get());
        addToList(ModItems.FLOWERING_AZALEA_SIGN.get());
        addToList(ModItems.FLOWERING_AZALEA_HANGING_SIGN.get());
            
        addToList(ModItems.COCONUT_BOAT.get());
        addToList(ModItems.COCONUT_CHEST_BOAT.get());
        addToList(ModItems.WALNUT_BOAT.get());
        addToList(ModItems.WALNUT_CHEST_BOAT.get());
        addToList(ModItems.AZALEA_BOAT.get());
        addToList(ModItems.AZALEA_CHEST_BOAT.get());
        addToList(ModItems.FLOWERING_AZALEA_BOAT.get());
        addToList(ModItems.FLOWERING_AZALEA_CHEST_BOAT.get());
            
        addToList(ModBlocks.COCONUT_LEAVES.get());
        addToList(ModBlocks.WALNUT_LEAVES.get());
        addToList(ModBlocks.COCONUT_SEEDLING.get());
        addToList(ModBlocks.WALNUT_SAPLING.get());
            
        addToList(ModBlocks.COCONUT.get());
        addToList(ModBlocks.SEASHELL.get());
        addToList(ModBlocks.SEASHELL_BLOCK.get());
        addToList(ModBlocks.SEASHELL_TILES.get());
        addToList(ModBlocks.SEASHELL_TILE_STAIRS.get());
        addToList(ModBlocks.SEASHELL_TILE_SLAB.get());
        addToList(ModBlocks.SEASHELL_TILE_WALL.get());
        addToList(ModBlocks.POT.get());
        addToList(ModBlocks.THIN_ICE.get());
        addToList(ModBlocks.ICE_BRICKS.get());
        addToList(ModBlocks.ICE_BRICK_STAIRS.get());
        addToList(ModBlocks.ICE_BRICK_SLAB.get());
        addToList(ModBlocks.ICE_BRICK_WALL.get());
        addToList(ModBlocks.SNOW_BRICKS.get());
        addToList(ModBlocks.SNOW_BRICK_STAIRS.get());
        addToList(ModBlocks.SNOW_BRICK_SLAB.get());
        addToList(ModBlocks.SNOW_BRICK_WALL.get());

        addToList(ModBlocks.AZALEA_FLOWER.get());
        addToList(ModBlocks.SURFACE_MOSS.get());
        addToList(ModBlocks.MOSS_LAYER.get());
        addToList(ModItems.COCONUT_SLICE.get());
        addToList(ModItems.COCONUT_HUSK.get());
        addToList(ModItems.CRAB_CLAW.get());
        addToList(ModItems.CRAB_MEAT.get());
        addToList(ModItems.TROPICAL_STEW.get());
        addToList(ModItems.COCONUT_CRAB_SPAWN_EGG.get());
        addToList(ModItems.PENGUIN_SPAWN_EGG.get());
        addToList(ModItems.SQUIRREL_SPAWN_EGG.get());
        addToList(ModItems.SANDCASTLE.get());
        addToList(ModItems.MUSIC_DISC_COCONUT.get());
        addToList(ModItems.PRICKLY_PEAR.get());
        addToList(ModItems.COOKED_PRICKLY_PEAR.get());
        addToList(ModItems.PENGUIN_FEATHER.get());
        addToList(ModItems.WALNUT.get());
	}
	
	private static void addToList(ItemLike item) {
		TAB_ITEMS.add(item);
	}
}
