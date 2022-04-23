package samebutdifferent.ecologics.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.effect.SlipperyMobEffect;

import java.util.function.Supplier;

public class ModMobEffects {

    public static void init(){}

    public static final StatusEffect SLIPPERY = registerStatusEffect("slippery", SlipperyMobEffect::new);

    public static <T extends StatusEffect> T registerStatusEffect(String name, Supplier<T> statusEffect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Ecologics.MOD_ID, name), statusEffect.get());
    }
}
