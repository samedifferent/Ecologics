package samebutdifferent.ecologics.fabric.integration;

import java.util.function.Consumer;

import com.phantomwing.rusticdelight.block.custom.PancakeBlock;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
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
		MAPLE_PANCAKES = ModBlocks.registerBlock("maple_pancakes", properties -> new PancakeBlock(() -> { return MAPLE_PANCAKE; }, properties), BlockBehaviour.Properties.ofFullCopy(com.phantomwing.rusticdelight.block.ModBlocks.PANCAKES), false);
		ModItems.registerItem("maple_pancakes", properties -> new BlockItem(MAPLE_PANCAKES, properties) {
			@Override @SuppressWarnings("deprecation")
		    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		        super.appendHoverText(stack, context, display, tooltipAdder, tooltipFlag);
		        tooltipAdder.accept(Component.translatable("tooltip.farmersdelight.placeable").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		    }
		}, new Item.Properties().useBlockDescriptionPrefix());
	}
	
	public void registerCreativeTabContent() {
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKE);
		ModCreativeModeTabContents.addToList(MAPLE_PANCAKES);
	}
}
