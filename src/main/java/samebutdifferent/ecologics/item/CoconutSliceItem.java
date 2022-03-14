package samebutdifferent.ecologics.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class CoconutSliceItem extends Item {
    public CoconutSliceItem(Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack pStack, World pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClient) {
            if (pLivingEntity instanceof PlayerEntity player) {
                player.clearStatusEffects();
                ItemStack mainHandStack = player.getMainHandStack();
                ItemStack coconutHuskStack = new ItemStack(ModBlocks.COCONUT_HUSK);
                if (!player.getAbilities().creativeMode) {
                    if (!mainHandStack.isEmpty()) {
                        if (!player.getInventory().insertStack(coconutHuskStack.copy())) {
                            player.dropItem(coconutHuskStack, false);
                        }
                    }
                }
            }
        }
        return super.finishUsing(pStack, pLevel, pLivingEntity);
    }
}
