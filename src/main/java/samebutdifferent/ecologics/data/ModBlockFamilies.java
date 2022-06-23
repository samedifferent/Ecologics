package samebutdifferent.ecologics.data;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final BlockFamily COCONUT_PLANKS = familyBuilder(ModBlocks.COCONUT_PLANKS.get()).button(ModBlocks.COCONUT_BUTTON.get()).fence(ModBlocks.COCONUT_FENCE.get()).fenceGate(ModBlocks.COCONUT_FENCE_GATE.get()).pressurePlate(ModBlocks.COCONUT_PRESSURE_PLATE.get()).sign(ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get()).slab(ModBlocks.COCONUT_SLAB.get()).stairs(ModBlocks.COCONUT_STAIRS.get()).door(ModBlocks.COCONUT_DOOR.get()).trapdoor(ModBlocks.COCONUT_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily WALNUT_PLANKS = familyBuilder(ModBlocks.WALNUT_PLANKS.get()).button(ModBlocks.WALNUT_BUTTON.get()).fence(ModBlocks.WALNUT_FENCE.get()).fenceGate(ModBlocks.WALNUT_FENCE_GATE.get()).pressurePlate(ModBlocks.WALNUT_PRESSURE_PLATE.get()).sign(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()).slab(ModBlocks.WALNUT_SLAB.get()).stairs(ModBlocks.WALNUT_STAIRS.get()).door(ModBlocks.WALNUT_DOOR.get()).trapdoor(ModBlocks.WALNUT_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily AZALEA_PLANKS = familyBuilder(ModBlocks.AZALEA_PLANKS.get()).button(ModBlocks.AZALEA_BUTTON.get()).fence(ModBlocks.AZALEA_FENCE.get()).fenceGate(ModBlocks.AZALEA_FENCE_GATE.get()).pressurePlate(ModBlocks.AZALEA_PRESSURE_PLATE.get()).sign(ModBlocks.AZALEA_SIGN.get(), ModBlocks.AZALEA_WALL_SIGN.get()).slab(ModBlocks.AZALEA_SLAB.get()).stairs(ModBlocks.AZALEA_STAIRS.get()).door(ModBlocks.AZALEA_DOOR.get()).trapdoor(ModBlocks.AZALEA_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily FLOWERING_AZALEA_PLANKS = familyBuilder(ModBlocks.FLOWERING_AZALEA_PLANKS.get()).fence(ModBlocks.FLOWERING_AZALEA_FENCE.get()).fenceGate(ModBlocks.FLOWERING_AZALEA_FENCE_GATE.get()).sign(ModBlocks.FLOWERING_AZALEA_SIGN.get(), ModBlocks.FLOWERING_AZALEA_WALL_SIGN.get()).slab(ModBlocks.FLOWERING_AZALEA_SLAB.get()).stairs(ModBlocks.FLOWERING_AZALEA_STAIRS.get()).door(ModBlocks.FLOWERING_AZALEA_DOOR.get()).trapdoor(ModBlocks.FLOWERING_AZALEA_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily ICE = familyBuilder(Blocks.ICE).polished(ModBlocks.ICE_BRICKS.get()).getFamily();
    public static final BlockFamily ICE_BRICKS = familyBuilder(ModBlocks.ICE_BRICKS.get()).stairs(ModBlocks.ICE_BRICK_STAIRS.get()).slab(ModBlocks.ICE_BRICK_SLAB.get()).wall(ModBlocks.ICE_BRICK_WALL.get()).getFamily();
    public static final BlockFamily SEASHELL = familyBuilder(ModBlocks.SEASHELL.get()).polished(ModBlocks.SEASHELL_TILES.get()).getFamily();
    public static final BlockFamily SEASHELL_TILES = familyBuilder(ModBlocks.SEASHELL_TILES.get()).stairs(ModBlocks.SEASHELL_TILE_STAIRS.get()).slab(ModBlocks.SEASHELL_TILE_SLAB.get()).wall(ModBlocks.SEASHELL_TILE_WALL.get()).getFamily();
    public static final BlockFamily SNOW = familyBuilder(Blocks.SNOW_BLOCK).polished(ModBlocks.SNOW_BRICKS.get()).getFamily();
    public static final BlockFamily SNOW_BRICKS = familyBuilder(ModBlocks.SNOW_BRICKS.get()).stairs(ModBlocks.SNOW_BRICK_STAIRS.get()).slab(ModBlocks.SNOW_BRICK_SLAB.get()).wall(ModBlocks.SNOW_BRICK_WALL.get()).getFamily();

    private static BlockFamily.Builder familyBuilder(Block pBaseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(pBaseBlock);
        BlockFamily blockfamily = MAP.put(pBaseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getKey(pBaseBlock));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
