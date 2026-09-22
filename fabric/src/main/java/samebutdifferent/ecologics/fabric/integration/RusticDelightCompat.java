package samebutdifferent.ecologics.fabric.integration;

import com.phantomwing.rusticdelight.block.custom.PancakeBlock;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCompat;
import samebutdifferent.ecologics.registry.ModCreativeModeTabContents;
import samebutdifferent.ecologics.registry.ModFoods;
import samebutdifferent.ecologics.registry.ModItems;

public class RusticDelightCompat implements ModCompat
{
	public final Item MAPLE_PANCAKE;
	public final Block MAPLE_PANCAKES;
	
	public RusticDelightCompat() {
		MAPLE_PANCAKE = ModItems.registerItem("maple_pancake", Item::new, new Item.Properties().food(ModFoods.MAPLE_PANCAKE));
		MAPLE_PANCAKES = ModBlocks.registerBlock("maple_pancakes", properties -> new PancakeBlock(() -> { return MAPLE_PANCAKE; }, properties), BlockBehaviour.Properties.ofFullCopy(com.phantomwing.rusticdelight.block.ModBlocks.PANCAKES), ModBlocks.DEFAULT_BLOCK_ITEM_PROPERTIES);
	}
	
	public void registerCreativeTabContent() {
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKE);
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKES);
	}
}
