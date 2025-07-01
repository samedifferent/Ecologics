package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class CoconutSaplingBlock extends SaplingBlock 
{
	protected final TreeGrower treeGrower;
	
    public CoconutSaplingBlock(TreeGrower treeGrowerIn, Properties properties) {
        super(treeGrowerIn, properties);
        this.treeGrower = treeGrowerIn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.SAND) || pState.is(Blocks.RED_SAND);
    }
}
