package samebutdifferent.ecologics.compat.farmersdelight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import java.util.function.Supplier;

public class FDCompat {
    public static final String FD_ID = "farmersdelight";
    private static final boolean loaded = ModList.get().isLoaded(FD_ID);
    public static final RegistryObject<Block> COCONUT_CABINET = registerBlock("coconut_cabinet",
            loaded ? FDSuppliers.COCONUT_CABINET : NoCabinetBlock::new);
    public static final RegistryObject<Block> WALNUT_CABINET = registerBlock("walnut_cabinet",
            loaded ? FDSuppliers.WALNUT_CABINET : NoCabinetBlock::new);
    public static final RegistryObject<Block> AZALEA_CABINET = registerBlock("azalea_cabinet",
            loaded ? FDSuppliers.AZALEA_CABINET : NoCabinetBlock::new);
    public static final RegistryObject<Block> FLOWERING_AZALEA_CABINET = registerBlock("flowering_azalea_cabinet",
            loaded ? FDSuppliers.FLOWERING_AZALEA_CABINET : NoCabinetBlock::new);

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                loaded ? new Item.Properties().tab(FDSuppliers.FD_TAB.get()) : new Item.Properties()));
        return toReturn;
    }

    public static void init() {}
}