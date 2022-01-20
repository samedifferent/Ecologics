package samebutdifferent.ecologics.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.*;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ecologics.MOD_ID);

    public static final RegistryObject<RotatedPillarBlock> COCONUT_LOG = registerBlock("coconut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> COCONUT_WOOD = registerBlock("coconut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<LeavesBlock> COCONUT_LEAVES = registerBlock("coconut_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> COCONUT_PLANKS = registerBlock("coconut_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<SlabBlock> COCONUT_SLAB = registerBlock("coconut_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairBlock> COCONUT_STAIRS = registerBlock("coconut_stairs", () -> new StairBlock(Blocks.OAK_PLANKS::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<FenceBlock> COCONUT_FENCE = registerBlock("coconut_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<FenceGateBlock> COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<DoorBlock> COCONUT_DOOR = registerBlock("coconut_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<TrapDoorBlock> COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<WoodButtonBlock> COCONUT_BUTTON = registerBlock("coconut_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<PressurePlateBlock> COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<HangingCoconutBlock> HANGING_COCONUT = BLOCKS.register("hanging_coconut", HangingCoconutBlock::new);
    public static final RegistryObject<CoconutBlock> COCONUT = registerBlock("coconut", CoconutBlock::new);
    public static final RegistryObject<SaplingBlock> COCONUT_HUSK = registerBlock("coconut_husk", CoconutSaplingBlock::new);
    public static final RegistryObject<SeashellBlock> SEASHELL = registerBlock("seashell", SeashellBlock::new);
    public static final RegistryObject<SandcastleBlock> SANDCASTLE = registerBlock("sandcastle", SandcastleBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(Ecologics.TAB)));
        return toReturn;
    }
}
