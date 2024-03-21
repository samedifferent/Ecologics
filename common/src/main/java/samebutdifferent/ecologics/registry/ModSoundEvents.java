package samebutdifferent.ecologics.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static void init() {}

    public static final Supplier<SoundEvent> COCONUT_SMASH = CommonPlatformHelper.registerSoundEvent("coconut_smash", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "block.coconut.smash")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_AMBIENT = CommonPlatformHelper.registerSoundEvent("coconut_crab_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_DEATH = CommonPlatformHelper.registerSoundEvent("coconut_crab_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final Supplier<SoundEvent> COCONUT_CRAB_HURT = CommonPlatformHelper.registerSoundEvent("coconut_crab_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final Supplier<SoundEvent> MUSIC_DISC_COCONUT = CommonPlatformHelper.registerSoundEvent("music_disc_coconut", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final Supplier<SoundEvent> THIN_ICE_CRACK = CommonPlatformHelper.registerSoundEvent("thin_ice_crack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "block.thin_ice.crack")));
    public static final Supplier<SoundEvent> PENGUIN_AMBIENT = CommonPlatformHelper.registerSoundEvent("penguin_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.ambient")));
    public static final Supplier<SoundEvent> PENGUIN_DEATH = CommonPlatformHelper.registerSoundEvent("penguin_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.death")));
    public static final Supplier<SoundEvent> PENGUIN_HURT = CommonPlatformHelper.registerSoundEvent("penguin_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.penguin.hurt")));
    public static final Supplier<SoundEvent> SQUIRREL_AMBIENT = CommonPlatformHelper.registerSoundEvent("squirrel_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final Supplier<SoundEvent> SQUIRREL_DEATH = CommonPlatformHelper.registerSoundEvent("squirrel_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final Supplier<SoundEvent> SQUIRREL_HURT = CommonPlatformHelper.registerSoundEvent("squirrel_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Ecologics.MOD_ID, "entity.squirrel.hurt")));
}
