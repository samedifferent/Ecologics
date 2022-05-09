package samebutdifferent.ecologics.compat.decorative_blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.compat.decorative_blocks.block.NoBeamBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.block.NoPalisadeBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.block.NoSeatBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.block.NoSupportBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class DBCompat {
    public static final String DB_ID = "decorative_blocks";
    private static final boolean loaded = ModList.get().isLoaded(DB_ID);

    public static final RegistryObject<Block> COCONUT_BEAM  = registerBeam("coconut_beam", DBCompatWoodTypes.COCONUT);
    public static final RegistryObject<Block> COCONUT_SUPPORT = registerSupport("coconut_support", DBCompatWoodTypes.COCONUT);
    public static final RegistryObject<Block> COCONUT_PALISADE = registerPalisade("coconut_palisade", DBCompatWoodTypes.COCONUT);
    public static final RegistryObject<Block> COCONUT_SEAT = registerSeat("coconut_seat", DBCompatWoodTypes.COCONUT);

    public static final RegistryObject<Block> WALNUT_BEAM = registerBeam("walnut_beam", DBCompatWoodTypes.WALNUT);
    public static final RegistryObject<Block> WALNUT_SUPPORT = registerSupport("walnut_support", DBCompatWoodTypes.WALNUT);
    public static final RegistryObject<Block> WALNUT_PALISADE = registerPalisade("walnut_palisade", DBCompatWoodTypes.WALNUT);
    public static final RegistryObject<Block> WALNUT_SEAT = registerSeat("walnut_seat", DBCompatWoodTypes.WALNUT);

    public static final RegistryObject<Block> AZALEA_BEAM = registerBeam("azalea_beam", DBCompatWoodTypes.AZALEA);
    public static final RegistryObject<Block> AZALEA_SUPPORT = registerSupport("azalea_support", DBCompatWoodTypes.AZALEA);
    public static final RegistryObject<Block> AZALEA_PALISADE = registerPalisade("azalea_palisade", DBCompatWoodTypes.AZALEA);
    public static final RegistryObject<Block> AZALEA_SEAT = registerSeat("azalea_seat", DBCompatWoodTypes.AZALEA);

//    public static final RegistryObject<Block> FLOWERING_AZALEA_BEAM = registerBeam("flowering_azalea_beam", DBCompatWoodTypes.FLOWERING_AZALEA);
//    public static final RegistryObject<Block> FLOWERING_AZALEA_SUPPORT = registerSupport("flowering_azalea_support", DBCompatWoodTypes.FLOWERING_AZALEA);
//    public static final RegistryObject<Block> FLOWERING_AZALEA_PALISADE = registerPalisade("flowering_azalea_palisade", DBCompatWoodTypes.FLOWERING_AZALEA);
//    public static final RegistryObject<Block> FLOWERING_AZALEA_SEAT = registerSeat("flowering_azalea_seat", DBCompatWoodTypes.FLOWERING_AZALEA);

    private static RegistryObject<Block> registerBeam(String name, DBCompatWoodTypes woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createBeamBlock(woodType) : NoBeamBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerSupport(String name, DBCompatWoodTypes woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createSupportBlock(woodType) : NoSupportBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerPalisade(String name, DBCompatWoodTypes woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createPalisadeBlock(woodType) : NoPalisadeBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerSeat(String name, DBCompatWoodTypes woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createSeatBlock(woodType) : NoSeatBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> regObj) {
        ModItems.ITEMS.register(name, () -> new BlockItem(regObj.get(),
                loaded ? new Item.Properties().tab(DBSuppliers.DB_TAB.get()) : new Item.Properties()));
    }

    public static void init() {}
}
