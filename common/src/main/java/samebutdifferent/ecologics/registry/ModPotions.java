package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

public class ModPotions 
{
    public static void init() {
    	for (Pair<ResourceLocation, Potion> registry : POTIONS) {
    		Registry.register(BuiltInRegistries.POTION, registry.getA(), registry.getB());
    	}
    }
    
    public static Holder<Potion> registerPotion(String name, Potion potion) {
    	//POTIONS.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), potion));
    	//return potion;
    	return Registry.registerForHolder(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), potion);
    }
    
    public static final ArrayList<Pair<ResourceLocation, Potion>> POTIONS = new ArrayList();

    public static final Holder<Potion> SLIDING = registerPotion("sliding", new Potion(new MobEffectInstance(ModMobEffects.SLIPPERY, 3600)));
    public static final Holder<Potion> LONG_SLIDING = registerPotion("long_sliding", new Potion("sliding", new MobEffectInstance(ModMobEffects.SLIPPERY, 9600)));
}
