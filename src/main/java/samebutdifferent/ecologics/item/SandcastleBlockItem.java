package samebutdifferent.ecologics.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.SandcastleBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

public class SandcastleBlockItem extends BlockItem {
    public SandcastleBlockItem() {
        super(ModBlocks.SANDCASTLE, new Settings().group(Ecologics.TAB).maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext pContext) {
        World level = pContext.getWorld();
        BlockPos pos = pContext.getBlockPos();
        BlockState state = level.getBlockState(pos);
        if (!state.isOf(Blocks.TURTLE_EGG)) {
            return super.useOnBlock(pContext);
        } else {
            level.playSound(pContext.getPlayer(), pos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            level.setBlockState(pos, ModBlocks.SANDCASTLE.getDefaultState().with(SandcastleBlock.FACING, pContext.getPlayerFacing().getOpposite()).with(SandcastleBlock.EGGS_INSIDE, state.get(TurtleEggBlock.EGGS)).with(SandcastleBlock.HATCH, state.get(TurtleEggBlock.HATCH)));
            pContext.getStack().decrement(1);
            if (pContext.getPlayer() instanceof ServerPlayerEntity player) {
                Criteria.PLACED_BLOCK.trigger(player, pos, pContext.getStack());
            }
            return ActionResult.success(level.isClient);
        }
    }
}
