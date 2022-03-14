package samebutdifferent.ecologics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SandcastleBlock extends HorizontalFacingBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntProperty HATCH = Properties.HATCH;
    public static final IntProperty EGGS_INSIDE = IntProperty.of("eggs_inside", 0, 4);

    public SandcastleBlock() {
        super(Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.7F).sounds(BlockSoundGroup.SAND).nonOpaque().ticksRandomly());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(EGGS_INSIDE, 0).with(HATCH, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.down()).isOf(Blocks.SAND);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !pState.canPlaceAt(pLevel, pCurrentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void onSteppedOn(World pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        this.destroySandcastle(pLevel, pState, pPos, pEntity, 50);
    }

    @Override
    public void onLandedUpon(World pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        this.destroySandcastle(pLevel, pState, pPos, pEntity, 3);
    }

    private void destroySandcastle(World pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int chance) {
        if (this.canBreakSandcastle(pEntity)) {
            if (!pLevel.isClient && pLevel.random.nextInt(chance) == 0) {
                pLevel.playSound(null, pPos, SoundEvents.BLOCK_SAND_FALL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (pState.get(EGGS_INSIDE) > 0) {
                    pLevel.setBlockState(pPos, Blocks.TURTLE_EGG.getDefaultState().with(TurtleEggBlock.EGGS, pState.get(EGGS_INSIDE)).with(TurtleEggBlock.HATCH, pState.get(HATCH)));
                } else {
                    pLevel.breakBlock(pPos, false);
                }
            }

        }
    }

    private boolean canBreakSandcastle(Entity pEntity) {
        if (!(pEntity instanceof TurtleEntity)) {
            if (!(pEntity instanceof LivingEntity)) {
                return false;
            } else {
                return pEntity instanceof PlayerEntity;
            }
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        return this.getDefaultState().with(FACING, pContext.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, EGGS_INSIDE, HATCH);
    }

    @Override
    public boolean hasRandomTicks(BlockState pState) {
        return pState.get(EGGS_INSIDE) > 0;
    }

    public void randomTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        if (this.shouldUpdateHatchLevel(pLevel)) {
            int hatch = pState.get(HATCH);
            if (hatch < 2) {
                pLevel.playSound(null, pPos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlockState(pPos, pState.with(HATCH, hatch + 1), 2);
            } else {
                pLevel.playSound(null, pPos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.playSound(null, pPos, SoundEvents.BLOCK_SAND_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);

                for(int i = 0; i < pState.get(EGGS_INSIDE); ++i) {
                    pLevel.syncWorldEvent(2001, pPos, Block.getRawIdFromState(pState));
                    TurtleEntity turtle = EntityType.TURTLE.create(pLevel);
                    turtle.setBreedingAge(-24000);
                    turtle.setHomePos(pPos);
                    turtle.refreshPositionAndAngles((double)pPos.getX() + 0.3D + (double)i * 0.2D, pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    pLevel.spawnEntity(turtle);
                }
            }
        }

    }

    private boolean shouldUpdateHatchLevel(World pLevel) {
        float time = pLevel.getSkyAngle(1.0F);
        if ((double)time < 0.69D && (double)time > 0.65D) {
            return true;
        } else {
            return pLevel.random.nextInt(2) == 0;
        }
    }

    @Override
    public void afterBreak(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.afterBreak(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
        if (pState.get(EGGS_INSIDE) > 0) {
            pLevel.setBlockState(pPos, Blocks.TURTLE_EGG.getDefaultState().with(TurtleEggBlock.EGGS, pState.get(EGGS_INSIDE)).with(TurtleEggBlock.HATCH, pState.get(HATCH)));
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState pState) {
        return PistonBehavior.DESTROY;
    }
}
