package samebutdifferent.ecologics.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.*;
import samebutdifferent.ecologics.block.properties.ModWoodType;

import java.util.function.Supplier;

public class ModBlocks {
    public static final PillarBlock COCONUT_LOG = registerBlock("coconut_log", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final PillarBlock STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final PillarBlock COCONUT_WOOD = registerBlock("coconut_wood", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final PillarBlock STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final CoconutLeavesBlock COCONUT_LEAVES = registerBlock("coconut_leaves", () -> new CoconutLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final PillarBlock WALNUT_LOG = registerBlock("walnut_log", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final PillarBlock STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final PillarBlock WALNUT_WOOD = registerBlock("walnut_wood", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final PillarBlock STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood", () -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final CoconutLeavesBlock WALNUT_LEAVES = registerBlock("walnut_leaves", () -> new CoconutLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block WALNUT_PLANKS = registerBlock("walnut_planks", () -> new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final SlabBlock WALNUT_SLAB = registerBlock("walnut_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final StairsBlock WALNUT_STAIRS = registerBlock("walnut_stairs", () -> new StairsBlock(Blocks.OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final FenceBlock WALNUT_FENCE = registerBlock("walnut_fence", () -> new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final ModStandingSignBlock WALNUT_SIGN = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "walnut_sign"), new ModStandingSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), ModWoodType.WALNUT));
    public static final ModWallSignBlock WALNUT_WALL_SIGN = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "walnut_wall_sign"), new ModWallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(WALNUT_SIGN), ModWoodType.WALNUT));
    public static final FenceGateBlock WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate", () -> new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block COCONUT_PLANKS = registerBlock("coconut_planks", () -> new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final SlabBlock COCONUT_SLAB = registerBlock("coconut_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final StairsBlock COCONUT_STAIRS = registerBlock("coconut_stairs", () -> new StairsBlock(Blocks.OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final FenceBlock COCONUT_FENCE = registerBlock("coconut_fence", () -> new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final FenceGateBlock COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate", () -> new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final DoorBlock COCONUT_DOOR = registerBlock("coconut_door", () -> new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final TrapdoorBlock COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor", () -> new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    public static final WoodenButtonBlock COCONUT_BUTTON = registerBlock("coconut_button", () -> new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final PressurePlateBlock COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final ModStandingSignBlock COCONUT_SIGN = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "coconut_sign"), new ModStandingSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), ModWoodType.COCONUT));
    public static final ModWallSignBlock COCONUT_WALL_SIGN = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "coconut_wall_sign"), new ModWallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(COCONUT_SIGN), ModWoodType.COCONUT));
    public static final HangingCoconutBlock HANGING_COCONUT = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "hanging_coconut"), new HangingCoconutBlock());
    public static final CoconutBlock COCONUT = registerBlock("coconut", CoconutBlock::new);
    public static final SaplingBlock COCONUT_HUSK = registerBlock("coconut_husk", CoconutSaplingBlock::new);
    public static final SeashellBlock SEASHELL = registerBlock("seashell", SeashellBlock::new);
    public static final SandcastleBlock SANDCASTLE = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "sandcastle"), new SandcastleBlock());
    public static final Block SEASHELL_BLOCK = registerBlock("seashell_block", () -> new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.BROWN).strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_TILES).requiresTool()));
    public static final Block SEASHELL_TILES = registerBlock("seashell_tiles", () -> new Block(AbstractBlock.Settings.copy(SEASHELL_BLOCK)));
    public static final StairsBlock SEASHELL_TILE_STAIRS = registerBlock("seashell_tile_stairs", () -> new StairsBlock(SEASHELL_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(SEASHELL_BLOCK)));
    public static final SlabBlock SEASHELL_TILE_SLAB = registerBlock("seashell_tile_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(SEASHELL_BLOCK)));
    public static final WallBlock SEASHELL_TILE_WALL = registerBlock("seashell_tile_wall", () -> new WallBlock(AbstractBlock.Settings.copy(SEASHELL_BLOCK)));
    public static final ThinIceBlock THIN_ICE = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "thin_ice"), new ThinIceBlock());
    public static final Block SNOW_BRICKS = registerBlock("snow_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final StairsBlock SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs", () -> new StairsBlock(SNOW_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SNOW_BRICKS)));
    public static final SlabBlock SNOW_BRICK_SLAB = registerBlock("snow_brick_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(SNOW_BRICKS)));
    public static final WallBlock SNOW_BRICK_WALL = registerBlock("snow_brick_wall", () -> new WallBlock(AbstractBlock.Settings.copy(SNOW_BRICKS)));
    public static final Block ICE_BRICKS = registerBlock("ice_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final StairsBlock ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs", () -> new StairsBlock(ICE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SNOW_BRICKS)));
    public static final SlabBlock ICE_BRICK_SLAB = registerBlock("ice_brick_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(ICE_BRICKS)));
    public static final WallBlock ICE_BRICK_WALL = registerBlock("ice_brick_wall", () -> new WallBlock(AbstractBlock.Settings.copy(ICE_BRICKS)));
    public static final PricklyPearBlock PRICKLY_PEAR = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, "prickly_pear"), new PricklyPearBlock());
    public static final PotBlock POT = registerBlock("pot", () -> new PotBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.ORANGE).requiresTool().strength(1.0F)));

    private static <T extends Block> T registerBlock(String name, Supplier<T> block) {
        //T toReturn = ModBlocks.BLOCKS.register(name, block);
        T toReturn = Registry.register(Registry.BLOCK, new Identifier(Ecologics.MOD_ID, name), block.get());
        Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, name), new BlockItem(toReturn, new Item.Settings().group(Ecologics.TAB)));
        return toReturn;
    }
}
