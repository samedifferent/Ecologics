package samebutdifferent.ecologics.block.interaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MapleSapBucketDispenseItemBehaviour extends OptionalDispenseItemBehavior 
{
    private ItemStack emptyLiquid(BlockSource blockSource, ItemStack originalItemStack, ItemStack targetItemStack) {
    	blockSource.level().gameEvent(null, GameEvent.FLUID_PLACE, blockSource.pos());
        return this.consumeWithRemainder(blockSource, originalItemStack, targetItemStack);
    }
    
    protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
        ServerLevel level = blockSource.level();
        if (level.isClientSide()) {
        	return stack;
        }
        BlockPos pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
        BlockState targetState = level.getBlockState(pos);
        if (targetState.is(Blocks.CAULDRON)) {
        	level.setBlockAndUpdate(pos, ModBlocks.MAPLE_SAP_CAULDRON.defaultBlockState().setValue(MapleSapCauldronBlock.LEVEL, 3));
        	this.setSuccess(true);
        	return this.emptyLiquid(blockSource, stack, new ItemStack(Items.BUCKET));
        }
        return super.execute(blockSource, stack);
    }
}
