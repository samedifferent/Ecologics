package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class PalmLeavesBlock extends LeavesBlock {
    public static final IntegerProperty DISTANCE_9 = IntegerProperty.create("distance_9", 1, 9);

    public PalmLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE_9, 9).setValue(PERSISTENT, false).setValue(DISTANCE, 7).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(DISTANCE_9) == 9 && !state.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (this.decaying(blockState)) {
            LeavesBlock.dropResources(blockState, serverLevel, blockPos);
            serverLevel.removeBlock(blockPos, false);
        }
    }

    @Override
    protected boolean decaying(BlockState pState) {
        return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_9) == 9;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

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
            return pNeighbor.getBlock() instanceof PalmLeavesBlock ? pNeighbor.getValue(DISTANCE_9) : 9;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(DISTANCE_9);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return updateDistance(blockstate, pContext.getLevel(), pContext.getClickedPos());
    }
}
