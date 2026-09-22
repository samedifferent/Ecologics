package samebutdifferent.ecologics.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class MaplePieItem extends BlockItem
{
    public MaplePieItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }
    
    @Override
    public Block getBlock() {
        return ModBlocks.MAPLE_PIE; // Workaround.
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Ecologics.LOGGER.info("Attempting to use pie.");
        if (Ecologics.farmersDelight != null && this.getBlock() != null) {
            InteractionResult result = this.place(new BlockPlaceContext(context));
            if (result.consumesAction()) {
                return context.getLevel().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }
}
