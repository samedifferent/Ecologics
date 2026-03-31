package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MossLayerBlock extends SnowLayerBlock 
{
    public MossLayerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, 2));
    }

    @Override
    public InteractionResult useItemOn(ItemStack item, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if ((pState.getValue(LAYERS) < 8) && isMatchingItem(item, pState) && (pPlayer.getInBlockState() != pState)) {
            if (pState.is(this) && !pLevel.isClientSide()) {
                if (pState.getValue(LAYERS) < 7) {
                    pLevel.setBlockAndUpdate(pPos, this.defaultBlockState().setValue(LAYERS, pState.getValue(LAYERS) + 1));
                } else {
                    pLevel.setBlockAndUpdate(pPos, pState.getBlock() == ModBlocks.PALE_MOSS_LAYER ? Blocks.PALE_MOSS_BLOCK.defaultBlockState() : Blocks.MOSS_BLOCK.defaultBlockState());
                }
                if (!pPlayer.isCreative()) {
                    item.shrink(1);
                }
            }
            pLevel.playSound(pPlayer, pPos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
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
        int layers = state.getValue(LAYERS);
        if (useContext.getItemInHand().is(this.asItem()) && layers < 8) {
            return useContext.getClickedFace() == Direction.UP;
        } else {
            return layers == 1;
        }
    }
    
   private static boolean isMatchingItem(ItemStack item, BlockState blockState) {
       if (item.getItem() == Items.MOSS_CARPET && blockState.getBlock() == ModBlocks.MOSS_LAYER) {
    	   return true;
       }
       if (item.getItem() == Items.PALE_MOSS_CARPET && blockState.getBlock() == ModBlocks.PALE_MOSS_LAYER) {
    	   return true;
       }
       return false;
   }
}
