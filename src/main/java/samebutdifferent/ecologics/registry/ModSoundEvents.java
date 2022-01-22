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
    public static final RegistryObject<SoundEvent> COCONUT_CRAB_AMBIENT = SOUND_EVENTS.register("coconut_crab_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final RegistryObject<SoundEvent> COCONUT_CRAB_DEATH = SOUND_EVENTS.register("coconut_crab_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final RegistryObject<SoundEvent> COCONUT_CRAB_HURT = SOUND_EVENTS.register("coconut_crab_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final RegistryObject<SoundEvent> SQUIRREL_AMBIENT = SOUND_EVENTS.register("squirrel_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final RegistryObject<SoundEvent> SQUIRREL_DEATH = SOUND_EVENTS.register("squirrel_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final RegistryObject<SoundEvent> SQUIRREL_HURT = SOUND_EVENTS.register("squirrel_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.hurt")));
    public static final RegistryObject<SoundEvent> MUSIC_DISC_COCONUT = SOUND_EVENTS.register("music_disc_coconut", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "music_disc.coconut")));
}
