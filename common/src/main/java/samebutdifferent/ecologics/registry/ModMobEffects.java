package samebutdifferent.ecologics.registry;

import net.minecraft.world.effect.MobEffect;
import samebutdifferent.ecologics.effect.SlipperyMobEffect;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModMobEffects {
    public static final Supplier<MobEffect> SLIPPERY = CommonPlatformHelper.registerMobEffect("slippery", SlipperyMobEffect::new);
}
