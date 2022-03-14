package samebutdifferent.ecologics.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import samebutdifferent.ecologics.Ecologics;

public class PricklyPearItem extends Item {
    public PricklyPearItem() {
        super(new Settings().group(Ecologics.TAB).food(FoodComponents.APPLE));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World level, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity player) {
            if (player.hasStatusEffect(StatusEffects.WITHER)) {
                player.removeStatusEffect(StatusEffects.WITHER);
            }
            player.damage(DamageSource.CACTUS, 1.0F);
        }
        return super.finishUsing(stack, level, livingEntity);
    }
}
