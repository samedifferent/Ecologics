package samebutdifferent.ecologics.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static void init() {}

    public static final Supplier<SoundEvent> COCONUT_SMASH = CommonPlatformHelper.registerSoundEvent("coconut_smash", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "block.coconut.smash")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_AMBIENT = CommonPlatformHelper.registerSoundEvent("coconut_crab_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_DEATH = CommonPlatformHelper.registerSoundEvent("coconut_crab_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_HURT = CommonPlatformHelper.registerSoundEvent("coconut_crab_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final Supplier<SoundEvent> MUSIC_DISC_COCONUT = CommonPlatformHelper.registerSoundEvent("music_disc_coconut", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final Supplier<SoundEvent> THIN_ICE_CRACK = CommonPlatformHelper.registerSoundEvent("thin_ice_crack", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "block.thin_ice.crack")));
    public static final Supplier<SoundEvent> PENGUIN_AMBIENT = CommonPlatformHelper.registerSoundEvent("penguin_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.ambient")));
    public static final Supplier<SoundEvent> PENGUIN_DEATH = CommonPlatformHelper.registerSoundEvent("penguin_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.death")));
    public static final Supplier<SoundEvent> PENGUIN_HURT = CommonPlatformHelper.registerSoundEvent("penguin_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.hurt")));
    public static final Supplier<SoundEvent> SQUIRREL_AMBIENT = CommonPlatformHelper.registerSoundEvent("squirrel_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final Supplier<SoundEvent> SQUIRREL_DEATH = CommonPlatformHelper.registerSoundEvent("squirrel_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final Supplier<SoundEvent> SQUIRREL_HURT = CommonPlatformHelper.registerSoundEvent("squirrel_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.hurt")));
    public static final Supplier<SoundEvent> CAMEL_AMBIENT = CommonPlatformHelper.registerSoundEvent("camel_ambient", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.ambient")));
    public static final Supplier<SoundEvent> CAMEL_DEATH = CommonPlatformHelper.registerSoundEvent("camel_death", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.death")));
    public static final Supplier<SoundEvent> CAMEL_HURT = CommonPlatformHelper.registerSoundEvent("camel_hurt", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.hurt")));
    public static final Supplier<SoundEvent> CAMEL_ANGRY = CommonPlatformHelper.registerSoundEvent("camel_angry", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.angry")));
    public static final Supplier<SoundEvent> CAMEL_EAT = CommonPlatformHelper.registerSoundEvent("camel_eat", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.eat")));
    public static final Supplier<SoundEvent> CAMEL_STEP = CommonPlatformHelper.registerSoundEvent("camel_step", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.step")));
    public static final Supplier<SoundEvent> CAMEL_CHEST = CommonPlatformHelper.registerSoundEvent("camel_chest", () -> new SoundEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.camel.chest")));
}
