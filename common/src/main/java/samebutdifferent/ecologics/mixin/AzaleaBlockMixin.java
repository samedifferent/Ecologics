package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.grower.ModTreeGrower;
import samebutdifferent.ecologics.config.ConfigCommon;

@Mixin(AzaleaBlock.class)
public class AzaleaBlockMixin {

	@Inject(at = @At(value = "HEAD"), method = "performBonemeal(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", cancellable = true)
    private void modifyPerformBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo callback) {
		if (ConfigCommon.replaceAzaleaTree()) {
	        ModTreeGrower.AZALEA.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
	        callback.cancel();
		}
    }
}
