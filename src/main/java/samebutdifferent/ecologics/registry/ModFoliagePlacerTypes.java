package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<CoconutFoliagePlacer> COCONUT_FOLIAGE_PLACER = Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(Ecologics.MOD_ID, "coconut_foliage_placer"), new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC));
}
