package samebutdifferent.ecologics.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;
import samebutdifferent.ecologics.Ecologics;

public class ModJukeboxSongs
{
	public static final ResourceKey<JukeboxSong> COCONUT = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut"));
}
