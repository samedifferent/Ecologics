package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import samebutdifferent.ecologics.mixin.FoliagePlacerTypeAccessor;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

import java.util.function.Supplier;

public class ModFoliagePlacerTypes {
    public static final Supplier<FoliagePlacerType<CoconutFoliagePlacer>> COCONUT_FOLIAGE_PLACER = CommonPlatformHelper.registerFoliagePlacerType("coconut_foliage_placer", () -> FoliagePlacerTypeAccessor.invokeConstructor(CoconutFoliagePlacer.CODEC));
}
