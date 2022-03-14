package samebutdifferent.ecologics.block;

import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PotBlock extends BlockWithEntity {
    protected static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(3, 13, 3, 13, 15, 13), Block.createCuboidShape(2, 0, 2, 14, 9, 14), Block.createCuboidShape(4, 9, 4, 12, 14, 12));
    public static final IntProperty CHISEL = IntProperty.of("chisel", 0, 5);
    public static final BooleanProperty POWERED = Properties.POWERED;

    public PotBlock(Settings properties) {
        super(properties);
        this.stateManager.getDefaultState().with(CHISEL, 0).with(POWERED, false);
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.down()).getMaterial().isSolid();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !pState.canPlaceAt(pLevel, pCurrentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState pState) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PotBlockEntity potBlockEntity) {
            ItemStack itemstack = pPlayer.getStackInHand(pHand);
            if (!itemstack.isEmpty()) {
                if (!pLevel.isClient && potBlockEntity.addItem(pPlayer.getAbilities().creativeMode ? itemstack.copy() : itemstack)) {
                    pLevel.playSound(null, pPos, SoundEvents.ENTITY_ITEM_FRAME_PLACE, SoundCategory.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.4F);
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS;
    }

    public static void signalItemAdded(World pLevel, BlockPos pPos, BlockState pState) {
        changePowered(pLevel, pPos, pState, true);
        pLevel.createAndScheduleBlockTick(pPos, pState.getBlock(), 2);
    }

    private static void changePowered(World pLevel, BlockPos pPos, BlockState pState, boolean pPowered) {
        pLevel.setBlockState(pPos, pState.with(POWERED, pPowered), 3);
        updateBelow(pLevel, pPos, pState);
    }

    private static void updateBelow(World pLevel, BlockPos pPos, BlockState pState) {
        pLevel.updateNeighborsAlways(pPos.down(), pState.getBlock());
    }

    @Override
    public void scheduledTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        changePowered(pLevel, pPos, pState, false);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        return super.getPlacementState(pContext).with(POWERED, false);
    }

    public void onStateReplaced(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.isOf(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PotBlockEntity potBlockEntity) {
                for(ItemStack stack : potBlockEntity.getItems()) {
                    ItemScatterer.spawn(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), stack);
                }
            }

            if (pState.get(POWERED)) {
                pLevel.updateNeighborsAlways(pPos.down(), this);
            }

            super.onStateReplaced(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState pState) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState pBlockState, BlockView pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.get(POWERED) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState pBlockState, BlockView pBlockAccess, BlockPos pPos, Direction pSide) {
        return pSide == Direction.UP && pBlockState.get(POWERED) ? 15 : 0;
    }

    @Override
    public boolean hasComparatorOutput(BlockState pState) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState pBlockState, World pLevel, BlockPos pPos) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PotBlockEntity pot) {
            return pot.getRedstoneSignal();
        }

        return 0;
    }

    @Override
    public boolean canPathfindThrough(BlockState pState, BlockView pLevel, BlockPos pPos, NavigationType pType) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new PotBlockEntity(pPos, pState);
    }

    @Override
    public BlockRenderType getRenderType(BlockState pState) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CHISEL, POWERED);
    }
}
