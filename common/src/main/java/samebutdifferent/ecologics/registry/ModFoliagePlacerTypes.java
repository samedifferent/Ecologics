package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.PalmFoliagePlacer;

import java.util.function.Supplier;

public class ModFoliagePlacerTypes {
    public static void init() {}

    public static final Supplier<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = CommonPlatformHelper.registerFoliagePlacerType("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
}
