package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.MossPatchFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Ecologics.MOD_ID);

    public static final RegistryObject<CoastalFeature> COASTAL = FEATURES.register("coastal", () -> new CoastalFeature(SimpleBlockConfiguration.CODEC));
    public static final RegistryObject<ThinIceFeature> THIN_ICE = FEATURES.register("thin_ice", () -> new ThinIceFeature(DiskConfiguration.CODEC));
    public static final RegistryObject<DesertRuinFeature> DESERT_RUIN = FEATURES.register("desert_ruin", () -> new DesertRuinFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<MossPatchFeature> SURFACE_MOSS_PATCH = FEATURES.register("surface_moss_patch", () -> new MossPatchFeature(MossPatchFeatureConfiguration.CODEC));
}
