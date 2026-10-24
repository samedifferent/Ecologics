package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods 
{
    public static final FoodProperties COCONUT_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties CRAB_MEAT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties TROPICAL_STEW = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6F).build();
    public static final FoodProperties PRICKLY_PEAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_PRICKLY_PEAR = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
    public static final FoodProperties WALNUT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.0F).build();
    public static final FoodProperties MAPLE_SYRUP_BOTTLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
    public static final FoodProperties MAPLE_COOKIE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
    public static final FoodProperties MAPLE_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();
    public static final FoodProperties MAPLE_PIE_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).build();
    
    // Rustic Delight Integration
    public static final FoodProperties MAPLE_PANCAKE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
}
