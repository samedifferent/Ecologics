package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.MossPatchFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;

import java.util.function.Supplier;

public class ModFeatures {
    public static final Supplier<CoastalFeature> COASTAL = registerFeature("coastal", () -> new CoastalFeature(SimpleBlockConfiguration.CODEC));
    public static final Supplier<ThinIceFeature> THIN_ICE = registerFeature("thin_ice", () -> new ThinIceFeature(DiskConfiguration.CODEC));
    public static final Supplier<DesertRuinFeature> DESERT_RUIN = registerFeature("desert_ruin", () -> new DesertRuinFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<MossPatchFeature> SURFACE_MOSS_PATCH = registerFeature("surface_moss_patch", () -> new MossPatchFeature(MossPatchFeatureConfiguration.CODEC));

    public static <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        return () -> Registry.register(Registry.FEATURE, new ResourceLocation(Ecologics.MOD_ID, name), feature.get());
    }
}
