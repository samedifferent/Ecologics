package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.block.MossLayerBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(CarpetBlock.class)
public abstract class CarpetBlockMixin extends Block {

    public CarpetBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useItemOn(ItemStack item, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (ItemStack.isSameItem(Items.MOSS_CARPET.getDefaultInstance(), item)) {
            if (state.is(Blocks.MOSS_CARPET.defaultBlockState().getBlock())) {
                if (!level.isClientSide()) {
                    level.setBlockAndUpdate(pos, ModBlocks.MOSS_LAYER.defaultBlockState().setValue(MossLayerBlock.LAYERS, 2));
                }
                if (!player.isCreative()) {
                    item.shrink(1);
                }
            }
            level.playSound(player, pos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
    	BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
    	if (blockstate.is(ModBlocks.MOSS_LAYER)) {
            int layers = blockstate.getValue(MossLayerBlock.LAYERS);
            if (layers + 1 < 8) {
            	return blockstate.setValue(MossLayerBlock.LAYERS, Integer.valueOf(Math.min(8, layers + 1)));
            }
            else {
            	return blockstate.getBlock() == ModBlocks.PALE_MOSS_LAYER ? Blocks.PALE_MOSS_BLOCK.defaultBlockState() : Blocks.MOSS_BLOCK.defaultBlockState();
            }
    	}
    	return super.getStateForPlacement(context);
    }
    
    /*@Inject(method = "getStateForPlacement(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"), cancellable = true)
    private void injectGetState(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
    	BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
    	if (blockstate.is(ModBlocks.MOSS_LAYER)) {
            int layers = blockstate.getValue(MossLayerBlock.LAYERS);
            cir.setReturnValue((blockstate).setValue(MossLayerBlock.LAYERS, Integer.valueOf(Math.min(8, layers + 1))));
    	}
    }*/
}