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
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import samebutdifferent.ecologics.Ecologics;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> TREES_BEACH = register("trees_beach", ModConfiguredFeatures.COCONUT, List.of(PlacedFeatures.createCountExtraModifier(0, 0.5F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> SEASHELL = register("seashell", ModConfiguredFeatures.SEASHELL, VegetationPlacedFeatures.modifiers(4));
    public static final RegistryEntry<PlacedFeature> THIN_ICE_PATCH = register("thin_ice_patch", ModConfiguredFeatures.THIN_ICE_PATCH, List.of(CountPlacementModifier.of(15), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> PRICKLY_PEAR = register("prickly_pear", ModConfiguredFeatures.PRICKLY_PEAR, List.of(CountPlacementModifier.of(128), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> DESERT_RUIN = register("desert_ruin", ModConfiguredFeatures.DESERT_RUIN, List.of(RarityFilterPlacementModifier.of(13), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> TREES_WALNUT = register("trees_walnut", ModConfiguredFeatures.WALNUT, List.of(PlacedFeatures.createCountExtraModifier(0, 0.05F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> ROOTED_AZALEA_TREE = register("rooted_azalea_tree", ModConfiguredFeatures.ROOTED_AZALEA_TREE, List.of(CountPlacementModifier.of(UniformIntProvider.create(1, 2)), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BiomePlacementModifier.of()));
    public static final RegistryEntry<PlacedFeature> SURFACE_MOSS_PATCH = register("surface_moss_patch", ModConfiguredFeatures.SURFACE_MOSS_PATCH, List.of(CountPlacementModifier.of(256), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of()));

    private static RegistryEntry<PlacedFeature> register(String name, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        return PlacedFeatures.register(new Identifier(Ecologics.MOD_ID, name).toString(), feature, modifiers);
    }
}
