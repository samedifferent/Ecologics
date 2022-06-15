package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.mixin.DamageSourceAccessor;
import samebutdifferent.ecologics.mixin.FallingBlockEntityAccessor;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class HangingCoconutBlock extends FallingBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D), Block.box(3.0D, 4.0D, 3.0D, 13.0D, 14.0D, 13.0D), Block.box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D)};

    public HangingCoconutBlock() {
        super(Properties.of(Material.PLANT).randomTicks().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
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
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        int age = blockState.getValue(AGE);
        if (randomSource.nextInt(3) == 0) {
            if (age < 2) {
                serverLevel.setBlock(blockPos, blockState.setValue(AGE, age + 1), 2);
            } else if (blockPos.getY() >= serverLevel.getMinBuildHeight() && isFree(serverLevel.getBlockState(blockPos.below()))){
                FallingBlockEntity fallingblockentity = FallingBlockEntityAccessor.invokeConstructor(serverLevel, blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, serverLevel.getBlockState(blockPos));
                this.falling(fallingblockentity);
                serverLevel.addFreshEntity(fallingblockentity);
                serverLevel.removeBlock(blockPos, false);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(AGE)];
    }

    @Override
    public PushReaction getPistonPushReaction(@NotNull BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull BlockGetter pLevel, @NotNull BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource randomSource, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, @NotNull RandomSource randomSource, @NotNull BlockPos blockPos, BlockState blockState) {
        serverLevel.setBlock(blockPos, blockState.setValue(AGE, blockState.getValue(AGE) + 1), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    protected void falling(FallingBlockEntity pEntity) {
        pEntity.setHurtsEntities(2.0F, 40);
    }

    @Override
    public DamageSource getFallDamageSource() {
        return DamageSourceAccessor.invokeConstructor("coconut");
    }

    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            if (blockState.getValue(AGE) == 2) {
                double x = blockPos.getX() + randomSource.nextDouble();
                double y = blockPos.getY() - 0.05D;
                double z = blockPos.getZ() + randomSource.nextDouble();
                level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState), x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getDustColor(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 3873032;
    }

    @Override
    public void tick(@NotNull BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, @NotNull RandomSource randomSource) {
        if ((serverLevel.isEmptyBlock(blockPos.above()) && blockPos.getY() >= serverLevel.getMinBuildHeight() && isFree(serverLevel.getBlockState(blockPos.below())))) {
            FallingBlockEntity fallingblockentity = FallingBlockEntityAccessor.invokeConstructor(serverLevel, blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, serverLevel.getBlockState(blockPos));
            this.falling(fallingblockentity);
            serverLevel.addFreshEntity(fallingblockentity);
            serverLevel.removeBlock(blockPos, false);
        }
    }

    @Override
    public void onBrokenAfterFall(Level pLevel, BlockPos pPos, FallingBlockEntity pFallingBlock) {
        pLevel.playSound(null, pPos, ModSoundEvents.COCONUT_SMASH.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
        if (pLevel.random.nextFloat() <= Ecologics.CONFIG.COCONUT_CRAB_SPAWN_CHANCE) {
            CoconutCrab coconutCrab = ModEntityTypes.COCONUT_CRAB.get().create(pLevel);
            if (coconutCrab != null) {
                coconutCrab.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
                pLevel.addFreshEntity(coconutCrab);
            }
        } else {
            Block.dropResources(pFallingBlock.getBlockState(), pLevel, pPos);
        }
    }
}
