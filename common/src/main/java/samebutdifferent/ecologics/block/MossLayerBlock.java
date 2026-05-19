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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MossLayerBlock extends SnowLayerBlock 
{
    public MossLayerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, 2));
    }

    @Override
    public InteractionResult useItemOn(ItemStack item, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if ((state.getValue(LAYERS) < 8) && isMatchingItem(item, state) && (player.getInBlockState() != state)) {
            if (state.is(this) && !level.isClientSide()) {
                if (state.getValue(LAYERS) < 7) {
                    level.setBlockAndUpdate(pos, this.defaultBlockState().setValue(LAYERS, state.getValue(LAYERS) + 1));
                } else {
                    level.setBlockAndUpdate(pos, state.getBlock() == ModBlocks.PALE_MOSS_LAYER ? Blocks.PALE_MOSS_BLOCK.defaultBlockState() : Blocks.MOSS_BLOCK.defaultBlockState());
                }
                if (!player.isCreative()) {
                    item.shrink(1);
                }
            }
            level.playSound(player, pos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        if ((state.getValue(LAYERS) > 1) && item.is(ItemTags.HOES) && (player.getInBlockState() != state)) {
            if (state.is(this) && !level.isClientSide()) {
                level.setBlockAndUpdate(pos, this.defaultBlockState().setValue(LAYERS, state.getValue(LAYERS) - 1));
                if (((ServerLevel)level).getGameRules().get(GameRules.BLOCK_DROPS).booleanValue()) {
                	Direction dir = hit.getDirection();
	                ItemEntity itementity = new ItemEntity(level, (double) pos.getX() + 0.5D + (double) dir.getStepX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) dir.getStepZ() * 0.65D, new ItemStack(state.getBlock() == ModBlocks.PALE_MOSS_LAYER ? Blocks.PALE_MOSS_CARPET.asItem() : Blocks.MOSS_CARPET.asItem()));
	                itementity.setDeltaMovement(0.05D * (double) dir.getStepX() + level.getRandom().nextDouble() * 0.02D, 0.05D, 0.05D * (double) dir.getStepZ() + level.getRandom().nextDouble() * 0.02D);
	                level.addFreshEntity(itementity);
                }
                if (!player.isCreative()) {
                    item.hurtAndBreak(1, player, hand);
                }
            }
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
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
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public void randomTick(BlockState p_56615_, ServerLevel p_56616_, BlockPos p_56617_, RandomSource p_56618_) {

    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }
    
   private static boolean isMatchingItem(ItemStack item, BlockState blockState) {
       if ((item.getItem() == Items.MOSS_CARPET || item.getItem() == ModBlocks.MOSS_LAYER.asItem()) && blockState.getBlock() == ModBlocks.MOSS_LAYER) {
    	   return true;
       }
       if ((item.getItem() == Items.PALE_MOSS_CARPET || item.getItem() == ModBlocks.PALE_MOSS_LAYER.asItem()) && blockState.getBlock() == ModBlocks.PALE_MOSS_LAYER) {
    	   return true;
       }
       return false;
   }
}
