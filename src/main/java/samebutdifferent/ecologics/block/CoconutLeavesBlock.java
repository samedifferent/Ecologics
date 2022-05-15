package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

public class CoconutLeavesBlock extends LeavesBlock {
    public static final IntegerProperty DISTANCE_9 = IntegerProperty.create("distance_9", 1, 9);

    public CoconutLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE_9, 9).setValue(PERSISTENT, false).setValue(DISTANCE, 7));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(DISTANCE_9) == 9 && !pState.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (!pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_9) == 9) {
            dropResources(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }

    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        int i = getDistanceAt(pFacingState) + 1;
        if (i != 1 || pState.getValue(DISTANCE_9) != i) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }

    private static BlockState updateDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = 9;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return pState.setValue(DISTANCE_9, i);
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        if (pNeighbor.is(BlockTags.LOGS)) {
            return 0;
        } else {
            return pNeighbor.getBlock() instanceof CoconutLeavesBlock ? pNeighbor.getValue(DISTANCE_9) : 9;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DISTANCE_9, PERSISTENT, DISTANCE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return updateDistance(this.defaultBlockState().setValue(PERSISTENT, true), pContext.getLevel(), pContext.getClickedPos());
    }
}
