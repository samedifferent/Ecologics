package samebutdifferent.ecologics.registry.forge;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.forge.CommonPlatformHelperImpl;
import samebutdifferent.ecologics.util.forge.VanillaJsonFeature;

import java.util.List;

public class ModFeaturesForge {
    public static final FeatureContainer TREES_BEACH = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"trees_beach"));
    public static final FeatureContainer SEASHELL = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"seashell"));
    public static final FeatureContainer PRICKLY_PEAR = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"prickly_pear"));
    public static final FeatureContainer DESERT_RUIN = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"desert_ruin"));
    public static final FeatureContainer THIN_ICE_PATCH = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"thin_ice_patch"));
    public static final FeatureContainer SURFACE_MOSS_PATCH = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"surface_moss_patch"));
    public static final FeatureContainer ROOTED_AZALEA_TREE = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"rooted_azalea_tree"));
    public static final FeatureContainer TREES_WALNUT = FeatureContainer.vanillaJsonFeature(new ResourceLocation(Ecologics.MOD_ID,"trees_walnut"));

    public record FeatureContainer(RegistryObject<? extends Feature<? extends FeatureConfiguration>> feature, NonNullLazy<ConfiguredFeature<? extends FeatureConfiguration, ? extends Feature<? extends FeatureConfiguration>>> configuredFeature, NonNullLazy<PlacedFeature> placedFeature) {
        static FeatureContainer vanillaJsonFeature(ResourceLocation placedFeatureId) {
            NonNullLazy<ConfiguredFeature<? extends FeatureConfiguration, ? extends Feature<? extends FeatureConfiguration>>> configuredFeature = NonNullLazy.of(() -> new ConfiguredFeature(CommonPlatformHelperImpl.VANILLA_JSON_FEATURE.get(), new VanillaJsonFeature.VanillaJsonFeatureConfig(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, placedFeatureId))));

            return new FeatureContainer(CommonPlatformHelperImpl.VANILLA_JSON_FEATURE, configuredFeature, NonNullLazy.of(() -> new PlacedFeature(Holder.direct(configuredFeature.get()), List.of())));
        }
    }
}
