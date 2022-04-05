package samebutdifferent.ecologics.registry;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import samebutdifferent.ecologics.Ecologics;

import java.util.List;

public class ModPlacedFeatures {
    public static final PlacedFeature TREES_BEACH = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.COCONUT), List.of(PlacedFeatures.createCountExtraModifier(0, 0.5F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature SEASHELL = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.SEASHELL), VegetationPlacedFeatures.modifiers(4));
    public static final PlacedFeature THIN_ICE_PATCH = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.THIN_ICE_PATCH), List.of(CountPlacementModifier.of(15), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature PRICKLY_PEAR = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.PRICKLY_PEAR), List.of(CountPlacementModifier.of(128), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature DESERT_RUIN = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.DESERT_RUIN), List.of(RarityFilterPlacementModifier.of(13), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature TREES_WALNUT = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.WALNUT), List.of(PlacedFeatures.createCountExtraModifier(0, 0.05F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature ROOTED_AZALEA_TREE = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.ROOTED_AZALEA_TREE), List.of(CountPlacementModifier.of(UniformIntProvider.create(1, 2)), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BiomePlacementModifier.of()));
    public static final PlacedFeature SURFACE_MOSS_PATCH = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.SURFACE_MOSS_PATCH), List.of(CountPlacementModifier.of(256), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of()));

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerPlacedFeature("trees_beach", TREES_BEACH);
        registerPlacedFeature("seashell", SEASHELL);
        registerPlacedFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerPlacedFeature("prickly_pear", PRICKLY_PEAR);
        registerPlacedFeature("desert_ruin", DESERT_RUIN);
        registerPlacedFeature("trees_walnut", TREES_WALNUT);
        registerPlacedFeature("surface_moss_patch", SURFACE_MOSS_PATCH);
    }
}
