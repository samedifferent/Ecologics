package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.effect.SlipperyMobEffect;

import net.minecraft.world.effect.MobEffects;

public class ModMobEffects 
{
    public static void init() {
    	for (Pair<ResourceLocation, MobEffect> registry : MOB_EFFECTS) {
    		Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, registry.getA(), registry.getB());
    	}
    }
    
    public static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
    	// MOB_EFFECTS.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), effect));
    	// return Holder.direct(effect);
    	return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), effect);
    }

    public static final ArrayList<Pair<ResourceLocation, MobEffect>> MOB_EFFECTS = new ArrayList();
    
    public static final Holder<MobEffect> SLIPPERY = registerMobEffect("slippery", new SlipperyMobEffect());
}
