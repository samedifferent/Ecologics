package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import oshi.util.tuples.Triplet;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.AzaleaFlowerBlock;
import samebutdifferent.ecologics.block.AzaleaLogBlock;
import samebutdifferent.ecologics.block.CoconutBlock;
import samebutdifferent.ecologics.block.CoconutLeavesBlock;
import samebutdifferent.ecologics.block.CoconutSaplingBlock;
import samebutdifferent.ecologics.block.FloweringAzaleaLogBlock;
import samebutdifferent.ecologics.block.HangingCoconutBlock;
import samebutdifferent.ecologics.block.ModCeilingHangingSignBlock;
import samebutdifferent.ecologics.block.ModStandingSignBlock;
import samebutdifferent.ecologics.block.ModWallHangingSignBlock;
import samebutdifferent.ecologics.block.ModWallSignBlock;
import samebutdifferent.ecologics.block.MossLayerBlock;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SandcastleBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.block.SurfaceMossBlock;
import samebutdifferent.ecologics.block.ThinIceBlock;
import samebutdifferent.ecologics.block.grower.ModTreeGrower;
import samebutdifferent.ecologics.block.properties.ModWoodType;

public class ModBlocks
{	
    public static void init() {
    	for (Triplet<ResourceLocation, Block, Boolean> registry : BLOCKS) {
    		Registry.register(BuiltInRegistries.BLOCK, registry.getA(), registry.getB());
    	}
    }
    
    public static Block registerBlock(String name, Block block) {
    	return registerBlock(name, block, true);
    }
    
