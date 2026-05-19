package samebutdifferent.ecologics.block;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;

public class PotBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<PotBlock> CODEC = PotBlock.simpleCodec(PotBlock::new);
    protected static final VoxelShape SHAPE = Shapes.or(Block.box(3, 13, 3, 13, 15, 13), Block.box(2, 0, 2, 14, 9, 14), Block.box(4, 9, 4, 12, 14, 12));
    public static final IntegerProperty CHISEL = IntegerProperty.create("chisel", 0, 5);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PotBlock(Properties properties) {
        super(properties.pushReaction(PushReaction.DESTROY));
        this.stateDefinition.any().setValue(CHISEL, 0).setValue(POWERED, false).setValue(WATERLOGGED, false);
    }
    
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

    @SuppressWarnings("deprecation")
	@Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).isSolid();
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult useItemOn(ItemStack itemstack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PotBlockEntity potBlockEntity) {
            if (!itemstack.isEmpty()) {
                if (!pLevel.isClientSide() && potBlockEntity.addItem(pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack)) {
                    pLevel.playSound(null, pPos, SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.4F);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    public static void signalItemAdded(Level pLevel, BlockPos pPos, BlockState pState) {
        changePowered(pLevel, pPos, pState, true);
        pLevel.scheduleTick(pPos, pState.getBlock(), 2);
    }

    private static void changePowered(Level pLevel, BlockPos pPos, BlockState pState, boolean pPowered) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, pPowered), 3);
        updateBelow(pLevel, pPos, pState);
    }

    private static void updateBelow(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.updateNeighborsAt(pPos.below(), pState.getBlock());
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRand) {
        changePowered(pLevel, pPos, pState, false);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
    	FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return super.getStateForPlacement(context).setValue(POWERED, false).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PotBlockEntity potBlockEntity) {
            for(ItemStack stack : potBlockEntity.getItems()) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
        if (state.getValue(POWERED)) {
        	level.updateNeighborsAt(pos.below(), this);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    @Override
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pSide == Direction.UP && pBlockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos, Direction direction) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PotBlockEntity pot) {
            return pot.getRedstoneSignal();
        }

        return 0;
    }

    @Override
    public boolean isPathfindable(BlockState pState, PathComputationType pType) {
        return false;
    }
    
    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PotBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CHISEL, POWERED, WATERLOGGED);
    }
}
