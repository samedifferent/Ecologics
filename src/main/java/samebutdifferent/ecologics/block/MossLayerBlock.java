package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class MossLayerBlock extends SnowLayerBlock {
    public MossLayerBlock() {
        super(Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS_CARPET));
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, 2));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if ((pState.getValue(LAYERS) < 8) && item.sameItem(Blocks.MOSS_CARPET.asItem().getDefaultInstance()) && (pPlayer.getFeetBlockState() != pState)) {
            if (pState.is(this) && !pLevel.isClientSide()) {
                if (pState.getValue(LAYERS) < 7) {
                    pLevel.setBlockAndUpdate(pPos, this.defaultBlockState().setValue(LAYERS, pState.getValue(LAYERS) + 1));
                } else {
                    pLevel.setBlockAndUpdate(pPos, Blocks.MOSS_BLOCK.defaultBlockState());
                }
                if (!pPlayer.isCreative()) {
                    item.shrink(1);
                }
            }
            pLevel.playSound(pPlayer, pPos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(pLevel.isClientSide());
        }
        return InteractionResult.PASS;
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
    public void randomTick(BlockState p_56615_, ServerLevel p_56616_, BlockPos p_56617_, Random p_56618_) {

    }
}
