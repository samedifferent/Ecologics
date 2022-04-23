package samebutdifferent.ecologics.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;

import java.util.function.Supplier;

public class ModSoundEvents {

    public static void init(){
    }

    public static final SoundEvent COCONUT_SMASH = registerSoundEvent("coconut_smash", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "block.coconut.smash")));
    public static final SoundEvent COCONUT_CRAB_AMBIENT = registerSoundEvent("coconut_crab_ambient", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final SoundEvent COCONUT_CRAB_DEATH = registerSoundEvent("coconut_crab_death", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final SoundEvent COCONUT_CRAB_HURT = registerSoundEvent("coconut_crab_hurt", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final SoundEvent MUSIC_DISC_COCONUT = registerSoundEvent("music_disc_coconut", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final SoundEvent THIN_ICE_CRACK = registerSoundEvent("thin_ice_crack", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "block.thin_ice.crack")));
    public static final SoundEvent PENGUIN_AMBIENT = registerSoundEvent("penguin_ambient", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.penguin.ambient")));
    public static final SoundEvent PENGUIN_DEATH = registerSoundEvent("penguin_death", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.penguin.death")));
    public static final SoundEvent PENGUIN_HURT = registerSoundEvent("penguin_hurt", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.penguin.hurt")));
    public static final SoundEvent SQUIRREL_AMBIENT = registerSoundEvent("squirrel_ambient", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final SoundEvent SQUIRREL_DEATH = registerSoundEvent("squirrel_death", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final SoundEvent SQUIRREL_HURT = registerSoundEvent("squirrel_hurt", () -> new SoundEvent(new Identifier(Ecologics.MOD_ID, "entity.squirrel.hurt")));

    private static <T extends SoundEvent> T registerSoundEvent(String name, Supplier<T> soundEvent) {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(Ecologics.MOD_ID, name), soundEvent.get());
    }
}
