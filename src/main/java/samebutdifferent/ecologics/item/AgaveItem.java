package samebutdifferent.ecologics.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class AgaveItem extends ItemNameBlockItem {
    public AgaveItem() {
        super(ModBlocks.AGAVE.get(), new Properties().tab(Ecologics.TAB).food(new FoodProperties.Builder().alwaysEat().fast().nutrition(4).saturationMod(0.3F).build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player && player.hasEffect(MobEffects.WITHER)) {
            player.removeEffect(MobEffects.WITHER);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
