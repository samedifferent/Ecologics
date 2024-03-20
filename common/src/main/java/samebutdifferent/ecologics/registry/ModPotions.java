package samebutdifferent.ecologics.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModPotions {

    public static void init() {}

    public static final Supplier<Potion> SLIDING = CommonPlatformHelper.registerPotion("sliding", () -> new Potion(new MobEffectInstance(ModMobEffects.SLIPPERY.get(), 3600)));
    public static final Supplier<Potion> LONG_SLIDING = CommonPlatformHelper.registerPotion("long_sliding", () -> new Potion("sliding", new MobEffectInstance(ModMobEffects.SLIPPERY.get(), 9600)));

}
