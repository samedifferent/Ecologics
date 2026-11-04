package samebutdifferent.ecologics.neoforge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.content.fluids.pipes.VanillaFluidTargets;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;
import samebutdifferent.ecologics.neoforge.registry.ModFluidTypes;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(VanillaFluidTargets.class)
public class CreateFluidTargetsMixin 
{
	@Inject(method = "canProvideFluidWithoutCapability(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At(value = "HEAD"), cancellable = true)
	private static void injectCanProvideFluid(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (state.is(ModBlocks.MAPLE_SAP_CAULDRON)) {
			cir.setReturnValue(true);
		}
	}
	
	@Inject(method = "drainBlock(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Lnet/neoforged/neoforge/fluids/FluidStack;", at = @At(value = "HEAD"), cancellable = true)
	private static void injectDrainBlock(Level level, BlockPos pos, BlockState state, boolean simulate, CallbackInfoReturnable<FluidStack> cir) {
		if (state.is(ModBlocks.MAPLE_SAP_CAULDRON) && state.getBlock() instanceof MapleSapCauldronBlock lcb) {
			if (!lcb.isFull(state)) {
				cir.setReturnValue(FluidStack.EMPTY);
			}
			if (!simulate) {
				level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), Block.UPDATE_ALL);
			}
			if (state.getValue(MapleSapCauldronBlock.LEVEL) == 1) {
				cir.setReturnValue(new FluidStack(ModFluidTypes.MAPLE_SYRUP, 250));
			}
			else {
				cir.setReturnValue(new FluidStack(ModFluidTypes.MAPLE_SAP, 1000));
			}
		}
	}
}
