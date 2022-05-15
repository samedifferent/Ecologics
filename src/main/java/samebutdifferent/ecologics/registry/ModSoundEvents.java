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
    public static final RegistryObject<SoundEvent> MUSIC_DISC_COCONUT = SOUND_EVENTS.register("music_disc_coconut", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final RegistryObject<SoundEvent> THIN_ICE_CRACK = SOUND_EVENTS.register("thin_ice_crack", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "block.thin_ice.crack")));
    public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = SOUND_EVENTS.register("penguin_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.ambient")));
    public static final RegistryObject<SoundEvent> PENGUIN_DEATH = SOUND_EVENTS.register("penguin_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.death")));
    public static final RegistryObject<SoundEvent> PENGUIN_HURT = SOUND_EVENTS.register("penguin_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.hurt")));
    public static final RegistryObject<SoundEvent> SQUIRREL_AMBIENT = SOUND_EVENTS.register("squirrel_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final RegistryObject<SoundEvent> SQUIRREL_DEATH = SOUND_EVENTS.register("squirrel_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final RegistryObject<SoundEvent> SQUIRREL_HURT = SOUND_EVENTS.register("squirrel_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.hurt")));
    public static final RegistryObject<SoundEvent> CAMEL_AMBIENT = SOUND_EVENTS.register("camel_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.ambient")));
    public static final RegistryObject<SoundEvent> CAMEL_DEATH = SOUND_EVENTS.register("camel_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.death")));
    public static final RegistryObject<SoundEvent> CAMEL_HURT = SOUND_EVENTS.register("camel_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.hurt")));
    public static final RegistryObject<SoundEvent> CAMEL_ANGRY = SOUND_EVENTS.register("camel_angry", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.angry")));
    public static final RegistryObject<SoundEvent> CAMEL_EAT = SOUND_EVENTS.register("camel_eat", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.eat")));
    public static final RegistryObject<SoundEvent> CAMEL_STEP = SOUND_EVENTS.register("camel_step", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.step")));
    public static final RegistryObject<SoundEvent> CAMEL_CHEST = SOUND_EVENTS.register("camel_chest", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.chest")));
}
