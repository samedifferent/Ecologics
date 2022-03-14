package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

public class ModFoliagePlacerTypes {

    public static final FoliagePlacerType<CoconutFoliagePlacer> COCONUT_FOLIAGE_PLACER = new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String pKey, FoliagePlacerType<P> pFoliagePlacerType) {
        return Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(Ecologics.MOD_ID, pKey), pFoliagePlacerType);
    }

    public static void register() {
        registerFoliagePlacerType("slanted_trunk_placer", COCONUT_FOLIAGE_PLACER);
    }
}
