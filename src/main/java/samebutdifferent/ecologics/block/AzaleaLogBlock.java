package samebutdifferent.ecologics.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Random;

public class AzaleaLogBlock extends PillarBlock {
    public static final BooleanProperty PERSISTENT = Properties.PERSISTENT;

    public AzaleaLogBlock() {
        super(AbstractBlock.Settings.copy(Blocks.OAK_LOG));
        this.setDefaultState(this.getStateManager().getDefaultState().with(PERSISTENT, Boolean.FALSE).with(AXIS, Direction.Axis.Y));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(25) == 0) {
            world.setBlockState(pos, ModBlocks.FLOWERING_AZALEA_LOG.getDefaultState().with(PillarBlock.AXIS, state.get(AXIS)));
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(PERSISTENT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis()).with(PERSISTENT, true);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PERSISTENT, AXIS);
    }
}