package samebutdifferent.ecologics.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;

import java.util.function.Supplier;

public class ModPotions {
    public static final Potion SLIDING = registerPotion("sliding", () -> new Potion(new StatusEffectInstance(ModMobEffects.SLIPPERY, 3600)));
    public static final Potion LONG_SLIDING = registerPotion("long_sliding", () -> new Potion("sliding", new StatusEffectInstance(ModMobEffects.SLIPPERY, 9600)));

    public static <T extends Potion> T registerPotion(String name, Supplier<T> potion) {
        return Registry.register(Registry.POTION, new Identifier(Ecologics.MOD_ID, name), potion.get());
    }
}
