package samebutdifferent.ecologics.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Ecologics.MOD_ID);

    public static final RegistryObject<Potion> SLIDING = POTIONS.register("sliding", () -> new Potion(new MobEffectInstance(ModMobEffects.SLIPPERY.get(), 3600)));
    public static final RegistryObject<Potion> LONG_SLIDING = POTIONS.register("long_sliding", () -> new Potion("sliding", new MobEffectInstance(ModMobEffects.SLIPPERY.get(), 9600)));
}
