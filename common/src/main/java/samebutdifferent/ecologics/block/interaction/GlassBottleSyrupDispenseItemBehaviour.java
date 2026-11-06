package samebutdifferent.ecologics.block.interaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class GlassBottleSyrupDispenseItemBehaviour extends OptionalDispenseItemBehavior 
{
	private final DispenseItemBehavior vanillaFallback;
	
    public GlassBottleSyrupDispenseItemBehaviour(DispenseItemBehavior vanillaBehavior) {
        this.vanillaFallback = vanillaBehavior;
    }
    
    private ItemStack takeLiquid(BlockSource blockSource, ItemStack originalItemStack, ItemStack targetItemStack) {
    	blockSource.level().gameEvent(null, GameEvent.FLUID_PICKUP, blockSource.pos());
        return this.consumeWithRemainder(blockSource, originalItemStack, targetItemStack);
    }
    
    protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
        ServerLevel level = blockSource.level();
        if (level.isClientSide()) {
        	return stack;
        }
        BlockPos pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
        BlockState targetState = level.getBlockState(pos);
        if (targetState.is(ModBlocks.MAPLE_SAP_CAULDRON)) {
        	if (targetState.getValue(MapleSapCauldronBlock.LEVEL) == 1) {
            	level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            	this.setSuccess(true);
            	return this.takeLiquid(blockSource, stack, new ItemStack(ModItems.MAPLE_SYRUP_BOTTLE));
        	}
        }
        return this.vanillaFallback.dispense(blockSource, stack);
    }
}
