package samebutdifferent.ecologics.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods 
{
	public static void init() {}
	
    public static final FoodProperties MAPLE_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).effect(new MobEffectInstance(MobEffects.SATURATION, 80), 1.0F).build();
    public static final FoodProperties MAPLE_PIE_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).effect(new MobEffectInstance(MobEffects.SATURATION, 20), 1.0F).fast().build();
    
    // Rustic Delight Integration
    public static final FoodProperties MAPLE_PANCAKE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
}
