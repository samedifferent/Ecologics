package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;

import java.util.function.Supplier;

public class ModFeatures {
    public static void init() {}

    public static final Supplier<CoastalFeature> COASTAL = CommonPlatformHelper.registerFeature("coastal", () -> new CoastalFeature(SimpleBlockConfiguration.CODEC));
    public static final Supplier<ThinIceFeature> THIN_ICE = CommonPlatformHelper.registerFeature("thin_ice", () -> new ThinIceFeature(DiskConfiguration.CODEC));
    public static final Supplier<DesertRuinFeature> DESERT_RUIN = CommonPlatformHelper.registerFeature("desert_ruin", () -> new DesertRuinFeature(NoneFeatureConfiguration.CODEC));
}
