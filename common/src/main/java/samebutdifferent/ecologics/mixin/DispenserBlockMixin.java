package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.SpileBlock;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin 
{
    @Inject(method = "dispenseFrom(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"), cancellable = true)
    private void interceptEmptyDispenser(ServerLevel level, BlockState state, BlockPos pos, CallbackInfo ci) {
        if (level.isClientSide()) {
            return;
        }
        DispenserBlockEntity dispenser = (DispenserBlockEntity) level.getBlockEntity(pos);
        if (dispenser == null) {
            return;
        }
        // Check if the dispenser has picked an empty slot or is totally empty
        int slotIndex = dispenser.getRandomSlot(level.random);
        if (slotIndex < 0 || dispenser.getItem(slotIndex).isEmpty()) {
            Direction facing = state.getValue(DispenserBlock.FACING);
            BlockPos targetPos = pos.relative(facing);
            BlockState targetState = level.getBlockState(targetPos);
            // Is the dispenser looking directly at a Spile?
            if (targetState.getBlock() instanceof SpileBlock) {
                boolean hasBucketOnSpile = targetState.getValue(SpileBlock.HAS_BUCKET);
                if (hasBucketOnSpile) {
                    boolean isBucketFull = targetState.getValue(SpileBlock.FULLNESS) >= 3;
                    ItemStack rewardItem = isBucketFull ? new ItemStack(ModItems.MAPLE_SAP_BUCKET) : new ItemStack(Items.BUCKET);
                    ItemStack remaining = dispenser.insertItem(rewardItem);
                    if (!remaining.isEmpty()) {
                        double x = pos.getX() + 0.5 + facing.getStepX() * 0.7;
                        double y = pos.getY() + 0.5 + facing.getStepY() * 0.7;
                        double z = pos.getZ() + 0.5 + facing.getStepZ() * 0.7;
                        ItemEntity itemEntity = new ItemEntity(level, x, y, z, remaining);
                        level.addFreshEntity(itemEntity);
                    }
                    BlockState newState = targetState.setValue(SpileBlock.HAS_BUCKET, false).setValue(SpileBlock.FULLNESS, 0);
                    level.setBlock(targetPos, newState, 3);
                    level.levelEvent(1000, pos, 0); 
                    ci.cancel();
                }
            }
        }
    }
}