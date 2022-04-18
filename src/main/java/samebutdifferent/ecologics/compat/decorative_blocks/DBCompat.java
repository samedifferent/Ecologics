package samebutdifferent.ecologics.compat.decorative_blocks;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.function.Supplier;

public class DBCompat {
    public static final RegistryObject<Block> COCONUT_BEAM;
    public static final RegistryObject<Block> COCONUT_SUPPORT;
    public static final RegistryObject<Block> COCONUT_PALISADE;
    public static final RegistryObject<Block> COCONUT_SEAT;

    public static final RegistryObject<Block> WALNUT_BEAM;
    public static final RegistryObject<Block> WALNUT_SUPPORT;
    public static final RegistryObject<Block> WALNUT_PALISADE;
    public static final RegistryObject<Block> WALNUT_SEAT;

    public static final RegistryObject<Block> AZALEA_BEAM;
    public static final RegistryObject<Block> AZALEA_SUPPORT;
    public static final RegistryObject<Block> AZALEA_PALISADE;
    public static final RegistryObject<Block> AZALEA_SEAT;

//    public static final RegistryObject<Block> FLOWERING_AZALEA_BEAM;
//    public static final RegistryObject<Block> FLOWERING_AZALEA_SUPPORT;
//    public static final RegistryObject<Block> FLOWERING_AZALEA_PALISADE;
//    public static final RegistryObject<Block> FLOWERING_AZALEA_SEAT;

    static {
        COCONUT_BEAM = registerBlock("coconut_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.BEAM));
        COCONUT_SUPPORT = registerBlock("coconut_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.SUPPORT));
        COCONUT_PALISADE = registerBlock("coconut_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.PALISADE));
        COCONUT_SEAT = registerBlock("coconut_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.SEAT));

        WALNUT_BEAM = registerBlock("walnut_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.BEAM));
        WALNUT_SUPPORT = registerBlock("walnut_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.SUPPORT));
        WALNUT_PALISADE = registerBlock("walnut_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.PALISADE));
        WALNUT_SEAT = registerBlock("walnut_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.SEAT));

        AZALEA_BEAM = registerBlock("azalea_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.BEAM));
        AZALEA_SUPPORT = registerBlock("azalea_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.SUPPORT));
        AZALEA_PALISADE = registerBlock("azalea_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.PALISADE));
        AZALEA_SEAT = registerBlock("azalea_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.SEAT));

//        FLOWERING_AZALEA_BEAM = registerBlock("flowering_azalea_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.BEAM));
//        FLOWERING_AZALEA_SUPPORT = registerBlock("flowering_azalea_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.SUPPORT));
//        FLOWERING_AZALEA_PALISADE = registerBlock("flowering_azalea_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.PALISADE));
//        FLOWERING_AZALEA_SEAT = registerBlock("flowering_azalea_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.SEAT));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(Constants.ITEM_GROUP)));
        return toReturn;
    }
    
    public static void init() {
    }
}
