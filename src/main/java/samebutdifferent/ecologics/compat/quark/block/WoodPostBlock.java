package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class WoodPostBlock extends Block {
    private static final VoxelShape SHAPE_X = Block.box(0F, 6F, 6F, 16F, 10F, 10F);
    private static final VoxelShape SHAPE_Y = Block.box(6F, 0F, 6F, 10F, 16F, 10F);
    private static final VoxelShape SHAPE_Z = Block.box(6F, 6F, 0F, 10F, 10F, 16F);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty[] CHAINED = new BooleanProperty[] {
            BooleanProperty.create("chain_down"),
            BooleanProperty.create("chain_up"),
            BooleanProperty.create("chain_north"),
            BooleanProperty.create("chain_south"),
            BooleanProperty.create("chain_west"),
            BooleanProperty.create("chain_east")
    };

    @Nullable
    private final Supplier<Block> strippedPostBlock;

    public WoodPostBlock() {
        this(null);
    }

    public WoodPostBlock(@Nullable Supplier<Block> strippedPostBlock) {
        super(Properties.copy(Blocks.OAK_PLANKS));
        this.strippedPostBlock = strippedPostBlock;

        BlockState state = stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Direction.Axis.Y);
        for (BooleanProperty prop : CHAINED)
            state = state.setValue(prop, false);
        this.registerDefaultState(state);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            default -> SHAPE_Z;
        };
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getState(context.getLevel(), context.getClickedPos(), context.getClickedFace().getAxis());
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    @Override
    public void neighborChanged(@Nonnull BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);

        BlockState newState = getState(worldIn, pos, state.getValue(AXIS));
        if (!newState.equals(state))
            worldIn.setBlockAndUpdate(pos, newState);
    }

    private BlockState getState(Level world, BlockPos pos, Direction.Axis axis) {
        BlockState state = defaultBlockState().setValue(WATERLOGGED, world.getFluidState(pos).getType() == Fluids.WATER).setValue(AXIS, axis);

        for (Direction direction : Direction.values()) {
            if (direction.getAxis() == axis)
                continue;

            BlockState sideState = world.getBlockState(pos.relative(direction));
            if ((sideState.getBlock() instanceof ChainBlock && sideState.getValue(BlockStateProperties.AXIS) == direction.getAxis()) || (direction == Direction.DOWN && sideState.getBlock() instanceof LanternBlock && sideState.getValue(LanternBlock.HANGING))) {
                BooleanProperty prop = CHAINED[direction.ordinal()];
                state = state.setValue(prop, true);
            }
        }

        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);
        for (BooleanProperty property : CHAINED)
            builder.add(property);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        if (ToolActions.AXE_STRIP.equals(toolAction) && strippedPostBlock != null && strippedPostBlock.get() instanceof WoodPostBlock) {
            BlockState newState = strippedPostBlock.get().defaultBlockState().setValue(WATERLOGGED, state.getValue(WATERLOGGED)).setValue(AXIS, state.getValue(AXIS));
            for (BooleanProperty property : CHAINED) {
                newState = newState.setValue(property, state.getValue(property));
            }
            return newState;
        }
        return super.getToolModifiedState(state, level, pos, player, stack, toolAction);
    }
}
