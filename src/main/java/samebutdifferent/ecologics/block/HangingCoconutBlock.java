package samebutdifferent.ecologics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModSoundEvents;

import java.util.Random;

public class HangingCoconutBlock extends FallingBlock implements Fertilizable {
    public static final IntProperty AGE = Properties.AGE_2;
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.createCuboidShape(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D), Block.createCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 14.0D, 13.0D), Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D)};

    public HangingCoconutBlock() {
        super(Settings.of(Material.PLANT).ticksRandomly().strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, 0));
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        BlockState aboveState = pLevel.getBlockState(pPos.up());
        return aboveState.isOf(ModBlocks.COCONUT_LEAVES);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == Direction.UP && !this.canPlaceAt(pState, pLevel, pCurrentPos) && pState.get(AGE) < 2) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        int age = pState.get(AGE);
        if (pRandom.nextInt(3) == 0) {
            if (age < 2) {
                pLevel.setBlockState(pPos, pState.with(AGE, age + 1), 2);
            } else if (pPos.getY() >= pLevel.getBottomY() && canFallThrough(pLevel.getBlockState(pPos.down()))){
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(pLevel, (double)pPos.getX() + 0.5D, pPos.getY(), (double)pPos.getZ() + 0.5D, pLevel.getBlockState(pPos));
                this.configureFallingBlockEntity(fallingblockentity);
                pLevel.spawnEntity(fallingblockentity);
                pLevel.removeBlock(pPos, false);
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        return SHAPE_BY_AGE[pState.get(AGE)];
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState pState) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public boolean isFertilizable(BlockView pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.get(AGE) < 2;
    }

    @Override
    public boolean canGrow(World pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void grow(ServerWorld pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlockState(pPos, pState.with(AGE, pState.get(AGE) + 1), 2);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public boolean canPathfindThrough(BlockState pState, BlockView pLevel, BlockPos pPos, NavigationType pType) {
        return false;
    }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity pEntity) {
        pEntity.setHurtEntities(2.0F, 40);
    }

    @Override
    public DamageSource getDamageSource() {
        return new DamageSource("coconut").setFallingBlock();
    }

    @Override
    public void randomDisplayTick(BlockState pState, World pLevel, BlockPos pPos, Random pRand) {
        if (pRand.nextInt(16) == 0) {
            if (pState.get(AGE) == 2) {
                double x = (double)pPos.getX() + pRand.nextDouble();
                double y = (double)pPos.getY() - 0.05D;
                double z = (double)pPos.getZ() + pRand.nextDouble();
                pLevel.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, pState), x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getColor(BlockState pState, BlockView pLevel, BlockPos pPos) {
        return 3873032;
    }

    @Override
    public void scheduledTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        if ((pLevel.isAir(pPos.up()) && pPos.getY() >= pLevel.getBottomY() && canFallThrough(pLevel.getBlockState(pPos.down())))) {
            FallingBlockEntity fallingblockentity = new FallingBlockEntity(pLevel, (double)pPos.getX() + 0.5D, pPos.getY(), (double)pPos.getZ() + 0.5D, pLevel.getBlockState(pPos));
            this.configureFallingBlockEntity(fallingblockentity);
            pLevel.spawnEntity(fallingblockentity);
            pLevel.removeBlock(pPos, false);
        }
    }

    @Override
    public void onDestroyedOnLanding(World pLevel, BlockPos pPos, FallingBlockEntity pFallingBlock) {
        pLevel.playSound(null, pPos, ModSoundEvents.COCONUT_SMASH, SoundCategory.BLOCKS, 0.2F, 1.0F);
        if (pLevel.random.nextInt(5) == 0) {
            CoconutCrab coconutCrab = ModEntityTypes.COCONUT_CRAB.create(pLevel);
            if (coconutCrab != null) {
                coconutCrab.setPosition(pPos.getX(), pPos.getY(), pPos.getZ());
                pLevel.spawnEntity(coconutCrab);
            }
        } else {
            Block.dropStacks(pFallingBlock.getBlockState(), pLevel, pPos);
        }
    }
}
