package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import java.util.Random;

public class CoconutLeavesBlock extends Block implements IForgeShearable {
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 9);

    public CoconutLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, 9).setValue(PERSISTENT, false));
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return Shapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(DISTANCE) == 9 && !pState.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (!pState.getValue(PERSISTENT) && pState.getValue(DISTANCE) == 9) {
            dropResources(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }

    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        int i = getDistanceAt(pFacingState) + 1;
        if (i != 1 || pState.getValue(DISTANCE) != i) {
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

        return pState.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        if (pNeighbor.is(BlockTags.LOGS)) {
            return 0;
        } else {
            return pNeighbor.getBlock() instanceof CoconutLeavesBlock ? pNeighbor.getValue(DISTANCE) : 9;
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        if (pLevel.isRainingAt(pPos.above())) {
            if (pRandom.nextInt(15) == 1) {
                BlockPos blockpos = pPos.below();
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
                    double d0 = (double)pPos.getX() + pRandom.nextDouble();
                    double d1 = (double)pPos.getY() - 0.05D;
                    double d2 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DISTANCE, PERSISTENT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return updateDistance(this.defaultBlockState().setValue(PERSISTENT, true), pContext.getLevel(), pContext.getClickedPos());
    }
}
