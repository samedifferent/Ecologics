package samebutdifferent.ecologics.item;

import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.HangingCoconutBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

public class CoconutBlockItem extends BlockItem 
{
    public CoconutBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.is(ModBlocks.COCONUT_LEAVES) && context.getClickedFace() == Direction.DOWN && isFreeForPlacement(level, state, pos.below())) {
            level.playSound(context.getPlayer(), pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlockAndUpdate(pos.below(), ModBlocks.HANGING_COCONUT.defaultBlockState().setValue(HangingCoconutBlock.AGE, 2).setValue(HangingCoconutBlock.PERSISTENT, true));
            context.getItemInHand().shrink(1);
            if (context.getPlayer() instanceof ServerPlayer player) {
                CriteriaTriggers.PLACED_BLOCK.trigger(player, pos, context.getItemInHand());
            }
            return InteractionResult.SUCCESS;
            
        } else {
            return super.useOn(context);
        }
    }
    
    private boolean isFreeForPlacement(Level level, BlockState state, BlockPos pos) {
        return (level.isEmptyBlock(pos) || state.canBeReplaced() || state.is(BlockTags.FIRE));
    }
}
