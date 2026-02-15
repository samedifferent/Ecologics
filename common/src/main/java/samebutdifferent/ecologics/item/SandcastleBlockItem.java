package samebutdifferent.ecologics.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.SandcastleBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

public class SandcastleBlockItem extends BlockItem 
{
    public SandcastleBlockItem(Item.Properties properties) {
        super(ModBlocks.SANDCASTLE, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (!state.is(Blocks.TURTLE_EGG)) {
            return super.useOn(context);
        } else {
            level.playSound(context.getPlayer(), pos, SoundEvents.SAND_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlockAndUpdate(pos, ModBlocks.SANDCASTLE.defaultBlockState().setValue(SandcastleBlock.FACING, context.getHorizontalDirection().getOpposite()).setValue(SandcastleBlock.EGGS_INSIDE, state.getValue(TurtleEggBlock.EGGS)).setValue(SandcastleBlock.HATCH, state.getValue(TurtleEggBlock.HATCH)));
            context.getItemInHand().shrink(1);
            if (context.getPlayer() instanceof ServerPlayer player) {
                CriteriaTriggers.PLACED_BLOCK.trigger(player, pos, context.getItemInHand());
            }
            return InteractionResult.SUCCESS;
        }
    }
}
