package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MossyCarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.block.MossLayerBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModTags.ItemTags;

@Mixin(MossyCarpetBlock.class)
public class MossyCarpetBlockMixin extends Block {

	private static final BooleanProperty NO_GROWTH = BooleanProperty.create("no_growth");
	
    public MossyCarpetBlockMixin(Properties properties) {
        super(properties);
    }
    
    @Inject(method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V", at = @At("TAIL"))
    private void injectConstructor(BlockBehaviour.Properties properties, CallbackInfo callback) {
    	this.registerDefaultState(this.getStateDefinition().any().setValue(NO_GROWTH, Boolean.valueOf(false)));
    }
    
    @Inject(method = "createBlockStateDefinition(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V", at = @At("TAIL"))
    private void injectBlockStateProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo callback) {
    	builder.add(NO_GROWTH);
    }
    
    @Inject(method = "getUpdatedState(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Z)Lnet/minecraft/world/level/block/state/BlockState;", at = @At(value = "HEAD"), cancellable = true)
    private static void injectGetUpdatedState(BlockState state, BlockGetter getter, BlockPos pos, boolean $$3, CallbackInfoReturnable<BlockState> cir) {
    	if (state.hasProperty(NO_GROWTH) && state.getValue(NO_GROWTH)) {
    		cir.setReturnValue(state);
    	}
    }
    
    @Override
    public InteractionResult useItemOn(ItemStack item, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (ItemStack.isSameItem(Items.PALE_MOSS_CARPET.getDefaultInstance(), item)) {
            if (state.is(Blocks.PALE_MOSS_CARPET)) {
                if (!level.isClientSide()) level.setBlockAndUpdate(pos, ModBlocks.PALE_MOSS_LAYER.defaultBlockState().setValue(MossLayerBlock.LAYERS, 2));
                if (!player.isCreative()) item.shrink(1);
            }
            level.playSound(player, pos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        if (item.is(ItemTags.SHEARS) && (player.getInBlockState() != state)) {
            if (state.is(Blocks.PALE_MOSS_CARPET)) {
            	if (!hasSideGrowth(state)) {
            		return InteractionResult.CONSUME;
            	}
                if (!level.isClientSide()) {
                	if (state.getValue(MossyCarpetBlock.BASE)) {
                		level.setBlock(pos, Blocks.PALE_MOSS_CARPET.defaultBlockState().setValue(NO_GROWTH, true), 2);
                	}
                	else {
                		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                	}
                    if (!player.isCreative()) {
                    	item.hurtAndBreak(1, player, hand);
                    }
                }
                level.playSound(player, pos, SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(item, state, level, pos, player, hand, hit);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
    	BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
    	if (blockstate.is(ModBlocks.PALE_MOSS_LAYER)) {
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
    
    private boolean hasSideGrowth(BlockState state) {
    	if (!(state.hasProperty(MossyCarpetBlock.NORTH) && state.hasProperty(MossyCarpetBlock.SOUTH) && state.hasProperty(MossyCarpetBlock.WEST) && state.hasProperty(MossyCarpetBlock.EAST))) {
    		return false;
    	}
    	if (state.getValue(MossyCarpetBlock.NORTH) != WallSide.NONE) {
    		return true;
    	}
    	if (state.getValue(MossyCarpetBlock.SOUTH) != WallSide.NONE) {
    		return true;
    	}
    	if (state.getValue(MossyCarpetBlock.WEST) != WallSide.NONE) {
    		return true;
    	}
    	if (state.getValue(MossyCarpetBlock.EAST) != WallSide.NONE) {
    		return true;
    	}
    	return false;
    }
}
