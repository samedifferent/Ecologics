package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModTrunkPlacerTypes<P extends TrunkPlacer>
{
    public static void init() {
    	for (Pair<Identifier, TrunkPlacerType<?>> registry : TRUNK_PLACERS) {
    		Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, registry.getA(), registry.getB());
    	}
    }

    public static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacerType(String name, TrunkPlacerType<P> trunk) {
    	TRUNK_PLACERS.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), trunk));
    	return trunk;
    }
    
    public static final ArrayList<Pair<Identifier, TrunkPlacerType<?>>> TRUNK_PLACERS = new ArrayList<>();
    
    public static final TrunkPlacerType<SlantedTrunkPlacer> SLANTED_TRUNK_PLACER = registerTrunkPlacerType("slanted_trunk_placer", new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC));
}
