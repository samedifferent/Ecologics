package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods 
{
    public static final FoodProperties COCONUT_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).usingConvertsTo(ModItems.COCONUT_HUSK).build();
    public static final FoodProperties CRAB_MEAT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build();
    public static final FoodProperties TROPICAL_STEW = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).usingConvertsTo(ModItems.COCONUT_HUSK).build();
    public static final FoodProperties PRICKLY_PEAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build();
    public static final FoodProperties COOKED_PRICKLY_PEAR = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build();
    public static final FoodProperties WALNUT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.0F).fast().build();
}
