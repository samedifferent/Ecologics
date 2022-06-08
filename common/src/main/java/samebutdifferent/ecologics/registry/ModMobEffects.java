package samebutdifferent.ecologics.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.effect.SlipperyMobEffect;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Ecologics.MOD_ID);

    public static final RegistryObject<MobEffect> SLIPPERY = MOB_EFFECTS.register("slippery", SlipperyMobEffect::new);
}
