package samebutdifferent.ecologics.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CoconutSliceItem extends Item 
{
    public CoconutSliceItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            if (pLivingEntity instanceof Player player) {
                player.removeAllEffects();
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
