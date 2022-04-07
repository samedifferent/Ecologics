package samebutdifferent.ecologics.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MossLayerBlock extends SnowBlock {
    public MossLayerBlock() {
        super(AbstractBlock.Settings.of(Material.PLANT, MapColor.GREEN).strength(0.1f).sounds(BlockSoundGroup.MOSS_CARPET));
        this.setDefaultState(this.stateManager.getDefaultState().with(LAYERS, 2));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
        ItemStack item = player.getStackInHand(hand);
        if ((state.get(LAYERS) < 8) && item.isItemEqual(Blocks.MOSS_CARPET.asItem().getDefaultStack()) && (player.getBlockStateAtPos() != state)) {
            if (state.isOf(this) && !world.isClient ) {
                if (state.get(LAYERS) < 7) {
                    world.setBlockState(pos, this.getDefaultState().with(LAYERS, state.get(LAYERS) + 1));
                } else {
                    world.setBlockState(pos, Blocks.MOSS_BLOCK.getDefaultState());
                }
                if (!player.isCreative()) {
                    item.decrement(1);
                }
            }
            world.playSound(player, pos, SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
