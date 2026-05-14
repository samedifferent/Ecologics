package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.platform.ConfigPlatformHelper;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class HangingCoconutBlock extends FallingBlock implements BonemealableBlock {
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D), Block.box(3.0D, 4.0D, 3.0D, 13.0D, 14.0D, 13.0D), Block.box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D)};

    public HangingCoconutBlock() {
        super(Properties.of().randomTicks().strength(2.0F, 3.0F).pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD).noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0).setValue(PERSISTENT, false));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState aboveState = pLevel.getBlockState(pPos.above());
        return aboveState.is(ModBlocks.COCONUT_LEAVES.get());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == Direction.UP && !this.canSurvive(pState, pLevel, pCurrentPos) && pState.getValue(AGE) < 2) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int age = pState.getValue(AGE);
        if (pRandom.nextInt(3) == 0) {
            if (age < 2) {
                pLevel.setBlock(pPos, pState.setValue(AGE, age + 1), 2);
            } else if (pPos.getY() >= pLevel.getMinBuildHeight() && isFree(pLevel.getBlockState(pPos.below())) && !pState.getValue(PERSISTENT)) {
                FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(pLevel, pPos, pLevel.getBlockState(pPos));
                this.falling(fallingblockentity);
                pLevel.removeBlock(pPos, false);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(AGE)];
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(AGE, pState.getValue(AGE) + 1), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, PERSISTENT);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRand) {
        if (pRand.nextInt(16) == 0) {
            if (pState.getValue(AGE) == 2) {
                double x = (double)pPos.getX() + pRand.nextDouble();
                double y = (double)pPos.getY() - 0.05D;
                double z = (double)pPos.getZ() + pRand.nextDouble();
                pLevel.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, pState), x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getDustColor(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 3873032;
    }
    
    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return ModItems.COCONUT.get().getDefaultInstance();
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRand) {
        if ((pLevel.isEmptyBlock(pPos.above()) && pPos.getY() >= pLevel.getMinBuildHeight() && isFree(pLevel.getBlockState(pPos.below())))) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(pLevel, pPos, pLevel.getBlockState(pPos));
            this.falling(fallingblockentity);
            pLevel.removeBlock(pPos, false);
        }
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
        if ((level.getRandom().nextFloat() < 0.25F || level.getFluidState(pos).getType() == Fluids.FLOWING_WATER || level.getFluidState(pos).getType() == Fluids.WATER) && FallingBlock.isFree(level.getBlockState(pos))) {
            boolean waterfilled = level.getBlockState(pos).getBlock() == Blocks.WATER;
            level.setBlock(pos, ModBlocks.COCONUT.get().defaultBlockState().setValue(CoconutBlock.WATERLOGGED, waterfilled), UPDATE_ALL);
            return;
        }
        level.playSound(null, pos, ModSoundEvents.COCONUT_SMASH.get(), SoundSource.BLOCKS, 0.7f, 0.9f + level.getRandom().nextFloat() * 0.2f);
        if (level.random.nextFloat() <= ConfigPlatformHelper.coconutCrabSpawnChance()) {
            CoconutCrab coconutCrab = ModEntityTypes.COCONUT_CRAB.get().create(level);
            coconutCrab.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(coconutCrab);
        } else {
            Block.dropResources(fallingBlock.getBlockState(), level, pos);
        }
    }
}
