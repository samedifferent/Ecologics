package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import samebutdifferent.ecologics.mixin.TrunkPlacerTypeAccessor;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.function.Supplier;

public class ModTrunkPlacerTypes {
    public static void init() {}

    public static final Supplier<TrunkPlacerType<SlantedTrunkPlacer>> SLANTED_TRUNK_PLACER = CommonPlatformHelper.registerTrunkPlacerType("slanted_trunk_placer", () -> TrunkPlacerTypeAccessor.invokeConstructor(SlantedTrunkPlacer.CODEC));
}
