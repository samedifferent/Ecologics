package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.MossPatchFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;

import java.util.function.Supplier;

public class ModFeatures {

    public static void init(){}

    public static final CoastalFeature COASTAL = registerFeature("coastal", () -> new CoastalFeature(SimpleBlockFeatureConfig.CODEC));
    public static final ThinIceFeature THIN_ICE = registerFeature("thin_ice", () -> new ThinIceFeature(DiskFeatureConfig.CODEC));
    public static final DesertRuinFeature DESERT_RUIN = registerFeature("desert_ruin", () -> new DesertRuinFeature(DefaultFeatureConfig.CODEC));
    public static final MossPatchFeature SURFACE_MOSS_PATCH = registerFeature("surface_moss_patch", () -> new MossPatchFeature(MossPatchFeatureConfiguration.CODEC));

    public static <T extends Feature<?>> T registerFeature(String name, Supplier<T> feature) {
        return Registry.register(Registry.FEATURE, new Identifier(Ecologics.MOD_ID, name), feature.get());
    }
}
