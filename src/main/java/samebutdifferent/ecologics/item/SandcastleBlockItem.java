package samebutdifferent.ecologics.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.SandcastleBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

public class SandcastleBlockItem extends BlockItem {
    public SandcastleBlockItem() {
        super(ModBlocks.SANDCASTLE.get(), new Properties().tab(Ecologics.TAB).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (!state.is(Blocks.TURTLE_EGG)) {
            return super.useOn(pContext);
        } else {
            level.playSound(pContext.getPlayer(), pos, SoundEvents.SAND_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlockAndUpdate(pos, ModBlocks.SANDCASTLE.get().defaultBlockState().setValue(SandcastleBlock.FACING, pContext.getHorizontalDirection().getOpposite()).setValue(SandcastleBlock.EGGS_INSIDE, state.getValue(TurtleEggBlock.EGGS)).setValue(SandcastleBlock.HATCH, state.getValue(TurtleEggBlock.HATCH)));
            pContext.getItemInHand().shrink(1);
            if (pContext.getPlayer() instanceof ServerPlayer player) {
                CriteriaTriggers.PLACED_BLOCK.trigger(player, pos, pContext.getItemInHand());
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }
}