    public static Block registerBlock(String name, Block block, boolean addItem) {
    	BLOCKS.add(new Triplet(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), block, addItem));
    	return block;
    }
    
    public static final ArrayList<Triplet<ResourceLocation, Block, Boolean>> BLOCKS = new ArrayList();

    // Azalea Woodset
    public static final Block AZALEA_LOG = registerBlock("azalea_log", new AzaleaLogBlock());
    public static final Block FLOWERING_AZALEA_LOG = registerBlock("flowering_azalea_log", new FloweringAzaleaLogBlock());
    public static final Block STRIPPED_AZALEA_LOG = registerBlock("stripped_azalea_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block AZALEA_WOOD = registerBlock("azalea_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final Block FLOWERING_AZALEA_WOOD = registerBlock("flowering_azalea_wood", new FloweringAzaleaLogBlock());
    public static final Block STRIPPED_AZALEA_WOOD = registerBlock("stripped_azalea_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block AZALEA_PLANKS = registerBlock("azalea_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block FLOWERING_AZALEA_PLANKS = registerBlock("flowering_azalea_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block AZALEA_SLAB = registerBlock("azalea_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block FLOWERING_AZALEA_SLAB = registerBlock("flowering_azalea_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block AZALEA_STAIRS = registerBlock("azalea_stairs", new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block FLOWERING_AZALEA_STAIRS = registerBlock("flowering_azalea_stairs", new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block AZALEA_FENCE = registerBlock("azalea_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block FLOWERING_AZALEA_FENCE = registerBlock("flowering_azalea_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block AZALEA_FENCE_GATE = registerBlock("azalea_fence_gate", new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block FLOWERING_AZALEA_FENCE_GATE = registerBlock("flowering_azalea_fence_gate", new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block AZALEA_DOOR = registerBlock("azalea_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)){});
    public static final Block FLOWERING_AZALEA_DOOR = registerBlock("flowering_azalea_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)){});
    public static final Block AZALEA_TRAPDOOR = registerBlock("azalea_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)){});
    public static final Block FLOWERING_AZALEA_TRAPDOOR = registerBlock("flowering_azalea_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)){});
    public static final Block AZALEA_BUTTON = registerBlock("azalea_button", new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)) {});
    public static final Block AZALEA_PRESSURE_PLATE = registerBlock("azalea_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)) {});
    public static final Block AZALEA_FLOWER = registerBlock("azalea_flower", new AzaleaFlowerBlock(Properties.of().instabreak().noCollission().sound(SoundType.GRASS).offsetType(OffsetType.XZ)));
    public static final Block POTTED_AZALEA_FLOWER = registerBlock("potted_azalea_flower", new FlowerPotBlock(ModBlocks.AZALEA_FLOWER, BlockBehaviour.Properties.of().instabreak().noOcclusion()), false);
    public static final Block AZALEA_SIGN = registerBlock("azalea_sign", new ModStandingSignBlock(ModWoodType.AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block AZALEA_WALL_SIGN = registerBlock("azalea_wall_sign", new ModWallSignBlock(ModWoodType.AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(AZALEA_SIGN)), false);
    public static final Block AZALEA_HANGING_SIGN = registerBlock("azalea_hanging_sign", new ModCeilingHangingSignBlock(ModWoodType.AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block AZALEA_WALL_HANGING_SIGN = registerBlock("azalea_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodType.AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(AZALEA_HANGING_SIGN)), false);
    public static final Block FLOWERING_AZALEA_SIGN = registerBlock("flowering_azalea_sign", new ModStandingSignBlock(ModWoodType.FLOWERING_AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block FLOWERING_AZALEA_WALL_SIGN = registerBlock("flowering_azalea_wall_sign", new ModWallSignBlock(ModWoodType.FLOWERING_AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(FLOWERING_AZALEA_SIGN)), false);
    public static final Block FLOWERING_AZALEA_HANGING_SIGN = registerBlock("flowering_azalea_hanging_sign", new ModCeilingHangingSignBlock(ModWoodType.FLOWERING_AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block FLOWERING_AZALEA_WALL_HANGING_SIGN = registerBlock("flowering_azalea_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodType.FLOWERING_AZALEA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(FLOWERING_AZALEA_HANGING_SIGN)), false);

    // Coconut Woodset
    public static final Block COCONUT_LOG = registerBlock("coconut_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block COCONUT_WOOD = registerBlock("coconut_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block COCONUT_LEAVES = registerBlock("coconut_leaves", new CoconutLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final Block COCONUT_PLANKS = registerBlock("coconut_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_SLAB = registerBlock("coconut_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_STAIRS = registerBlock("coconut_stairs", new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_FENCE = registerBlock("coconut_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate", new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_DOOR = registerBlock("coconut_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)){});
    public static final Block COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)){});
    public static final Block COCONUT_BUTTON = registerBlock("coconut_button", new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)) {});
    public static final Block COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)) {});
    public static final Block COCONUT_SIGN = registerBlock("coconut_sign", new ModStandingSignBlock(ModWoodType.COCONUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block COCONUT_WALL_SIGN = registerBlock("coconut_wall_sign", new ModWallSignBlock(ModWoodType.COCONUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(COCONUT_SIGN)), false);
    public static final Block COCONUT_HANGING_SIGN = registerBlock("coconut_hanging_sign", new ModCeilingHangingSignBlock(ModWoodType.COCONUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block COCONUT_WALL_HANGING_SIGN = registerBlock("coconut_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodType.COCONUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(COCONUT_HANGING_SIGN)), false);
    public static final Block COCONUT_SEEDLING = registerBlock("coconut_seedling", new CoconutSaplingBlock(ModTreeGrower.COCONUT, Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final Block POTTED_COCONUT_SEEDLING = registerBlock("potted_coconut_seedling", new FlowerPotBlock(ModBlocks.COCONUT_SEEDLING, BlockBehaviour.Properties.of().instabreak().noOcclusion()), false);
    
    // Walnut Woodset
    public static final Block WALNUT_LOG = registerBlock("walnut_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block WALNUT_WOOD = registerBlock("walnut_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block WALNUT_LEAVES = registerBlock("walnut_leaves", new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final Block WALNUT_PLANKS = registerBlock("walnut_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block WALNUT_SLAB = registerBlock("walnut_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block WALNUT_STAIRS = registerBlock("walnut_stairs", new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block WALNUT_FENCE = registerBlock("walnut_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate", new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){});
    public static final Block WALNUT_DOOR = registerBlock("walnut_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)){});
    public static final Block WALNUT_TRAPDOOR = registerBlock("walnut_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)){});
    public static final Block WALNUT_BUTTON = registerBlock("walnut_button", new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)) {});
    public static final Block WALNUT_PRESSURE_PLATE = registerBlock("walnut_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)) {});
    public static final Block WALNUT_SIGN = registerBlock("walnut_sign", new ModStandingSignBlock(ModWoodType.WALNUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block WALNUT_WALL_SIGN = registerBlock("walnut_wall_sign", new ModWallSignBlock(ModWoodType.WALNUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(WALNUT_SIGN)), false);
    public static final Block WALNUT_HANGING_SIGN = registerBlock("walnut_hanging_sign", new ModCeilingHangingSignBlock(ModWoodType.WALNUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)), false);
    public static final Block WALNUT_WALL_HANGING_SIGN = registerBlock("walnut_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodType.WALNUT, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(WALNUT_HANGING_SIGN)), false);
    public static final Block WALNUT_SAPLING = registerBlock("walnut_sapling", new SaplingBlock(ModTreeGrower.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)){});
    public static final Block POTTED_WALNUT_SAPLING = registerBlock("potted_walnut_sapling", new FlowerPotBlock(ModBlocks.WALNUT_SAPLING, BlockBehaviour.Properties.of().instabreak().noOcclusion()), false);
    
    // SeaShell
    public static final Block SEASHELL = registerBlock("seashell", new SeashellBlock());
    public static final Block SEASHELL_BLOCK = registerBlock("seashell_block", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F, 6.0F).sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops()));
    public static final Block SEASHELL_TILES = registerBlock("seashell_tiles", new Block(BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK)));
    public static final Block SEASHELL_TILE_STAIRS = registerBlock("seashell_tile_stairs", new StairBlock(SEASHELL_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK)));
    public static final Block SEASHELL_TILE_SLAB = registerBlock("seashell_tile_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK)));
    public static final Block SEASHELL_TILE_WALL = registerBlock("seashell_tile_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK)));

    // Snow & Ice Bricks
    public static final Block ICE_BRICKS = registerBlock("ice_bricks", new Block(BlockBehaviour.Properties.of().friction(0.98F).strength(0.5F).sound(SoundType.GLASS)));
    public static final Block ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs", new StairBlock(ICE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS)));
    public static final Block ICE_BRICK_SLAB = registerBlock("ice_brick_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS)));
    public static final Block ICE_BRICK_WALL = registerBlock("ice_brick_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS)));
    public static final Block SNOW_BRICKS = registerBlock("snow_bricks", new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.SNOW)));
    public static final Block SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs", new StairBlock(SNOW_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS)));
    public static final Block SNOW_BRICK_SLAB = registerBlock("snow_brick_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS)));
    public static final Block SNOW_BRICK_WALL = registerBlock("snow_brick_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS)));

    // Miscellaneous
    public static final Block SURFACE_MOSS = registerBlock("surface_moss", new SurfaceMossBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.2F).pushReaction(PushReaction.DESTROY).sound(SoundType.MOSS_CARPET).noOcclusion()));
    public static final Block MOSS_LAYER = registerBlock("moss_layer", new MossLayerBlock());

    public static final Block HANGING_COCONUT = registerBlock("hanging_coconut", new HangingCoconutBlock(Properties.of().randomTicks().strength(2.0F, 3.0F).pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD).noOcclusion()), false);
    public static final Block COCONUT = registerBlock("coconut", new CoconutBlock());
    public static final Block SANDCASTLE = registerBlock("sandcastle", new SandcastleBlock(Properties.of().mapColor(MapColor.SAND).strength(0.7F).sound(SoundType.SAND).pushReaction(PushReaction.DESTROY).noOcclusion().randomTicks()), false);
    public static final Block PRICKLY_PEAR = registerBlock("prickly_pear", new PricklyPearBlock(), false);
    public static final Block POT = registerBlock("pot", new PotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final Block THIN_ICE = registerBlock("thin_ice", new ThinIceBlock());
    
    /*public static <T extends Block T> registerBlock(String name, T> block) {
        <T> toReturn = registerBlock(name, block);
        registerItem(name, new BlockItem(toReturn, new Item.Properties()));
        return toReturn;
    }*/
}
