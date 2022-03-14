package samebutdifferent.ecologics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class SeashellBlock extends HorizontalFacingBlock implements Waterloggable {
    protected static final VoxelShape SHAPE_NORTH = VoxelShapes.union(Block.createCuboidShape(3, 0, 4, 13, 3, 14), Block.createCuboidShape(5, 0, 2, 11, 3, 4));
    protected static final VoxelShape SHAPE_EAST = VoxelShapes.union(Block.createCuboidShape(2, 0, 3, 12, 3, 13), Block.createCuboidShape(12, 0, 5, 14, 3, 11));
    protected static final VoxelShape SHAPE_SOUTH = VoxelShapes.union(Block.createCuboidShape(3, 0, 2, 13, 3, 12), Block.createCuboidShape(5, 0, 12, 11, 3, 14));
    protected static final VoxelShape SHAPE_WEST = VoxelShapes.union(Block.createCuboidShape(4, 0, 3, 14, 3, 13), Block.createCuboidShape(2, 0, 5, 4, 3, 11));
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public SeashellBlock() {
        super(Settings.of(Material.DECORATION, MapColor.BROWN).nonOpaque().strength(0.5F));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        switch(pState.get(FACING)) {
            case SOUTH:
                return SHAPE_SOUTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_NORTH;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        FluidState fluidstate = pContext.getWorld().getFluidState(pContext.getBlockPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return this.getDefaultState().with(FACING, pContext.getPlayerFacing().getOpposite()).with(WATERLOGGED, flag);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.get(WATERLOGGED)) {
            pLevel.createAndScheduleFluidTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickRate(pLevel));
        }
        return pFacing == Direction.DOWN && !pState.canPlaceAt(pLevel, pCurrentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(pState);
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.down()).getMaterial().isSolid();
    }

    @Override
    public boolean canPathfindThrough(BlockState pState, BlockView pLevel, BlockPos pPos, NavigationType pType) {
        return false;
    }
}
