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
    public CoconutSliceItem() {
        super(new Item.Properties().tab(Ecologics.TAB).food(Foods.APPLE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            ItemStack mainHandStack = player.getMainHandItem();
            ItemStack coconutHuskStack = new ItemStack(ModBlocks.COCONUT_HUSK.get());
            if (!mainHandStack.isEmpty()) {
                if (!player.getInventory().add(coconutHuskStack.copy())) {
                    player.drop(coconutHuskStack, false);
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
