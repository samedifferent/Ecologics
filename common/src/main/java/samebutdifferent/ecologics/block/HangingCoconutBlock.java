package samebutdifferent.ecologics.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.config.ConfigCommon;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class HangingCoconutBlock extends FallingBlock implements BonemealableBlock 
{
	public static final MapCodec<HangingCoconutBlock> CODEC = HangingCoconutBlock.simpleCodec(HangingCoconutBlock::new);
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D), Block.box(3.0D, 4.0D, 3.0D, 13.0D, 14.0D, 13.0D), Block.box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D)};

    public HangingCoconutBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0).setValue(PERSISTENT, false));
    }
    
	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return CODEC;
	}

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        return aboveState.is(ModBlocks.COCONUT_LEAVES);
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (direction == Direction.UP && !this.canSurvive(state, level, pos) && state.getValue(AGE) < 2) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (random.nextInt(6) == 0) {
            if (age < 2) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);
            } else if (pos.getY() >= level.getMinY() && isFree(level.getBlockState(pos.below())) && !state.getValue(PERSISTENT)) {
                FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, pos, level.getBlockState(pos));
                this.falling(fallingblockentity);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext pContext) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, PERSISTENT);
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType pathtype) {
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean bool) {
    	return ModBlocks.COCONUT.asItem().getDefaultInstance();
    }
    
    @Override
    protected void falling(FallingBlockEntity pEntity) {
        pEntity.setHurtsEntities(1.0F, 20);
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingBlock(entity); // entity.damageSources().fallingBlock("coconut", entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(16) == 0) {
            if (state.getValue(AGE) == 2) {
                double x = (double)pos.getX() + rand.nextDouble();
                double y = (double)pos.getY() - 0.05D;
                double z = (double)pos.getZ() + rand.nextDouble();
                level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter level, BlockPos pos) {
        return 3873032;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if ((level.isEmptyBlock(pos.above()) && pos.getY() >= level.getMinY() && isFree(level.getBlockState(pos.below())))) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, pos, level.getBlockState(pos));
            this.falling(fallingblockentity);
            level.removeBlock(pos, false);
        }
    }
    
    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
    	if ((level.getFluidState(pos).getType() == Fluids.FLOWING_WATER || level.getFluidState(pos).getType() == Fluids.WATER) && FallingBlock.isFree(level.getBlockState(pos))) {
    		boolean waterfilled = level.getBlockState(pos).getBlock() == Blocks.WATER;
    		level.setBlock(pos, ModBlocks.COCONUT.defaultBlockState().setValue(CoconutBlock.WATERLOGGED, waterfilled), UPDATE_ALL);
    		return;
    	}
        level.playSound(null, pos, ModSoundEvents.COCONUT_SMASH, SoundSource.BLOCKS, 0.7f, 0.9f + level.getRandom().nextFloat() * 0.2f);
        if (level.getRandom().nextFloat() <= ConfigCommon.getCoconutCrabSpawnChance()) {
            CoconutCrab coconutCrab = ModEntityTypes.COCONUT_CRAB.create(level, EntitySpawnReason.NATURAL);
            coconutCrab.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(coconutCrab);
        } else {
            Block.dropResources(fallingBlock.getBlockState(), level, pos);
        }
    }
}
