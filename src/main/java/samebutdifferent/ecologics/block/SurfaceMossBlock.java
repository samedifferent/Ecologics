package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SurfaceMossBlock extends Block {
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 3);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 0.1D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(15.9D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 0.1D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 15.9D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 15.9D, 0.0D, 16.0D, 16.0D, 16.0D);
    
    public SurfaceMossBlock() {
        super(Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().strength(0.2F).sound(SoundType.MOSS_CARPET).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            case UP -> UP_AABB;
            case DOWN -> DOWN_AABB;
        };
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        return world.getBlockState(blockpos).isFaceSturdy(world, blockpos, direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(LAYERS) < 3 || super.canBeReplaced(state, context);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(FACING, context.getClickedFace()).setValue(LAYERS, Math.min(4, blockstate.getValue(LAYERS) + 1));
        } else {
            return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace());
        }
    }

    @Override
    public BlockState rotate(BlockState p_152033_, Rotation p_152034_) {
        return p_152033_.setValue(FACING, p_152034_.rotate(p_152033_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_152030_, Mirror p_152031_) {
        return p_152030_.rotate(p_152031_.getRotation(p_152030_.getValue(FACING)));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, FACING);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_152047_) {
        return PushReaction.DESTROY;
    }
}
