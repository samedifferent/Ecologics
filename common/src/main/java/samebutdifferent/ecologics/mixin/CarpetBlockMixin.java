package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.MossLayerBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(CarpetBlock.class)
public abstract class CarpetBlockMixin extends Block {

    public CarpetBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
    	if (state.is(Blocks.MOSS_CARPET) && context.getItemInHand().getItem() == Items.MOSS_CARPET) {
    		return context.replacingClickedOnBlock() ? context.getClickedFace() == Direction.UP : true;
    	}
        return super.canBeReplaced(state, context);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
    	BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
    	if (blockstate.is(Blocks.MOSS_CARPET)) {
    		return ModBlocks.MOSS_LAYER.defaultBlockState().setValue(MossLayerBlock.LAYERS, 2);
    	}
    	if (blockstate.is(ModBlocks.MOSS_LAYER)) {
            int layers = blockstate.getValue(MossLayerBlock.LAYERS);
            if (layers + 1 < 8) {
            	return blockstate.setValue(MossLayerBlock.LAYERS, Integer.valueOf(Math.min(8, layers + 1)));
            }
            else {
            	return Blocks.MOSS_BLOCK.defaultBlockState();
            }
    	}
    	return super.getStateForPlacement(context);
    }
}