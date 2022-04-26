package samebutdifferent.ecologics.compat.farmersdelight;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import java.util.function.Supplier;

public class FDSuppliers {
    public static Supplier<Block> COCONUT_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    public static Supplier<Block> WALNUT_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    public static Supplier<Block> AZALEA_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    public static Supplier<Block> FLOWERING_AZALEA_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    public static Supplier<CreativeModeTab> FD_TAB = () -> FarmersDelight.CREATIVE_TAB;
}