package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(CarpetBlock.class)
public abstract class CarpetBlockMixin extends Block {

    public CarpetBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack item, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (ItemStack.isSameItem(Blocks.MOSS_CARPET.asItem().getDefaultInstance(), item) && (pPlayer.getInBlockState() != pState)) {
            if (pState.is(Blocks.MOSS_CARPET.defaultBlockState().getBlock())) {
                if (!pLevel.isClientSide()) pLevel.setBlockAndUpdate(pPos, ModBlocks.MOSS_LAYER.defaultBlockState());
                if (!pPlayer.isCreative()) item.shrink(1);
            }
            pLevel.playSound(pPlayer, pPos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
        }
        return ItemInteractionResult.CONSUME;
    }
}