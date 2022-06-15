package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.worldgen.feature.placement.CoconutTreePlacement;
import samebutdifferent.ecologics.worldgen.feature.placement.ThinIcePlacement;

import java.util.List;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> TREES_BEACH = PlacementUtils.register("trees_beach", ModConfiguredFeatures.COCONUT, List.of(new CoconutTreePlacement(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> SEASHELL = PlacementUtils.register("seashell", ModConfiguredFeatures.SEASHELL, VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final Holder<PlacedFeature> THIN_ICE_PATCH = PlacementUtils.register("thin_ice_patch", ModConfiguredFeatures.THIN_ICE_PATCH, List.of(new ThinIcePlacement(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> PRICKLY_PEAR = PlacementUtils.register("prickly_pear", ModConfiguredFeatures.PRICKLY_PEAR, List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> DESERT_RUIN = PlacementUtils.register("desert_ruin", ModConfiguredFeatures.DESERT_RUIN, List.of(RarityFilter.onAverageOnceEvery(13), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> TREES_WALNUT = PlacementUtils.register("trees_walnut", ModConfiguredFeatures.WALNUT, List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> ROOTED_AZALEA_TREE = PlacementUtils.register("rooted_azalea_tree", ModConfiguredFeatures.ROOTED_AZALEA_TREE, List.of(CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> SURFACE_MOSS_PATCH = PlacementUtils.register("surface_moss_patch", ModConfiguredFeatures.SURFACE_MOSS_PATCH, List.of(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
}
