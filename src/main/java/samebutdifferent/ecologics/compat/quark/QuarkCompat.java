package samebutdifferent.ecologics.compat.quark;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.compat.quark.block.*;
import samebutdifferent.ecologics.compat.quark.block.entity.ModChestBlockEntity;
import samebutdifferent.ecologics.compat.quark.block.entity.ModTrappedChestBlockEntity;
import samebutdifferent.ecologics.compat.quark.item.ModChestBlockItem;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.function.Supplier;

public class QuarkCompat {
    public static final RegistryObject<Block> COCONUT_VERTICAL_SLAB = registerBlock("coconut_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.COCONUT_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> WALNUT_VERTICAL_SLAB = registerBlock("walnut_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.WALNUT_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZALEA_VERTICAL_SLAB = registerBlock("azalea_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.AZALEA_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FLOWERING_AZALEA_VERTICAL_SLAB = registerBlock("flowering_azalea_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.FLOWERING_AZALEA_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SEASHELL_TILE_VERTICAL_SLAB = registerBlock("seashell_tile_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.SEASHELL_TILE_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ICE_BRICK_VERTICAL_SLAB = registerBlock("ice_brick_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.ICE_BRICK_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_VERTICAL_SLAB = registerBlock("snow_brick_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.SNOW_BRICK_SLAB.get()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_COCONUT_POST = registerBlock("stripped_coconut_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_WALNUT_POST = registerBlock("stripped_walnut_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_AZALEA_POST = registerBlock("stripped_azalea_post", WoodPostBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> COCONUT_POST = registerBlock("coconut_post", () -> new WoodPostBlock(STRIPPED_COCONUT_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> WALNUT_POST = registerBlock("walnut_post", () -> new WoodPostBlock(STRIPPED_WALNUT_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZALEA_POST = registerBlock("azalea_post", () -> new WoodPostBlock(STRIPPED_AZALEA_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FLOWERING_AZALEA_POST = registerBlock("flowering_azalea_post", () -> new WoodPostBlock(STRIPPED_AZALEA_POST), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> COCONUT_LADDER = registerBlock("coconut_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WALNUT_LADDER = registerBlock("walnut_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> AZALEA_LADDER = registerBlock("azalea_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FLOWERING_AZALEA_LADDER = registerBlock("flowering_azalea_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COCONUT_BOOKSHELF = registerBlock("coconut_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> WALNUT_BOOKSHELF = registerBlock("walnut_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZALEA_BOOKSHELF = registerBlock("azalea_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FLOWERING_AZALEA_BOOKSHELF = registerBlock("flowering_azalea_bookshelf", BookshelfBlock::new, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> COCONUT_LEAF_CARPET = registerBlock("coconut_leaf_carpet", LeafCarpetBlock::new, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WALNUT_LEAF_CARPET = registerBlock("walnut_leaf_carpet", LeafCarpetBlock::new, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COCONUT_HEDGE = registerBlock("coconut_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WALNUT_HEDGE = registerBlock("walnut_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> AZALEA_HEDGE = registerBlock("azalea_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FLOWERING_AZALEA_HEDGE = registerBlock("flowering_azalea_hedge", HedgeBlock::new, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> VERTICAL_COCONUT_PLANKS = registerBlock("vertical_coconut_planks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.COCONUT_PLANKS.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> VERTICAL_WALNUT_PLANKS = registerBlock("vertical_walnut_planks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.WALNUT_PLANKS.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> VERTICAL_AZALEA_PLANKS = registerBlock("vertical_azalea_planks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.AZALEA_PLANKS.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> VERTICAL_FLOWERING_AZALEA_PLANKS = registerBlock("vertical_flowering_azalea_planks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.FLOWERING_AZALEA_PLANKS.get())), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COCONUT_CHEST = registerChest("coconut_chest", () -> new ModChestBlock(ChestVariant.COCONUT));
    public static final RegistryObject<Block> WALNUT_CHEST = registerChest("walnut_chest", () -> new ModChestBlock(ChestVariant.WALNUT));
    public static final RegistryObject<Block> AZALEA_CHEST = registerChest("azalea_chest", () -> new ModChestBlock(ChestVariant.AZALEA));
    public static final RegistryObject<Block> FLOWERING_AZALEA_CHEST = registerChest("flowering_azalea_chest", () -> new ModChestBlock(ChestVariant.FLOWERING_AZALEA));
    public static final RegistryObject<Block> COCONUT_TRAPPED_CHEST = registerChest("coconut_trapped_chest", () -> new ModTrappedChestBlock(ChestVariant.COCONUT_TRAPPED));
    public static final RegistryObject<Block> WALNUT_TRAPPED_CHEST = registerChest("walnut_trapped_chest", () -> new ModTrappedChestBlock(ChestVariant.WALNUT_TRAPPED));
    public static final RegistryObject<Block> AZALEA_TRAPPED_CHEST = registerChest("azalea_trapped_chest", () -> new ModTrappedChestBlock(ChestVariant.AZALEA_TRAPPED));
    public static final RegistryObject<Block> FLOWERING_AZALEA_TRAPPED_CHEST = registerChest("flowering_azalea_trapped_chest", () -> new ModTrappedChestBlock(ChestVariant.FLOWERING_AZALEA_TRAPPED));

    public static final RegistryObject<BlockEntityType<ModChestBlockEntity>> CHEST = ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register("chest", () -> BlockEntityType.Builder.of(ModChestBlockEntity::new, COCONUT_CHEST.get(), WALNUT_CHEST.get(), AZALEA_CHEST.get(), FLOWERING_AZALEA_CHEST.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModTrappedChestBlockEntity>> TRAPPED_CHEST = ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register("trapped_chest", () -> BlockEntityType.Builder.of(ModTrappedChestBlockEntity::new, COCONUT_TRAPPED_CHEST.get(), WALNUT_TRAPPED_CHEST.get(), AZALEA_TRAPPED_CHEST.get(), FLOWERING_AZALEA_TRAPPED_CHEST.get()).build(null));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(tab)));
        return toReturn;
    }

    public static <T extends Block> RegistryObject<T> registerChest(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new ModChestBlockItem(toReturn.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        return toReturn;
    }

    public static void init() {
    }
}
