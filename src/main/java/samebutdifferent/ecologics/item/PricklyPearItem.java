package samebutdifferent.ecologics.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import samebutdifferent.ecologics.Ecologics;

public class PricklyPearItem extends Item {
    public PricklyPearItem() {
        super(new Properties().tab(Ecologics.TAB).food(Foods.APPLE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (player.hasEffect(MobEffects.WITHER)) {
                player.removeEffect(MobEffects.WITHER);
            }
            player.hurt(DamageSource.CACTUS, 1.0F);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
