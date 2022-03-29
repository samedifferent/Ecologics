package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Objects;

public class FloweringAzaleaLogBlock extends RotatedPillarBlock {
    public FloweringAzaleaLogBlock() {
        super(Properties.copy(Blocks.OAK_LOG));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.SHEARS)) {
            if (!world.isClientSide) {
                Direction direction = hitResult.getDirection().getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : hitResult.getDirection();
                shearAzalea(world, player, pos, itemstack, hand, direction, (Objects.equals(this.getRegistryName(), ModBlocks.FLOWERING_AZALEA_LOG.get().getRegistryName()) ? ModBlocks.AZALEA_LOG.get() : ModBlocks.AZALEA_WOOD.get()).defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(AXIS)));
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return super.use(state, world, pos, player, hand, hitResult);
        }
    }

    public static void shearAzalea(Level world, Player player, BlockPos pos, ItemStack stack, InteractionHand hand, Direction direction, BlockState replacementState) {
        world.playSound(null, pos, SoundEvents.FLOWERING_AZALEA_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.setBlock(pos, replacementState, 11);
        ItemEntity itementity = new ItemEntity(world, (double) pos.getX() + 0.5D + (double) direction.getStepX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction.getStepZ() * 0.65D, new ItemStack(ModBlocks.AZALEA_FLOWER.get().asItem()));
        itementity.setDeltaMovement(0.05D * (double) direction.getStepX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction.getStepZ() + world.random.nextDouble() * 0.02D);
        world.addFreshEntity(itementity);
        stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        world.gameEvent(player, GameEvent.SHEAR, pos);
        player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
    }
}
