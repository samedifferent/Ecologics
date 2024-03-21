package samebutdifferent.ecologics.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import samebutdifferent.ecologics.block.*;
import samebutdifferent.ecologics.block.grower.WalnutTreeGrower;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModBlocks {
    public static void init() {}

    public static final Supplier<PalmLeavesBlock> PALM_LEAVES = registerBlock("palm_leaves", () -> new PalmLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<HangingCoconutBlock> HANGING_COCONUT = CommonPlatformHelper.registerBlock("hanging_coconut", HangingCoconutBlock::new);
    public static final Supplier<CoconutBlock> COCONUT = registerBlock("coconut", CoconutBlock::new);
    public static final Supplier<SaplingBlock> PALM_SAPLING = registerBlock("palm_sapling", PalmSaplingBlock::new);
    public static final Supplier<FlowerPotBlock> POTTED_PALM_SAPLING = CommonPlatformHelper.registerBlock("potted_palm_sapling", () -> new FlowerPotBlock(ModBlocks.PALM_SAPLING.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final Supplier<SeashellBlock> SEASHELL = registerBlock("seashell", SeashellBlock::new);
    public static final Supplier<SandcastleBlock> SANDCASTLE = CommonPlatformHelper.registerBlock("sandcastle", SandcastleBlock::new);
    public static final Supplier<Block> SEASHELL_BLOCK = registerBlock("seashell_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F, 6.0F).sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops()));
    public static final Supplier<Block> SEASHELL_TILES = registerBlock("seashell_tiles", () -> new Block(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final Supplier<StairBlock> SEASHELL_TILE_STAIRS = registerBlock("seashell_tile_stairs", () -> new StairBlock(SEASHELL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final Supplier<SlabBlock> SEASHELL_TILE_SLAB = registerBlock("seashell_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final Supplier<WallBlock> SEASHELL_TILE_WALL = registerBlock("seashell_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final Supplier<PricklyPearBlock> PRICKLY_PEAR = CommonPlatformHelper.registerBlock("prickly_pear", PricklyPearBlock::new);
    public static final Supplier<PotBlock> POT = registerBlock("pot", () -> new PotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final Supplier<ThinIceBlock> THIN_ICE = registerBlock("thin_ice", ThinIceBlock::new);
    public static final Supplier<Block> ICE_BRICKS = registerBlock("ice_bricks", () -> new Block(BlockBehaviour.Properties.of().friction(0.98F).strength(0.5F).sound(SoundType.GLASS)));
    public static final Supplier<StairBlock> ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs", () -> new StairBlock(ICE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ICE_BRICKS.get())));
    public static final Supplier<SlabBlock> ICE_BRICK_SLAB = registerBlock("ice_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ICE_BRICKS.get())));
    public static final Supplier<WallBlock> ICE_BRICK_WALL = registerBlock("ice_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ICE_BRICKS.get())));
    public static final Supplier<Block> SNOW_BRICKS = registerBlock("snow_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.SNOW)));
    public static final Supplier<StairBlock> SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SNOW_BRICKS.get())));
    public static final Supplier<SlabBlock> SNOW_BRICK_SLAB = registerBlock("snow_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SNOW_BRICKS.get())));
    public static final Supplier<WallBlock> SNOW_BRICK_WALL = registerBlock("snow_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SNOW_BRICKS.get())));
    public static final Supplier<RotatedPillarBlock> WALNUT_LOG = registerBlock("walnut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final Supplier<RotatedPillarBlock> STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Supplier<RotatedPillarBlock> WALNUT_WOOD = registerBlock("walnut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final Supplier<RotatedPillarBlock> STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Supplier<LeavesBlock> WALNUT_LEAVES = registerBlock("walnut_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<Block> WALNUT_PLANKS = registerBlock("walnut_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<SlabBlock> WALNUT_SLAB = registerBlock("walnut_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<StairBlock> WALNUT_STAIRS = registerBlock("walnut_stairs", () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<FenceBlock> WALNUT_FENCE = registerBlock("walnut_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<FenceGateBlock> WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), WoodType.OAK));
    public static final Supplier<DoorBlock> WALNUT_DOOR = registerBlock("walnut_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final Supplier<TrapDoorBlock> WALNUT_TRAPDOOR = registerBlock("walnut_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final Supplier<ButtonBlock> WALNUT_BUTTON = registerBlock("walnut_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));
    public static final Supplier<PressurePlateBlock> WALNUT_PRESSURE_PLATE = registerBlock("walnut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Supplier<ModStandingSignBlock> WALNUT_SIGN = CommonPlatformHelper.registerBlock("walnut_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodType.WALNUT));
    public static final Supplier<ModWallSignBlock> WALNUT_WALL_SIGN = CommonPlatformHelper.registerBlock("walnut_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(WALNUT_SIGN.get()), ModWoodType.WALNUT));
    public static final Supplier<ModCeilingHangingSignBlock> WALNUT_HANGING_SIGN = CommonPlatformHelper.registerBlock("walnut_hanging_sign", () -> new ModCeilingHangingSignBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodType.WALNUT));
    public static final Supplier<ModWallHangingSignBlock> WALNUT_WALL_HANGING_SIGN = CommonPlatformHelper.registerBlock("walnut_wall_hanging_sign", () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(WALNUT_HANGING_SIGN.get()), ModWoodType.WALNUT));
    public static final Supplier<SaplingBlock> WALNUT_SAPLING = registerBlock("walnut_sapling", () -> new SaplingBlock(new WalnutTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Supplier<FlowerPotBlock> POTTED_WALNUT_SAPLING = CommonPlatformHelper.registerBlock("potted_walnut_sapling", () -> new FlowerPotBlock(ModBlocks.WALNUT_SAPLING.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final Supplier<SurfaceMossBlock> SURFACE_MOSS = registerBlock("surface_moss", SurfaceMossBlock::new);
    public static final Supplier<MossLayerBlock> MOSS_LAYER = CommonPlatformHelper.registerBlock("moss_layer", MossLayerBlock::new);

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> toReturn = CommonPlatformHelper.registerBlock(name, block);
        CommonPlatformHelper.registerItem(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
}
