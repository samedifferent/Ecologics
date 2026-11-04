package samebutdifferent.ecologics.block.interaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import samebutdifferent.ecologics.block.SpileBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class BucketSpileDispenseItemBehaviour extends OptionalDispenseItemBehavior 
{
	private final DefaultDispenseItemBehavior defaultDispense = new DefaultDispenseItemBehavior();
	private final DispenseItemBehavior vanillaFallback;
	
    public BucketSpileDispenseItemBehaviour(DispenseItemBehavior vanillaBehavior) {
        this.vanillaFallback = vanillaBehavior;
    }
	
    @Override
    protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
        ServerLevel level = blockSource.level();
        if (level.isClientSide()) {
        	return stack;
        }

        Direction facing = blockSource.state().getValue(DispenserBlock.FACING);
        BlockPos targetPos = blockSource.pos().relative(facing);
        BlockState targetState = level.getBlockState(targetPos);

        if (targetState.getBlock() instanceof SpileBlock) {
            boolean hasBucketOnSpile = targetState.hasProperty(SpileBlock.HAS_BUCKET) && targetState.getValue(SpileBlock.HAS_BUCKET);
            int fullness = targetState.hasProperty(SpileBlock.FULLNESS) ? targetState.getValue(SpileBlock.FULLNESS) : 0;
            if (stack.is(Items.BUCKET) && hasBucketOnSpile && fullness >= 3) {
                this.setSuccess(true);
                ItemStack rewardItem = tryExtractSpileBucket(blockSource, level, targetPos, true);
                stack.shrink(1);

                if (stack.isEmpty()) {
                    return rewardItem;
                }
                else {
                    DispenserBlockEntity dispenser = blockSource.blockEntity();
                    ItemStack remaining = dispenser.insertItem(rewardItem);
                    if (!remaining.isEmpty()) {
                        defaultDispense.dispense(blockSource, remaining);
                    }
                    return stack;
                }
            }
            if (stack.is(Items.BUCKET) && !hasBucketOnSpile) {
                boolean placed = tryPlaceSpileBucket(blockSource, level, targetPos);
                this.setSuccess(placed);
                if (placed) {
                    stack.shrink(1);
                }
                return stack;
            }
        }
        return this.vanillaFallback.dispense(blockSource, stack);
    }
    
    private static ItemStack tryExtractSpileBucket(BlockSource blockSource, ServerLevel level, BlockPos pos, boolean chainedSwap) {
        BlockState blockState = level.getBlockState(pos);
        if (blockState.is(ModBlocks.SPILE)) {
            boolean hasBucket = blockState.getValue(SpileBlock.HAS_BUCKET);
            if (hasBucket) {
            	boolean isFull = blockState.getValue(SpileBlock.FULLNESS) >= 3;
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                BlockState newState = blockState.setValue(SpileBlock.FULLNESS, 0);
                if (!chainedSwap) {
                    newState = newState.setValue(SpileBlock.HAS_BUCKET, false);
                }
                level.setBlockAndUpdate(pos, newState);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
                return isFull ? new ItemStack(ModItems.MAPLE_SAP_BUCKET) : new ItemStack(Items.BUCKET);
            }
        }
        return ItemStack.EMPTY;
    }
    
    private static boolean tryPlaceSpileBucket(BlockSource blockSource, ServerLevel level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        if (blockState.is(ModBlocks.SPILE)) {
        	boolean hasBucket = blockState.getValue(SpileBlock.HAS_BUCKET);
            if (!hasBucket) {
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(pos, blockState.setValue(SpileBlock.HAS_BUCKET, true));
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
                return true;
            }
        }
        return false;
    }
}
