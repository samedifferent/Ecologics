package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;

public class ModSoundEvents 
{
    public static void init() {
    	for (Pair<ResourceLocation, SoundEvent> registry : SOUND_EVENTS) {
    		Registry.register(BuiltInRegistries.SOUND_EVENT, registry.getA(), registry.getB());
    	}
    }
    
    public static SoundEvent registerSoundEvent(String name, SoundEvent sound) {
    	SOUND_EVENTS.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), sound));
    	return sound;
    }
    
    public static final ArrayList<Pair<ResourceLocation, SoundEvent>> SOUND_EVENTS = new ArrayList();

    public static final SoundEvent COCONUT_SMASH = registerSoundEvent("coconut_smash", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "block.coconut.smash")));
    public static final SoundEvent COCONUT_CRAB_AMBIENT = registerSoundEvent("coconut_crab_ambient", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.coconut_crab.ambient")));
    public static final SoundEvent COCONUT_CRAB_DEATH = registerSoundEvent("coconut_crab_death", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.coconut_crab.death")));
    public static final SoundEvent COCONUT_CRAB_HURT = registerSoundEvent("coconut_crab_hurt", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.coconut_crab.hurt")));
    public static final SoundEvent MUSIC_DISC_COCONUT = registerSoundEvent("music_disc.coconut", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "music_disc.coconut")));
    public static final SoundEvent THIN_ICE_CRACK = registerSoundEvent("thin_ice_crack", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "block.thin_ice.crack")));
    public static final SoundEvent PENGUIN_AMBIENT = registerSoundEvent("penguin_ambient", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.penguin.ambient")));
    public static final SoundEvent PENGUIN_DEATH = registerSoundEvent("penguin_death", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.penguin.death")));
    public static final SoundEvent PENGUIN_HURT = registerSoundEvent("penguin_hurt", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.penguin.hurt")));
    public static final SoundEvent SQUIRREL_AMBIENT = registerSoundEvent("squirrel_ambient", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.squirrel.ambient")));
    public static final SoundEvent SQUIRREL_DEATH = registerSoundEvent("squirrel_death", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.squirrel.death")));
    public static final SoundEvent SQUIRREL_HURT = registerSoundEvent("squirrel_hurt", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity.squirrel.hurt")));
}
