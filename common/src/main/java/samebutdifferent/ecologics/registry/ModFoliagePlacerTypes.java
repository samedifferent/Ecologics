package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModFoliagePlacerTypes
{
    public static void init() {
    	for (Pair<ResourceLocation, FoliagePlacerType<?>> registry : FOLIAGE_PLACERS) {
    		Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, registry.getA(), registry.getB());
    	}
    }
    
    public static FoliagePlacerType registerFoliagePlacerType(String name, FoliagePlacerType<?> foliage) {
    	FOLIAGE_PLACERS.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), foliage));
    	return foliage;
    }

    public static final ArrayList<Pair<ResourceLocation, FoliagePlacerType<?>>> FOLIAGE_PLACERS = new ArrayList();
    
    public static final FoliagePlacerType<CoconutFoliagePlacer> COCONUT_FOLIAGE_PLACER = registerFoliagePlacerType("coconut_foliage_placer", new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC));
}
