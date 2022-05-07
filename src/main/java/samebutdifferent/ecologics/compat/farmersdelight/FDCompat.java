package samebutdifferent.ecologics.compat.farmersdelight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class FDCompat {
    public static final String FD_ID = "farmersdelight";
    private static final boolean loaded = ModList.get().isLoaded(FD_ID);

    public static final RegistryObject<Block> COCONUT_CABINET = registerCabinet("coconut_cabinet");
    public static final RegistryObject<Block> WALNUT_CABINET = registerCabinet("walnut_cabinet");
    public static final RegistryObject<Block> AZALEA_CABINET = registerCabinet("azalea_cabinet");
    public static final RegistryObject<Block> FLOWERING_AZALEA_CABINET = registerCabinet("flowering_azalea_cabinet");

    private static RegistryObject<Block> registerCabinet(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? FDSuppliers.CABINET : NoCabinetBlock::new);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                loaded ? new Item.Properties().tab(FDSuppliers.FD_TAB.get()) : new Item.Properties()));
        return toReturn;
    }

    public static void init() {}
}