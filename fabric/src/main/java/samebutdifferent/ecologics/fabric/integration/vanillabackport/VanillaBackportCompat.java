package samebutdifferent.ecologics.fabric.integration.vanillabackport;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;
import samebutdifferent.ecologics.block.MossLayerBlock;
import samebutdifferent.ecologics.config.ConfigCommon;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCompat;
import samebutdifferent.ecologics.registry.ModCreativeModeTabContents;
import samebutdifferent.ecologics.registry.ModCreativeModeTabContents.Operation;

public class VanillaBackportCompat implements ModCompat
{
    public final BlockEntityType<ModShelfBlockEntity> SHELF_BLOCK_ENTITY;
    
    public final Block PALE_MOSS_LAYER;
    public final Block AZALEA_SHELF;
    public final Block COCONUT_SHELF;
    public final Block MAPLE_SHELF;
    public final Block WALNUT_SHELF;
    
    public VanillaBackportCompat() {
        PALE_MOSS_LAYER = ModBlocks.registerBlock("pale_moss_layer", new MossLayerBlock(MapColor.COLOR_GRAY));
        AZALEA_SHELF = ModBlocks.registerBlock("azalea_shelf", new ModShelfBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        COCONUT_SHELF = ModBlocks.registerBlock("coconut_shelf", new ModShelfBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        MAPLE_SHELF = ModBlocks.registerBlock("maple_shelf", new ModShelfBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        WALNUT_SHELF = ModBlocks.registerBlock("walnut_shelf", new ModShelfBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        
        SHELF_BLOCK_ENTITY = ModBlockEntityTypes.registerBlockEntityType("shelf", BlockEntityType.Builder.of(ModShelfBlockEntity::new, AZALEA_SHELF, COCONUT_SHELF, MAPLE_SHELF, WALNUT_SHELF).build(null));
    }
    
    @Override
    public void registerCreativeTabContent() {
        ModCreativeModeTabContents.addToList(PALE_MOSS_LAYER);
        ModCreativeModeTabContents.addToList(COCONUT_SHELF);
        ModCreativeModeTabContents.addToList(MAPLE_SHELF);
        ModCreativeModeTabContents.addToList(WALNUT_SHELF);
        ModCreativeModeTabContents.addToList(AZALEA_SHELF);
        if (ConfigCommon.getUseVanillaTabs()) {
            ModCreativeModeTabContents.addToList(PALE_MOSS_LAYER, CreativeModeTabs.NATURAL_BLOCKS, Operation.AFTER, ModBlocks.MOSS_LAYER);
            ModCreativeModeTabContents.addToList(COCONUT_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.BEFORE, Blocks.LECTERN);
            ModCreativeModeTabContents.addToList(MAPLE_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, COCONUT_SHELF);
            ModCreativeModeTabContents.addToList(WALNUT_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, MAPLE_SHELF);
            ModCreativeModeTabContents.addToList(AZALEA_SHELF, CreativeModeTabs.FUNCTIONAL_BLOCKS, Operation.AFTER, WALNUT_SHELF);
        }
    }
}
