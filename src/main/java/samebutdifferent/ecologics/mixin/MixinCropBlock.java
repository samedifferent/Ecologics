package samebutdifferent.ecologics.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Random;

@Mixin(CropBlock.class)
public abstract class MixinCropBlock {
    @Inject(at = @At("TAIL"), method = "grow")
    public void onGrow(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.isOf(Blocks.CACTUS)) {
            if (world.getBlockState(pos.up()).isOf(Blocks.CACTUS) && world.getBlockState(pos.down()).isOf(Blocks.CACTUS)) {
                if (world.isAir(pos.up(2))) {
                    world.setBlockState(pos.up(2), ModBlocks.PRICKLY_PEAR.getDefaultState(), 2);
                    world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
}
