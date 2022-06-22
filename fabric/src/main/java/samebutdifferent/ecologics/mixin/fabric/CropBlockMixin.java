package samebutdifferent.ecologics.mixin.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

import java.util.Random;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @Inject(at = @At("TAIL"), method = "performBonemeal")
    public void onGrow(ServerLevel world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.is(Blocks.CACTUS)) {
            if (world.getBlockState(pos.above()).is(Blocks.CACTUS) && world.getBlockState(pos.below()).is(Blocks.CACTUS)) {
                ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
                if (world.isEmptyBlock(pos.above(2)) && world.getRandom().nextFloat() <= config.desert.pricklyPearGrowthChance) {
                    world.setBlock(pos.above(2), ModBlocks.PRICKLY_PEAR.get().defaultBlockState(), 2);
                    world.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
}
