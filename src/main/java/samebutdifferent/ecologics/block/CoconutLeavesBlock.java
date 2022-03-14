package samebutdifferent.ecologics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Shearable;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public class CoconutLeavesBlock extends Block implements Shearable {
    public static final BooleanProperty PERSISTENT = Properties.PERSISTENT;
    public static final IntProperty DISTANCE = IntProperty.of("distance", 1, 9);

    public CoconutLeavesBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.stateManager.getDefaultState().with(DISTANCE, 9).with(PERSISTENT, false));
    }

    @Override
    public VoxelShape getSidesShape(BlockState pState, BlockView pReader, BlockPos pPos) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean hasRandomTicks(BlockState pState) {
        return pState.get(DISTANCE) == 9 && !pState.get(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        if (!pState.get(PERSISTENT) && pState.get(DISTANCE) == 9) {
            dropStacks(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }

    }

    @Override
    public void scheduledTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        pLevel.setBlockState(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public int getOpacity(BlockState pState, BlockView pLevel, BlockPos pPos) {
        return 1;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        int i = getDistanceAt(pFacingState) + 1;
        if (i != 1 || pState.get(DISTANCE) != i) {
            pLevel.createAndScheduleBlockTick(pCurrentPos, this, 1);
        }

        return pState;
    }

    private static BlockState updateDistance(BlockState pState, WorldAccess pLevel, BlockPos pPos) {
        int i = 9;
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.set(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return pState.with(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        if (pNeighbor.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return pNeighbor.getBlock() instanceof CoconutLeavesBlock ? pNeighbor.get(DISTANCE) : 9;
        }
    }

    @Override
    public void randomDisplayTick(BlockState pState, World pLevel, BlockPos pPos, Random pRandom) {
        if (pLevel.hasRain(pPos.up())) {
            if (pRandom.nextInt(15) == 1) {
                BlockPos blockpos = pPos.down();
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (!blockstate.isOpaque() || !blockstate.isSideSolidFullSquare(pLevel, blockpos, Direction.UP)) {
                    double d0 = (double)pPos.getX() + pRandom.nextDouble();
                    double d1 = (double)pPos.getY() - 0.05D;
                    double d2 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DISTANCE, PERSISTENT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        return updateDistance(this.getDefaultState().with(PERSISTENT, true), pContext.getWorld(), pContext.getBlockPos());
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        // TODO: WTF IS SUPPOSED TO GO HERE???
    }

    @Override
    public boolean isShearable() {
        return true;
    }
}
