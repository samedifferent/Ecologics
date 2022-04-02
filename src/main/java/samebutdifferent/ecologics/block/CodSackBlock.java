package samebutdifferent.ecologics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CodSackBlock extends Block {
    public static final IntProperty FISH = IntProperty.of("fish", 0, 16);

    public CodSackBlock(Settings properties) {
        super(properties);
        this.stateManager.getDefaultState().with(FISH, 0);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getStackInHand(pHand);
        int fish = pState.get(FISH);
        if (fish < 16 && stack.isOf(Items.COD)) {
            pLevel.setBlockState(pPos, pState.cycle(FISH));
            pLevel.playSound(pPlayer, pPos, SoundEvents.ENTITY_COD_FLOP, SoundCategory.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.4F + 0.8F);
            if (!pPlayer.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FISH);
    }
}
