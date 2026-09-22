package samebutdifferent.ecologics.fabric.integration.farmersdelight;

import java.util.Set;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCompat;
import samebutdifferent.ecologics.registry.ModConsumables;
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
        MAPLE_PIE_SLICE = ModItems.registerItem("maple_pie_slice", Item::new, new Item.Properties().food(ModFoods.MAPLE_PIE_SLICE, ModConsumables.MAPLE_PIE_SLICE));
        ModBlocks.MAPLE_PIE = ModBlocks.registerBlock("maple_pie", properties -> new MaplePieBlock(properties, () -> MAPLE_PIE_SLICE), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.PUMPKIN_PIE.get()), null);

		AZALEA_CABINET = ModBlocks.registerBlock("azalea_cabinet", properties -> new ModCabinetBlock(properties), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.OAK_CABINET.get()), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
		FLOWERING_AZALEA_CABINET = ModBlocks.registerBlock("flowering_azalea_cabinet", properties -> new ModCabinetBlock(properties), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.OAK_CABINET.get()), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
		COCONUT_CABINET = ModBlocks.registerBlock("coconut_cabinet", properties -> new ModCabinetBlock(properties), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.OAK_CABINET.get()), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
		MAPLE_CABINET = ModBlocks.registerBlock("maple_cabinet", properties -> new ModCabinetBlock(properties), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.OAK_CABINET.get()), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
		WALNUT_CABINET = ModBlocks.registerBlock("walnut_cabinet", properties -> new ModCabinetBlock(properties), BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.OAK_CABINET.get()), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
		
		CABINET_BLOCK_ENTITY = ModBlockEntityTypes.registerBlockEntityType("cabinet", new BlockEntityType<>(ModCabinetBlockEntity::new, Set.of(AZALEA_CABINET, FLOWERING_AZALEA_CABINET, COCONUT_CABINET, MAPLE_CABINET, WALNUT_CABINET)));
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
