package samebutdifferent.ecologics.compat.quark;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.compat.quark.block.*;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.function.Supplier;

public class QuarkCompat {
    public static final RegistryObject<Block> COCONUT_VERTICAL_SLAB;
    public static final RegistryObject<Block> WALNUT_VERTICAL_SLAB;
    public static final RegistryObject<Block> AZALEA_VERTICAL_SLAB;
    public static final RegistryObject<Block> FLOWERING_AZALEA_VERTICAL_SLAB;

    public static final RegistryObject<Block> STRIPPED_COCONUT_POST;
    public static final RegistryObject<Block> STRIPPED_WALNUT_POST;
    public static final RegistryObject<Block> STRIPPED_AZALEA_POST;

    public static final RegistryObject<Block> COCONUT_POST;
    public static final RegistryObject<Block> WALNUT_POST;
    public static final RegistryObject<Block> AZALEA_POST;
    public static final RegistryObject<Block> FLOWERING_AZALEA_POST;

    public static final RegistryObject<Block> COCONUT_LADDER;
    public static final RegistryObject<Block> WALNUT_LADDER;
    public static final RegistryObject<Block> AZALEA_LADDER;
    public static final RegistryObject<Block> FLOWERING_AZALEA_LADDER;

    public static final RegistryObject<Block> COCONUT_BOOKSHELF;
    public static final RegistryObject<Block> WALNUT_BOOKSHELF;
    public static final RegistryObject<Block> AZALEA_BOOKSHELF;
    public static final RegistryObject<Block> FLOWERING_AZALEA_BOOKSHELF;

    public static final RegistryObject<Block> COCONUT_LEAF_CARPET;
    public static final RegistryObject<Block> WALNUT_LEAF_CARPET;

    public static final RegistryObject<Block> COCONUT_HEDGE;
    public static final RegistryObject<Block> WALNUT_HEDGE;

    static {
        COCONUT_VERTICAL_SLAB = registerBlock("coconut_vertical_slab", VerticalSlabBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        WALNUT_VERTICAL_SLAB = registerBlock("walnut_vertical_slab", VerticalSlabBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        AZALEA_VERTICAL_SLAB = registerBlock("azalea_vertical_slab", VerticalSlabBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        FLOWERING_AZALEA_VERTICAL_SLAB = registerBlock("flowering_azalea_vertical_slab", VerticalSlabBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);

        STRIPPED_COCONUT_POST = registerBlock("stripped_coconut_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        STRIPPED_WALNUT_POST = registerBlock("stripped_walnut_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        STRIPPED_AZALEA_POST = registerBlock("stripped_azalea_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);

        COCONUT_POST = registerBlock("coconut_post", () -> new WoodPostBlock(STRIPPED_COCONUT_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
        WALNUT_POST = registerBlock("walnut_post", () -> new WoodPostBlock(STRIPPED_WALNUT_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
        AZALEA_POST = registerBlock("azalea_post", () -> new WoodPostBlock(STRIPPED_AZALEA_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
        FLOWERING_AZALEA_POST = registerBlock("flowering_azalea_post", () -> new WoodPostBlock(STRIPPED_AZALEA_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);

        COCONUT_LADDER = registerBlock("coconut_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
        WALNUT_LADDER = registerBlock("walnut_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
        AZALEA_LADDER = registerBlock("azalea_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
        FLOWERING_AZALEA_LADDER = registerBlock("flowering_azalea_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);

        COCONUT_BOOKSHELF = registerBlock("coconut_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        WALNUT_BOOKSHELF = registerBlock("walnut_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        AZALEA_BOOKSHELF = registerBlock("azalea_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
        FLOWERING_AZALEA_BOOKSHELF = registerBlock("flowering_azalea_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);

        COCONUT_LEAF_CARPET = registerBlock("coconut_leaf_carpet", LeafCarpetBlock::new, CreativeModeTab.TAB_DECORATIONS);
        WALNUT_LEAF_CARPET = registerBlock("walnut_leaf_carpet", LeafCarpetBlock::new, CreativeModeTab.TAB_DECORATIONS);

        COCONUT_HEDGE = registerBlock("coconut_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);
        WALNUT_HEDGE = registerBlock("walnut_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(tab)));
        return toReturn;
    }

    public static void init() {
    }
}
