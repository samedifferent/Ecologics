package samebutdifferent.ecologics.mixin.fabric;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Inject(at = @At("TAIL"), method = "randomTick")
    public void onGrow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (state.getValue(CactusBlock.AGE) == 15) {
            if (state.is(Blocks.CACTUS)) {
                if (level.getBlockState(pos.above()).is(Blocks.CACTUS) && level.getBlockState(pos.below()).is(Blocks.CACTUS)) {
                    ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
                    if (level.isEmptyBlock(pos.above(2)) && level.getRandom().nextFloat() <= config.desert.pricklyPearGrowthChance) {
                        level.setBlock(pos.above(2), ModBlocks.PRICKLY_PEAR.defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}
