package samebutdifferent.ecologics.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModConsumables 
{
    public static final Consumable MAPLE_SYRUP_BOTTLE = Consumables.defaultDrink().consumeSeconds(2.0F).sound(SoundEvents.HONEY_DRINK).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 200, 0))).build();
    public static final Consumable MAPLE_PIE = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SATURATION, 20, 0))).build();
    public static final Consumable MAPLE_PIE_SLICE = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0))).build();
}
