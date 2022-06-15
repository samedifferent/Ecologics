package samebutdifferent.ecologics.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class CoconutSliceItem extends Item {
    public CoconutSliceItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            if (pLivingEntity instanceof Player player) {
                player.removeAllEffects();
                ItemStack mainHandStack = player.getMainHandItem();
                ItemStack coconutHuskStack = new ItemStack(ModBlocks.COCONUT_HUSK.get());
                if (!player.getAbilities().instabuild) {
                    if (!mainHandStack.isEmpty()) {
                        if (!player.getInventory().add(coconutHuskStack.copy())) {
                            player.drop(coconutHuskStack, false);
                        }
                    }
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
