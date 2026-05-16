package samebutdifferent.ecologics.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MossLayerBlock extends SnowLayerBlock {
    public MossLayerBlock() {
        super(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS_CARPET));
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, 2));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack item = player.getItemInHand(hand);
        if ((state.getValue(LAYERS) < 8) && (ItemStack.isSameItem(Blocks.MOSS_CARPET.asItem().getDefaultInstance(), item) || ItemStack.isSameItem(ModBlocks.MOSS_LAYER.get().asItem().getDefaultInstance(), item)) && (player.getFeetBlockState() != state)) {
            if (state.is(this) && !level.isClientSide()) {
                if (state.getValue(LAYERS) < 7) {
                    level.setBlockAndUpdate(pos, this.defaultBlockState().setValue(LAYERS, state.getValue(LAYERS) + 1));
                } else {
                    level.setBlockAndUpdate(pos, Blocks.MOSS_BLOCK.defaultBlockState());
                }
                if (!player.isCreative()) {
                    item.shrink(1);
                }
            }
            level.playSound(player, pos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        else if ((state.getValue(LAYERS) > 1) && item.is(ItemTags.HOES) && (player.getFeetBlockState() != state)) {
            if (state.is(this) && !level.isClientSide()) {
                level.setBlockAndUpdate(pos, this.defaultBlockState().setValue(LAYERS, state.getValue(LAYERS) - 1));
                if (level.getGameRules().getRule(GameRules.RULE_DOBLOCKDROPS).get()) {
                	Direction dir = hit.getDirection();
	                ItemEntity itementity = new ItemEntity(level, (double) pos.getX() + 0.5D + (double) dir.getStepX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) dir.getStepZ() * 0.65D, new ItemStack(Blocks.MOSS_CARPET.asItem()));
	                itementity.setDeltaMovement(0.05D * (double) dir.getStepX() + level.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) dir.getStepZ() + level.random.nextDouble() * 0.02D);
	                level.addFreshEntity(itementity);
                }
                if (!player.isCreative()) {
                    item.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
                }
            }
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(LAYERS, 1);
    }
    
    @Override
    public boolean useShapeForLightOcclusion(BlockState p_56630_) {
        return false;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return !reader.isEmptyBlock(pos.below());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, facingPos);
    }

    @Override
    public void randomTick(BlockState p_56615_, ServerLevel p_56616_, BlockPos p_56617_, RandomSource p_56618_) {

    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        int layers = state.getValue(LAYERS);
        if (useContext.getItemInHand().is(this.asItem()) && layers < 8) {
            return useContext.getClickedFace() == Direction.UP;
        } else {
            return layers == 1;
        }
    }
}
