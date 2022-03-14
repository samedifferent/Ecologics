package samebutdifferent.ecologics.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;

public class ModSoundEvents {
    public static final SoundEvent COCONUT_SMASH = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "coconut_smash"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "block.coconut.smash")));
    public static final SoundEvent COCONUT_CRAB_AMBIENT = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "coconut_crab_ambient"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final SoundEvent COCONUT_CRAB_DEATH = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "coconut_crab_death"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final SoundEvent COCONUT_CRAB_HURT = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "coconut_crab_hurt"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final SoundEvent MUSIC_DISC_COCONUT = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "music_disc_coconut"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final SoundEvent THIN_ICE_CRACK = Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, "thin_ice_crack"), new SoundEvent(new Identifier(Ecologics.MOD_ID, "block.thin_ice.crack")));
}
