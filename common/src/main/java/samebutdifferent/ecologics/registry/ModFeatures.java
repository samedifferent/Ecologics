package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.CoastalFeature;
import samebutdifferent.ecologics.worldgen.feature.DesertRuinFeature;
import samebutdifferent.ecologics.worldgen.feature.ThinIceFeature;

public class ModFeatures 
{
    public static void init() {
    	for (Pair<ResourceLocation, Feature> registry : FEATURES) {
    		Registry.register(BuiltInRegistries.FEATURE, registry.getA(), registry.getB());
    	}
    }
    
    public static Feature registerFeature(String name, Feature feature) {
    	FEATURES.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), feature));
    	return feature;
    }
    
    public static final ArrayList<Pair<ResourceLocation, Feature>> FEATURES = new ArrayList();

    public static final Feature COASTAL = registerFeature("coastal", new CoastalFeature(SimpleBlockConfiguration.CODEC));
    public static final Feature THIN_ICE = registerFeature("thin_ice", new ThinIceFeature(DiskConfiguration.CODEC));
    public static final Feature DESERT_RUIN = registerFeature("desert_ruin", new DesertRuinFeature(NoneFeatureConfiguration.CODEC));
    
    public static final Feature AZALEA_TREE = registerFeature("azalea_tree", new TreeFeature(TreeConfiguration.CODEC));
    public static final Feature COCONUT_TREE = registerFeature("coconut_tree", new TreeFeature(TreeConfiguration.CODEC));
    public static final Feature WALNUT_TREE = registerFeature("walnut_tree", new TreeFeature(TreeConfiguration.CODEC));
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROOTED_AZALEA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "rooted_azalea_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_REGULAR = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> WALNUT_REGULAR = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "walnut_tree"));
}
