package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;

public class ModFeatures {
    public static final CoastalFeature COASTAL = Registry.register(Registry.FEATURE, new Identifier(Ecologics.MOD_ID, "coastal"), new CoastalFeature(SimpleBlockFeatureConfig.CODEC));
    public static final ThinIceFeature THIN_ICE = Registry.register(Registry.FEATURE, new Identifier(Ecologics.MOD_ID, "thin_ice"), new ThinIceFeature(DiskFeatureConfig.CODEC));
    public static final DesertRuinFeature DESERT_RUIN = Registry.register(Registry.FEATURE, new Identifier(Ecologics.MOD_ID, "desert_ruin"), new DesertRuinFeature(DefaultFeatureConfig.CODEC));
}
