package samebutdifferent.ecologics.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MaplePieItem extends BlockItem
{
    public MaplePieItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }
    
    @Override
    public Block getBlock() {
        return Ecologics.farmersDelight != null ? ModBlocks.MAPLE_PIE : Blocks.CAKE; // Include a fallback.
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (Ecologics.farmersDelight != null) {
            InteractionResult result = this.place(new BlockPlaceContext(context));
            if (result.consumesAction()) {
                return context.getLevel().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, tooltipAdder, tooltipFlag);
        if (Ecologics.farmersDelight != null) {
            tooltipAdder.accept(Component.translatable("tooltip.farmersdelight.placeable").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}
