package samebutdifferent.ecologics.compat.farmersdelight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class FDCompat {
    public static final RegistryObject<Block> COCONUT_CABINET;
    public static final RegistryObject<Block> WALNUT_CABINET;
    public static final RegistryObject<Block> AZALEA_CABINET;
    public static final RegistryObject<Block> FLOWERING_AZALEA_CABINET;

    static {
        COCONUT_CABINET = registerBlock("coconut_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
        WALNUT_CABINET = registerBlock("walnut_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
        AZALEA_CABINET = registerBlock("azalea_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
        FLOWERING_AZALEA_CABINET = registerBlock("flowering_azalea_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(FarmersDelight.CREATIVE_TAB)));
        return toReturn;
    }

    public static void init() {
    }
}
