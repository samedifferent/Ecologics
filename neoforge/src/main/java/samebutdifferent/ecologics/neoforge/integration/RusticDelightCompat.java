package samebutdifferent.ecologics.neoforge.integration;

import java.util.List;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
		MAPLE_PANCAKE = ModItems.registerItem("maple_pancake", new Item(new Item.Properties().food(ModFoods.MAPLE_PANCAKE)));
		MAPLE_PANCAKES = ModBlocks.registerBlock("maple_pancakes", new PancakeBlock(() -> { return MAPLE_PANCAKE; }, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)), false);
		ModItems.registerItem("maple_pancakes", new BlockItem(MAPLE_PANCAKES, new Item.Properties()) {
			@Override
		    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		        super.appendHoverText(stack, context, tooltipAdder, tooltipFlag);
		        tooltipAdder.add(Component.translatable("tooltip.farmersdelight.placeable").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		    }
		});
	}
	
	public void registerCreativeTabContent() {
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKE);
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKES);
	}
}
