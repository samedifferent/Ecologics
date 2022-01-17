package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

public class ModFoliagePlacerTypes {

    public static final FoliagePlacerType<CoconutFoliagePlacer> COCONUT_FOLIAGE_PLACER = new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String pKey, FoliagePlacerType<P> pFoliagePlacerType) {
        return Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(Ecologics.MOD_ID, pKey), pFoliagePlacerType);
    }

    public static void register() {
        registerFoliagePlacerType("slanted_trunk_placer", COCONUT_FOLIAGE_PLACER);
    }
}
