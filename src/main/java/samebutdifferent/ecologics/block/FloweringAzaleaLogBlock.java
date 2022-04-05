package samebutdifferent.ecologics.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Objects;

public class FloweringAzaleaLogBlock extends PillarBlock {
    public FloweringAzaleaLogBlock() {
        super(Settings.copy(Blocks.OAK_LOG));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.isOf(Items.SHEARS)) {
            if (!world.isClient) {
                Direction direction = hit.getSide().getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : hit.getSide();
                shearAzalea(world, player, pos, itemstack, hand, direction, (Objects.equals(this.getRegistryName(), ModBlocks.FLOWERING_AZALEA_LOG.get().getRegistryName()) ? ModBlocks.AZALEA_LOG.get() : ModBlocks.AZALEA_WOOD.get()).getDefaultState().with(PillarBlock.AXIS, state.get(AXIS)));
            }

            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    public static void shearAzalea(World world, PlayerEntity player, BlockPos pos, ItemStack stack, Hand hand, Direction direction, BlockState replacementState) {
        world.playSound(null, pos, SoundEvents.FLOWERING_AZALEA_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.setBlockState(pos, replacementState, 11);
        ItemEntity itementity = new ItemEntity(world, (double) pos.getX() + 0.5D + (double) direction.getOffsetX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction.getOffsetZ() * 0.65D, new ItemStack(ModBlocks.AZALEA_FLOWER.get().asItem()));
        itementity.setVelocity(0.05D * (double) direction.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction.getOffsetZ() + world.random.nextDouble() * 0.02D);
        world.spawnEntity(itementity);
        stack.damage(1, player, (player1) -> player1.sendToolBreakStatus(hand));
        world.emitGameEvent(player, GameEvent.SHEAR, pos);
        player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
    }
}