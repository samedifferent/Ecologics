package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SandcastleBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS_INSIDE = IntegerProperty.create("eggs_inside", 0, 4);

    public SandcastleBlock() {
        super(Properties.of(Material.SAND, MaterialColor.SAND).strength(0.7F).sound(SoundType.SAND).noOcclusion().randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(EGGS_INSIDE, 0).setValue(HATCH, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.SAND);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        this.destroySandcastle(pLevel, pState, pPos, pEntity, 50);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        this.destroySandcastle(pLevel, pState, pPos, pEntity, 3);
    }

    private void destroySandcastle(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int chance) {
        if (this.canBreakSandcastle(pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(chance) == 0) {
                pLevel.playSound(null, pPos, SoundEvents.SAND_FALL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (pState.getValue(EGGS_INSIDE) > 0) {
                    pLevel.setBlockAndUpdate(pPos, Blocks.TURTLE_EGG.defaultBlockState().setValue(TurtleEggBlock.EGGS, pState.getValue(EGGS_INSIDE)).setValue(TurtleEggBlock.HATCH, pState.getValue(HATCH)));
                } else {
                    pLevel.destroyBlock(pPos, false);
                }
            }

        }
    }

    private boolean canBreakSandcastle(Entity pEntity) {
        if (!(pEntity instanceof Turtle)) {
            if (!(pEntity instanceof LivingEntity)) {
                return false;
            } else {
                return pEntity instanceof Player;
            }
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, EGGS_INSIDE, HATCH);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(EGGS_INSIDE) > 0;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (this.shouldUpdateHatchLevel(pLevel)) {
            int hatch = pState.getValue(HATCH);
            if (hatch < 2) {
                pLevel.playSound(null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, hatch + 1), 2);
            } else {
                pLevel.playSound(null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.playSound(null, pPos, SoundEvents.SAND_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);

                for(int i = 0; i < pState.getValue(EGGS_INSIDE); ++i) {
                    pLevel.levelEvent(2001, pPos, Block.getId(pState));
                    Turtle turtle = EntityType.TURTLE.create(pLevel);
                    turtle.setAge(-24000);
                    turtle.setHomePos(pPos);
                    turtle.moveTo((double)pPos.getX() + 0.3D + (double)i * 0.2D, pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    pLevel.addFreshEntity(turtle);
                }
            }
        }

    }

    private boolean shouldUpdateHatchLevel(Level pLevel) {
        float time = pLevel.getTimeOfDay(1.0F);
        if ((double)time < 0.69D && (double)time > 0.65D) {
            return true;
        } else {
            return pLevel.random.nextInt(2) == 0;
        }
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
        if (pState.getValue(EGGS_INSIDE) > 0) {
            pLevel.setBlockAndUpdate(pPos, Blocks.TURTLE_EGG.defaultBlockState().setValue(TurtleEggBlock.EGGS, pState.getValue(EGGS_INSIDE)).setValue(TurtleEggBlock.HATCH, pState.getValue(HATCH)));
        }
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }
}
