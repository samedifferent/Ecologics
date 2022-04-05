package samebutdifferent.ecologics.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mixin(CarpetBlock.class)
public abstract class MixinCarpetBlock extends Block {
    public MixinCarpetBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
        ItemStack item = player.getStackInHand(hand);
        if (item.isItemEqual(Blocks.MOSS_CARPET.asItem().getDefaultStack()) && (player.getBlockStateAtPos() != state)) {
            if (state.isOf(Blocks.MOSS_CARPET.getDefaultState().getBlock()) && !world.isClient ) {
                world.setBlockState(pos, ModBlocks.MOSS_LAYER.getDefaultState());
            }
            world.playSound(player, pos, SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}