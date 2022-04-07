package samebutdifferent.ecologics.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(CarpetBlock.class)
public abstract class MixinCarpetBlock extends Block {

    public MixinCarpetBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (item.sameItem(Blocks.MOSS_CARPET.asItem().getDefaultInstance()) && (pPlayer.getFeetBlockState() != pState)) {
            if (pState.is(Blocks.MOSS_CARPET.defaultBlockState().getBlock())) {
                if (!pLevel.isClientSide()) pLevel.setBlockAndUpdate(pPos, ModBlocks.MOSS_LAYER.get().defaultBlockState());
                if (!pPlayer.isCreative()) item.shrink(1);
            }
            pLevel.playSound(pPlayer, pPos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(pLevel.isClientSide());
        }
        return InteractionResult.PASS;
    }
}