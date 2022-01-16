package samebutdifferent.ecologics.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Ecologics.MOD_ID);

    public static final RegistryObject<SoundEvent> COCONUT_SMASH = SOUND_EVENTS.register("coconut_smash", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "block.coconut.smash")));
}
