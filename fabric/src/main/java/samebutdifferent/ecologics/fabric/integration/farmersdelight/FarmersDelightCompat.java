package samebutdifferent.ecologics.fabric.integration.farmersdelight;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCompat;
import samebutdifferent.ecologics.registry.ModCreativeModeTabContents;
import samebutdifferent.ecologics.registry.ModFoods;
import samebutdifferent.ecologics.registry.ModItems;

public class FarmersDelightCompat implements ModCompat
{
    public final BlockEntityType<ModCabinetBlockEntity> CABINET_BLOCK_ENTITY;

    public final Item MAPLE_PIE_SLICE;

    public final Block AZALEA_CABINET;
    public final Block FLOWERING_AZALEA_CABINET;
    public final Block COCONUT_CABINET;
    public final Block MAPLE_CABINET;
    public final Block WALNUT_CABINET;

    public FarmersDelightCompat() {
        MAPLE_PIE_SLICE = ModItems.registerItem("maple_pie_slice", new Item(new Item.Properties().food(ModFoods.MAPLE_PIE_SLICE)));
        ModBlocks.MAPLE_PIE = ModBlocks.registerBlock("maple_pie", new MaplePieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> MAPLE_PIE_SLICE), false);

        AZALEA_CABINET = ModBlocks.registerBlock("azalea_cabinet", new ModCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        FLOWERING_AZALEA_CABINET = ModBlocks.registerBlock("flowering_azalea_cabinet", new ModCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        COCONUT_CABINET = ModBlocks.registerBlock("coconut_cabinet", new ModCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        MAPLE_CABINET = ModBlocks.registerBlock("maple_cabinet", new ModCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        WALNUT_CABINET = ModBlocks.registerBlock("walnut_cabinet", new ModCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)), true);
        
        CABINET_BLOCK_ENTITY = ModBlockEntityTypes.registerBlockEntityType("cabinet", BlockEntityType.Builder.of(ModCabinetBlockEntity::new, AZALEA_CABINET, FLOWERING_AZALEA_CABINET, COCONUT_CABINET, MAPLE_CABINET, WALNUT_CABINET).build(null));
    }
    
    public void registerCreativeTabContent() {
        ModCreativeModeTabContents.addToList(MAPLE_PIE_SLICE);
        ModCreativeModeTabContents.addToList(AZALEA_CABINET);
        ModCreativeModeTabContents.addToList(FLOWERING_AZALEA_CABINET);
        ModCreativeModeTabContents.addToList(COCONUT_CABINET);
        ModCreativeModeTabContents.addToList(MAPLE_CABINET);
        ModCreativeModeTabContents.addToList(WALNUT_CABINET);
    }
}
