package samebutdifferent.ecologics.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.*;
import samebutdifferent.ecologics.block.properties.ModWoodType;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ecologics.MOD_ID);

    public static final RegistryObject<RotatedPillarBlock> COCONUT_LOG = registerBlock("coconut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> COCONUT_WOOD = registerBlock("coconut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<CoconutLeavesBlock> COCONUT_LEAVES = registerBlock("coconut_leaves", () -> new CoconutLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> COCONUT_PLANKS = registerBlock("coconut_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<SlabBlock> COCONUT_SLAB = registerBlock("coconut_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairBlock> COCONUT_STAIRS = registerBlock("coconut_stairs", () -> new StairBlock(Blocks.OAK_PLANKS::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<FenceBlock> COCONUT_FENCE = registerBlock("coconut_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<FenceGateBlock> COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<DoorBlock> COCONUT_DOOR = registerBlock("coconut_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<TrapDoorBlock> COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<WoodButtonBlock> COCONUT_BUTTON = registerBlock("coconut_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<PressurePlateBlock> COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<ModStandingSignBlock> COCONUT_SIGN = BLOCKS.register("coconut_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodType.COCONUT));
    public static final RegistryObject<ModWallSignBlock> COCONUT_WALL_SIGN = BLOCKS.register("coconut_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(COCONUT_SIGN.get()), ModWoodType.COCONUT));
    public static final RegistryObject<HangingCoconutBlock> HANGING_COCONUT = BLOCKS.register("hanging_coconut", HangingCoconutBlock::new);
    public static final RegistryObject<CoconutBlock> COCONUT = registerBlock("coconut", CoconutBlock::new);
    public static final RegistryObject<SaplingBlock> COCONUT_HUSK = registerBlock("coconut_husk", CoconutSaplingBlock::new);
    public static final RegistryObject<SeashellBlock> SEASHELL = registerBlock("seashell", SeashellBlock::new);
    public static final RegistryObject<SandcastleBlock> SANDCASTLE = BLOCKS.register("sandcastle", SandcastleBlock::new);
    public static final RegistryObject<Block> SEASHELL_BLOCK = registerBlock("seashell_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(1.5F, 6.0F).sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SEASHELL_TILES = registerBlock("seashell_tiles", () -> new Block(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final RegistryObject<StairBlock> SEASHELL_TILE_STAIRS = registerBlock("seashell_tile_stairs", () -> new StairBlock(() -> SEASHELL_BLOCK.get().defaultBlockState() ,BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final RegistryObject<SlabBlock> SEASHELL_TILE_SLAB = registerBlock("seashell_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final RegistryObject<WallBlock> SEASHELL_TILE_WALL = registerBlock("seashell_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SEASHELL_BLOCK.get())));
    public static final RegistryObject<ThinIceBlock> THIN_ICE = BLOCKS.register("thin_ice", ThinIceBlock::new);
    public static final RegistryObject<PricklyPearBlock> PRICKLY_PEAR = BLOCKS.register("prickly_pear", PricklyPearBlock::new);
    public static final RegistryObject<PotBlock> POT = registerBlock("pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> WHITE_POT = registerBlock("white_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> ORANGE_POT = registerBlock("orange_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> MAGENTA_POT = registerBlock("magenta_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_MAGENTA).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> LIGHT_BLUE_POT = registerBlock("light_blue_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> YELLOW_POT = registerBlock("yellow_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_YELLOW).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> LIME_POT = registerBlock("lime_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GREEN).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> PINK_POT = registerBlock("pink_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> GRAY_POT = registerBlock("gray_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> LIGHT_GRAY_POT = registerBlock("light_gray_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> CYAN_POT = registerBlock("cyan_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_CYAN).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> PURPLE_POT = registerBlock("purple_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PURPLE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> BLUE_POT = registerBlock("blue_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> BROWN_POT = registerBlock("brown_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> GREEN_POT = registerBlock("green_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GREEN).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> RED_POT = registerBlock("red_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<PotBlock> BLACK_POT = registerBlock("black_pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(1.0F)));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(Ecologics.TAB)));
        return toReturn;
    }
}
