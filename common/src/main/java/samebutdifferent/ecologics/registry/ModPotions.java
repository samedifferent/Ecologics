package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;

public class ModPotions 
{
    public static void init() {
    	for (Pair<Identifier, Potion> registry : POTIONS) {
    		Registry.register(BuiltInRegistries.POTION, registry.getA(), registry.getB());
    	}
    }
    
    public static Holder<Potion> registerPotion(String name, Potion potion) {
    	return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), potion);
    }
    
    public static final ArrayList<Pair<Identifier, Potion>> POTIONS = new ArrayList<>();

    public static final Holder<Potion> SLIDING = registerPotion("sliding", new Potion("sliding", new MobEffectInstance(ModMobEffects.SLIPPERY, 3600)));
    public static final Holder<Potion> LONG_SLIDING = registerPotion("long_sliding", new Potion("sliding", new MobEffectInstance(ModMobEffects.SLIPPERY, 9600)));
}
