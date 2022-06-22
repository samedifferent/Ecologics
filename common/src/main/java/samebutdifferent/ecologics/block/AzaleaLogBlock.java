package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Random;

public class AzaleaLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public AzaleaLogBlock() {
        super(Properties.copy(Blocks.OAK_LOG));
        this.registerDefaultState(this.stateDefinition.any().setValue(PERSISTENT, Boolean.FALSE).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (random.nextInt(25) == 0) {
            world.setBlock(pos, ModBlocks.FLOWERING_AZALEA_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(AXIS)), 2);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(PERSISTENT, Boolean.TRUE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PERSISTENT, AXIS);
    }
}
