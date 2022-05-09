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

    public static final RegistryObject<Block> COCONUT_BEAM  = registerBeam("coconut_beam", "coconut");
    public static final RegistryObject<Block> COCONUT_SUPPORT = registerSupport("coconut_support", "coconut");
    public static final RegistryObject<Block> COCONUT_PALISADE = registerPalisade("coconut_palisade", "coconut");
    public static final RegistryObject<Block> COCONUT_SEAT = registerSeat("coconut_seat", "coconut");

    public static final RegistryObject<Block> WALNUT_BEAM = registerBeam("walnut_beam", "walnut");
    public static final RegistryObject<Block> WALNUT_SUPPORT = registerSupport("walnut_support", "walnut");
    public static final RegistryObject<Block> WALNUT_PALISADE = registerPalisade("walnut_palisade", "walnut");
    public static final RegistryObject<Block> WALNUT_SEAT = registerSeat("walnut_seat", "walnut");

    public static final RegistryObject<Block> AZALEA_BEAM = registerBeam("azalea_beam", "azalea");
    public static final RegistryObject<Block> AZALEA_SUPPORT = registerSupport("azalea_support", "azalea");
    public static final RegistryObject<Block> AZALEA_PALISADE = registerPalisade("azalea_palisade", "azalea");
    public static final RegistryObject<Block> AZALEA_SEAT = registerSeat("azalea_seat", "azalea");

    public static final RegistryObject<Block> FLOWERING_AZALEA_BEAM = registerBeam("flowering_azalea_beam", "flowering_azalea");
    public static final RegistryObject<Block> FLOWERING_AZALEA_SUPPORT = registerSupport("flowering_azalea_support", "flowering_azalea");
    public static final RegistryObject<Block> FLOWERING_AZALEA_PALISADE = registerPalisade("flowering_azalea_palisade", "flowering_azalea");
    public static final RegistryObject<Block> FLOWERING_AZALEA_SEAT = registerSeat("flowering_azalea_seat", "flowering_azalea");

    private static RegistryObject<Block> registerBeam(String name, String woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createBeamBlock(DBCompatWoodTypes.withName(woodType)) : NoBeamBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerSupport(String name, String woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createSupportBlock(DBCompatWoodTypes.withName(woodType)) : NoSupportBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerPalisade(String name, String woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createPalisadeBlock(DBCompatWoodTypes.withName(woodType)) : NoPalisadeBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerSeat(String name, String woodType) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? DBSuppliers.createSeatBlock(DBCompatWoodTypes.withName(woodType)) : NoSeatBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> regObj) {
        ModItems.ITEMS.register(name, () -> new BlockItem(regObj.get(),
                loaded ? new Item.Properties().tab(DBSuppliers.DB_TAB.get()) : new Item.Properties()));
    }

    public static void init() {}
}
