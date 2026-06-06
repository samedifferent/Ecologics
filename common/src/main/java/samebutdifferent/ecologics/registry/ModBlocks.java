package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
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
import samebutdifferent.ecologics.block.ModShelfBlock;
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
import samebutdifferent.ecologics.block.properties.ModBlockSetType;
import samebutdifferent.ecologics.block.properties.ModWoodType;

public class ModBlocks
{	
    public static void init() {
    	for (Triplet<Identifier, Block, Boolean> registry : BLOCKS) {
    		Registry.register(BuiltInRegistries.BLOCK, registry.getA(), registry.getB());
    	}
    }
    
    public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean addItem) {
    	ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name));
    	Block block = blockFactory.apply(properties.setId(blockKey));
    	BLOCKS.add(new Triplet<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), block, addItem));
    	return block;
    }
    
    public static final ArrayList<Triplet<Identifier, Block, Boolean>> BLOCKS = new ArrayList<>();

    // Azalea Woodset
    public static final Block AZALEA_LOG = registerBlock("azalea_log", AzaleaLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG), true);
    public static final Block FLOWERING_AZALEA_LOG = registerBlock("flowering_azalea_log", FloweringAzaleaLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG), true);
    public static final Block STRIPPED_AZALEA_LOG = registerBlock("stripped_azalea_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block AZALEA_WOOD = registerBlock("azalea_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD), true);
    public static final Block FLOWERING_AZALEA_WOOD = registerBlock("flowering_azalea_wood", FloweringAzaleaLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_AZALEA_WOOD = registerBlock("stripped_azalea_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block AZALEA_PLANKS = registerBlock("azalea_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block FLOWERING_AZALEA_PLANKS = registerBlock("flowering_azalea_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block AZALEA_SLAB = registerBlock("azalea_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block FLOWERING_AZALEA_SLAB = registerBlock("flowering_azalea_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block AZALEA_STAIRS = registerBlock("azalea_stairs", properties -> new StairBlock(AZALEA_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block FLOWERING_AZALEA_STAIRS = registerBlock("flowering_azalea_stairs", properties -> new StairBlock(FLOWERING_AZALEA_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block AZALEA_FENCE = registerBlock("azalea_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block FLOWERING_AZALEA_FENCE = registerBlock("flowering_azalea_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block AZALEA_FENCE_GATE = registerBlock("azalea_fence_gate", properties -> new FenceGateBlock(ModWoodType.AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block FLOWERING_AZALEA_FENCE_GATE = registerBlock("flowering_azalea_fence_gate", properties -> new FenceGateBlock(ModWoodType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block AZALEA_DOOR = registerBlock("azalea_door", properties -> new DoorBlock(ModBlockSetType.AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final Block FLOWERING_AZALEA_DOOR = registerBlock("flowering_azalea_door", properties -> new DoorBlock(ModBlockSetType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final Block AZALEA_TRAPDOOR = registerBlock("azalea_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);
    public static final Block FLOWERING_AZALEA_TRAPDOOR = registerBlock("flowering_azalea_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);
    public static final Block AZALEA_BUTTON = registerBlock("azalea_button", properties -> new ButtonBlock(ModBlockSetType.AZALEA, 10, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON), true);
    public static final Block AZALEA_PRESSURE_PLATE = registerBlock("azalea_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.AZALEA, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE), true);
    public static final Block AZALEA_FLOWER = registerBlock("azalea_flower", AzaleaFlowerBlock::new, Properties.of().instabreak().noCollision().sound(SoundType.GRASS).offsetType(OffsetType.XZ), true);
    public static final Block POTTED_AZALEA_FLOWER = registerBlock("potted_azalea_flower", properties -> new FlowerPotBlock(ModBlocks.AZALEA_FLOWER, properties), BlockBehaviour.Properties.of().instabreak().noOcclusion(), false);
    public static final Block AZALEA_SHELF = registerBlock("azalea_shelf", ModShelfBlock::new, BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD), true);
    public static final Block AZALEA_SIGN = registerBlock("azalea_sign", properties -> new ModStandingSignBlock(ModWoodType.AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block AZALEA_WALL_SIGN = registerBlock("azalea_wall_sign", properties -> new ModWallSignBlock(ModWoodType.AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block AZALEA_HANGING_SIGN = registerBlock("azalea_hanging_sign", properties -> new ModCeilingHangingSignBlock(ModWoodType.AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block AZALEA_WALL_HANGING_SIGN = registerBlock("azalea_wall_hanging_sign", properties -> new ModWallHangingSignBlock(ModWoodType.AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block FLOWERING_AZALEA_SIGN = registerBlock("flowering_azalea_sign", properties -> new ModStandingSignBlock(ModWoodType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block FLOWERING_AZALEA_WALL_SIGN = registerBlock("flowering_azalea_wall_sign", properties -> new ModWallSignBlock(ModWoodType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block FLOWERING_AZALEA_HANGING_SIGN = registerBlock("flowering_azalea_hanging_sign", properties -> new ModCeilingHangingSignBlock(ModWoodType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block FLOWERING_AZALEA_WALL_HANGING_SIGN = registerBlock("flowering_azalea_wall_hanging_sign", properties -> new ModWallHangingSignBlock(ModWoodType.FLOWERING_AZALEA, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);

    // Coconut Woodset
    public static final Block COCONUT_LOG = registerBlock("coconut_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block COCONUT_WOOD = registerBlock("coconut_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block COCONUT_LEAVES = registerBlock("coconut_leaves", properties -> new CoconutLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, -9399763), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false), true);
    public static final Block COCONUT_PLANKS = registerBlock("coconut_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block COCONUT_SLAB = registerBlock("coconut_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block COCONUT_STAIRS = registerBlock("coconut_stairs", properties -> new StairBlock(COCONUT_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block COCONUT_FENCE = registerBlock("coconut_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate", properties -> new FenceGateBlock(ModWoodType.COCONUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block COCONUT_DOOR = registerBlock("coconut_door", properties -> new DoorBlock(ModBlockSetType.COCONUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final Block COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.COCONUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);
    public static final Block COCONUT_BUTTON = registerBlock("coconut_button", properties -> new ButtonBlock(ModBlockSetType.COCONUT, 10, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON), true);
    public static final Block COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.COCONUT, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE), true);
    public static final Block COCONUT_SHELF = registerBlock("coconut_shelf", ModShelfBlock::new, BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD), true);
    public static final Block COCONUT_SIGN = registerBlock("coconut_sign", properties -> new ModStandingSignBlock(ModWoodType.COCONUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block COCONUT_WALL_SIGN = registerBlock("coconut_wall_sign", properties -> new ModWallSignBlock(ModWoodType.COCONUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block COCONUT_HANGING_SIGN = registerBlock("coconut_hanging_sign", properties -> new ModCeilingHangingSignBlock(ModWoodType.COCONUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block COCONUT_WALL_HANGING_SIGN = registerBlock("coconut_wall_hanging_sign", properties -> new ModWallHangingSignBlock(ModWoodType.COCONUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block COCONUT_SEEDLING = registerBlock("coconut_seedling", properties -> new CoconutSaplingBlock(ModTreeGrower.COCONUT, properties), Properties.of().noCollision().randomTicks().instabreak().sound(SoundType.GRASS), true);
    public static final Block POTTED_COCONUT_SEEDLING = registerBlock("potted_coconut_seedling", properties -> new FlowerPotBlock(ModBlocks.COCONUT_SEEDLING, properties), BlockBehaviour.Properties.of().instabreak().noOcclusion(), false);
    
    // Walnut Woodset
    public static final Block WALNUT_LOG = registerBlock("walnut_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block WALNUT_WOOD = registerBlock("walnut_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block WALNUT_LEAVES = registerBlock("walnut_leaves", properties -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x9C813B), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false), true);
    public static final Block WALNUT_PLANKS = registerBlock("walnut_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block WALNUT_SLAB = registerBlock("walnut_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block WALNUT_STAIRS = registerBlock("walnut_stairs", properties -> new StairBlock(WALNUT_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block WALNUT_FENCE = registerBlock("walnut_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate", properties -> new FenceGateBlock(ModWoodType.WALNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block WALNUT_DOOR = registerBlock("walnut_door", properties -> new DoorBlock(ModBlockSetType.WALNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final Block WALNUT_TRAPDOOR = registerBlock("walnut_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.WALNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);
    public static final Block WALNUT_BUTTON = registerBlock("walnut_button", properties -> new ButtonBlock(ModBlockSetType.WALNUT, 10, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON), true);
    public static final Block WALNUT_PRESSURE_PLATE = registerBlock("walnut_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.WALNUT, properties){}, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE), true);
    public static final Block WALNUT_SHELF = registerBlock("walnut_shelf", ModShelfBlock::new, BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD), true);
    public static final Block WALNUT_SIGN = registerBlock("walnut_sign", properties -> new ModStandingSignBlock(ModWoodType.WALNUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block WALNUT_WALL_SIGN = registerBlock("walnut_wall_sign", properties -> new ModWallSignBlock(ModWoodType.WALNUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block WALNUT_HANGING_SIGN = registerBlock("walnut_hanging_sign", properties -> new ModCeilingHangingSignBlock(ModWoodType.WALNUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block WALNUT_WALL_HANGING_SIGN = registerBlock("walnut_wall_hanging_sign", properties -> new ModWallHangingSignBlock(ModWoodType.WALNUT, properties), BlockBehaviour.Properties.of().noCollision().strength(1.0F).sound(SoundType.WOOD), false);
    public static final Block WALNUT_SAPLING = registerBlock("walnut_sapling", properties -> new SaplingBlock(ModTreeGrower.WALNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final Block POTTED_WALNUT_SAPLING = registerBlock("potted_walnut_sapling", properties -> new FlowerPotBlock(ModBlocks.WALNUT_SAPLING, properties), BlockBehaviour.Properties.of().instabreak().noOcclusion(), false);
    
    // SeaShell
    public static final Block SEASHELL = registerBlock("seashell", SeashellBlock::new, Properties.of().mapColor(MapColor.COLOR_BROWN).noOcclusion().strength(0.5F).sound(SoundType.BONE_BLOCK), true);
    public static final Block SEASHELL_BLOCK = registerBlock("seashell_block", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F, 6.0F).sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops(), true);
    public static final Block SEASHELL_TILES = registerBlock("seashell_tiles", Block::new, BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK), true);
    public static final Block SEASHELL_TILE_STAIRS = registerBlock("seashell_tile_stairs", properties -> new StairBlock(SEASHELL_BLOCK.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK), true);
    public static final Block SEASHELL_TILE_SLAB = registerBlock("seashell_tile_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK), true);
    public static final Block SEASHELL_TILE_WALL = registerBlock("seashell_tile_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(SEASHELL_BLOCK), true);

    // Snow & Ice Bricks
    public static final Block ICE_BRICKS = registerBlock("ice_bricks", Block::new, BlockBehaviour.Properties.of().friction(0.98F).strength(0.5F).sound(SoundType.GLASS), true);
    public static final Block ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs", properties -> new StairBlock(ICE_BRICKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS), true);
    public static final Block ICE_BRICK_SLAB = registerBlock("ice_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS), true);
    public static final Block ICE_BRICK_WALL = registerBlock("ice_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(ICE_BRICKS), true);
    public static final Block SNOW_BRICKS = registerBlock("snow_bricks", Block::new, BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.SNOW), true);
    public static final Block SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs", properties -> new StairBlock(SNOW_BRICKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS), true);
    public static final Block SNOW_BRICK_SLAB = registerBlock("snow_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS), true);
    public static final Block SNOW_BRICK_WALL = registerBlock("snow_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(SNOW_BRICKS), true);

    // Miscellaneous
    public static final Block SURFACE_MOSS = registerBlock("surface_moss", SurfaceMossBlock::new, Properties.of().mapColor(MapColor.COLOR_GREEN).noCollision().strength(0.2F).pushReaction(PushReaction.DESTROY).sound(SoundType.MOSS_CARPET).noOcclusion(), true);
    public static final Block MOSS_LAYER = registerBlock("moss_layer", MossLayerBlock::new, Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).pushReaction(PushReaction.DESTROY).sound(SoundType.MOSS_CARPET), true);
    public static final Block PALE_MOSS_LAYER = registerBlock("pale_moss_layer", MossLayerBlock::new, Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.1F).pushReaction(PushReaction.DESTROY).sound(SoundType.MOSS_CARPET), true);

    public static final Block HANGING_COCONUT = registerBlock("hanging_coconut", HangingCoconutBlock::new, Properties.of().randomTicks().strength(2.0F, 3.0F).pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD).noOcclusion(), false);
    public static final Block COCONUT = registerBlock("coconut", CoconutBlock::new, Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), false);
    public static final Block SANDCASTLE = registerBlock("sandcastle", SandcastleBlock::new, Properties.of().mapColor(MapColor.SAND).strength(0.7F).sound(SoundType.SAND).pushReaction(PushReaction.DESTROY).noOcclusion().randomTicks(), false);
    public static final Block PRICKLY_PEAR = registerBlock("prickly_pear", PricklyPearBlock::new, Properties.of().noCollision().randomTicks().pushReaction(PushReaction.DESTROY).instabreak().sound(SoundType.HONEY_BLOCK), false);
    public static final Block POT = registerBlock("pot", PotBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(1.0F), true);
    public static final Block THIN_ICE = registerBlock("thin_ice", ThinIceBlock::new, Properties.of().friction(0.98F).strength(0.5F).sound(SoundType.GLASS).noOcclusion(), true);

}
