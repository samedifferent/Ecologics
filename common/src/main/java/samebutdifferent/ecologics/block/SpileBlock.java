package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Prediction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModParticleTypes;
import samebutdifferent.ecologics.registry.ModTags;

public class SpileBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
{
	public static final BooleanProperty HAS_BUCKET = BooleanProperty.create("has_bucket");
	public static final IntegerProperty FULLNESS = IntegerProperty.create("fullness", 0, 3);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
    // VoxelShapes mapped by facing direction: 3px wide (6.5 to 9.5), 3px high (6 to 9), 6px long extending from the log surface
    private static final VoxelShape SHAPE_NORTH_SPILE = Block.box(6.5D, 11.0D, 11.0D, 9.5D, 14.0D, 16.0D);
    private static final VoxelShape SHAPE_SOUTH_SPILE = Block.box(6.5D, 11.0D, 0.0D, 9.5D, 14.0D, 5.0D);
    private static final VoxelShape SHAPE_WEST_SPILE = Block.box(11.0D, 11.0D, 6.5D, 16.0D, 14.0D, 9.5D);
    private static final VoxelShape SHAPE_EAST_SPILE = Block.box(0.0D, 11.0D, 6.5D, 5.0D, 14.0D, 9.5D);

    // Create bucket shapes. The shape would be hollow to allow for realism.
    private static final VoxelShape SHAPE_NORTH_BUCKET = Shapes.or(Block.box(4.0D, 4.0D, 8.0D, 5.0D, 12.0D, 16.0D), Block.box(11.0D, 4.0D, 8.0D, 12.0D, 12.0D, 16.0D), Block.box(4.0D, 4.0D, 8.0D, 12.0D, 12.0D, 9.0D), Block.box(4.0D, 4.0D, 15.0D, 12.0D, 12.0D, 16.0D), Block.box(4.0D, 4.0D, 8.0D, 12.0D, 5.0D, 16.0D));
    private static final VoxelShape SHAPE_SOUTH_BUCKET = Shapes.or(Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 1.0D), Block.box(4.0D, 4.0D, 7.0D, 12.0D, 12.0D, 8.0D), Block.box(4.0D, 4.0D, 0.0D, 5.0D, 12.0D, 8.0D), Block.box(11.0D, 4.0D, 0.0D, 12.0D, 12.0D, 8.0D), Block.box(4.0D, 4.0D, 0.0D, 12.0D, 5.0D, 8.0D));
    private static final VoxelShape SHAPE_WEST_BUCKET = Shapes.or(Block.box(8.0D, 4.0D, 4.0D, 16.0D, 12.0D, 5.0D), Block.box(8.0D, 4.0D, 11.0D, 16.0D, 12.0D, 12.0D), Block.box(8.0D, 4.0D, 4.0D, 9.0D, 12.0D, 12.0D), Block.box(15.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), Block.box(8.0D, 4.0D, 4.0D, 16.0D, 5.0D, 12.0D));
    private static final VoxelShape SHAPE_EAST_BUCKET = Shapes.or(Block.box(0.0D, 4.0D, 4.0D, 1.0D, 12.0D, 12.0D), Block.box(7.0D, 4.0D, 4.0D, 8.0D, 12.0D, 12.0D), Block.box(0.0D, 4.0D, 4.0D, 8.0D, 12.0D, 5.0D), Block.box(0.0D, 4.0D, 11.0D, 8.0D, 12.0D, 12.0D), Block.box(0.0D, 4.0D, 4.0D, 8.0D, 5.0D, 12.0D));

    // Create combined shapes.
    public static final VoxelShape SHAPE_NORTH_COMBINED = Shapes.or(SHAPE_NORTH_SPILE, SHAPE_NORTH_BUCKET);
    public static final VoxelShape SHAPE_SOUTH_COMBINED = Shapes.or(SHAPE_SOUTH_SPILE, SHAPE_SOUTH_BUCKET);;
    public static final VoxelShape SHAPE_WEST_COMBINED = Shapes.or(SHAPE_WEST_SPILE, SHAPE_WEST_BUCKET);;
    public static final VoxelShape SHAPE_EAST_COMBINED = Shapes.or(SHAPE_EAST_SPILE, SHAPE_EAST_BUCKET);;
	
	public SpileBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HAS_BUCKET, false).setValue(FULLNESS, 0).setValue(WATERLOGGED, false));
	}
	
	@Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, HAS_BUCKET, FULLNESS, WATERLOGGED);
    }
	
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Boolean hasBucket = state.getValue(HAS_BUCKET);
        switch (state.getValue(FACING)) {
            case NORTH:
                return hasBucket ? SHAPE_NORTH_COMBINED : SHAPE_NORTH_SPILE;              
            case EAST: 
                return hasBucket ? SHAPE_EAST_COMBINED : SHAPE_EAST_SPILE;
            case WEST: 
                return hasBucket ? SHAPE_WEST_COMBINED : SHAPE_WEST_SPILE;
            case SOUTH:
            default:
                return hasBucket ? SHAPE_SOUTH_COMBINED : SHAPE_SOUTH_SPILE;
        }
    }
    
    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
    	if (stack.is(Items.BUCKET) && !state.getValue(HAS_BUCKET)) {
    		if (!player.isCreative()) {
    			stack.shrink(1);
    		}
    		level.setBlock(pos, state.setValue(HAS_BUCKET, true), 11);
    		return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    	}
    	return super.useItemOn(stack, state, level, pos, player, hand, result);
    }
    
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
    	if (state.getValue(HAS_BUCKET)) {
            ItemStack dropStack;
            if (state.getValue(FULLNESS) == 3) {
                dropStack = new ItemStack(ModItems.MAPLE_SAP_BUCKET);
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            } 
            else {
                dropStack = new ItemStack(Items.BUCKET);
                level.playSound(null, pos, SoundEvents.LANTERN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            // Safely give the item to the player or drop it if inventory is completely full
            if (!player.getInventory().add(dropStack)) {
                player.drop(dropStack, false, Prediction.PREDICTED);
            }
        	level.setBlock(pos, state.setValue(HAS_BUCKET, false).setValue(FULLNESS, 0), 11);
        	return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    	}
    	return super.useWithoutItem(state, level, pos, player, result);
    }
    
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction facing = state.getValue(FACING);
        BlockPos logPos = pos.relative(facing.getOpposite());
        BlockState logState = level.getBlockState(logPos);
        // Only process sap filling if the attached log is still a Sappy Maple Log
        if (logState.is(ModTags.BlockTags.SAPPY_MAPLE_LOGS) && state.getValue(HAS_BUCKET) && state.getValue(FULLNESS) < 3) {
            int currentFullness = state.getValue(FULLNESS);
            int nextFullness = random.nextFloat() < 0.4F ? currentFullness + 1 : currentFullness;
            level.setBlock(pos, state.setValue(FULLNESS, nextFullness), 3);
            // If the bucket just became completely full, roll a chance to deplete the log
            if (nextFullness == 3) {
                if (random.nextFloat() < 0.20F) {
                    this.depleteLogSap(level, logPos, logState);
                }
            }
        }
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(HAS_BUCKET) && random.nextFloat() < 0.2F) { // Control the drop frequency
            BlockPos logPos = pos.relative(state.getValue(FACING).getOpposite());
            if (!level.getBlockState(logPos).is(ModTags.BlockTags.SAPPY_MAPLE_LOGS)) {
                return; // If the maple log is exhausted, don't play animations.
            }
        	Direction direction = state.getValue(FACING);
            // Establish baseline coordinates centered exactly at the block's middle
            double spawnX = pos.getX() + 0.5;
            double spawnY = pos.getY() + 0.68; // Adjusted to match the underside floor of your spile model
            double spawnZ = pos.getZ() + 0.5;

            // Offset the particle toward the front edge tip based on the block's orientation
            switch (direction) {
                case NORTH -> spawnZ += 0.16F;
                case SOUTH -> spawnZ -= 0.16F;
                case WEST  -> spawnX += 0.16F;
                case EAST  -> spawnX -= 0.16F;
		        default -> spawnX += 0;
            }
            level.addParticle(ModParticleTypes.DRIPPING_MAPLE_SAP, spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0);
        }
    }
    
    // Log depletion mechanic
    private void depleteLogSap(ServerLevel level, BlockPos logPos, BlockState sappyLogState) {
        if (sappyLogState.hasProperty(BlockStateProperties.AXIS)) {
            Axis activeAxis = sappyLogState.getValue(BlockStateProperties.AXIS);
            level.setBlock(logPos, ModBlocks.MAPLE_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, activeAxis), 3);
        } else {
            // Fallback replacement if your logs don't use axis alignment properties
            level.setBlock(logPos, ModBlocks.MAPLE_LOG.defaultBlockState(), 3);
        }
    }
    
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos logPos = pos.relative(direction.getOpposite());
        return level.getBlockState(logPos).is(ModBlocks.SAPPY_MAPLE_LOG) || level.getBlockState(logPos).is(BlockTags.LOGS);
    }
	
    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
        	scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        // Spiles can only be attached horizontally to the sides of logs
        if (clickedFace.getAxis().isHorizontal()) {
            BlockPos targetLogPos = context.getClickedPos().relative(clickedFace.getOpposite());
            if (context.getLevel().getBlockState(targetLogPos).is(ModBlocks.SAPPY_MAPLE_LOG)) {
                FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
                return this.defaultBlockState().setValue(FACING, clickedFace).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            }
        }
        return null; // Prevents placement if not clicked on a horizontal side of a sappy log
    }
}
