package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;

import java.util.List;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> TREES_BEACH = register("trees_beach", ModConfiguredFeatures.COCONUT, List.of(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> SEASHELL = register("seashell", ModConfiguredFeatures.SEASHELL, VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final Holder<PlacedFeature> THIN_ICE_PATCH = register("thin_ice_patch", ModConfiguredFeatures.THIN_ICE_PATCH, List.of(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> PRICKLY_PEAR = register("prickly_pear", ModConfiguredFeatures.PRICKLY_PEAR, List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> DESERT_RUIN = register("desert_ruin", ModConfiguredFeatures.DESERT_RUIN, List.of(RarityFilter.onAverageOnceEvery(13), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final Holder<PlacedFeature> TREES_WALNUT = register("trees_walnut", ModConfiguredFeatures.WALNUT, List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> ROOTED_AZALEA_TREE = register("rooted_azalea_tree", ModConfiguredFeatures.ROOTED_AZALEA_TREE, List.of(CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final Holder<PlacedFeature> SURFACE_MOSS_PATCH = register("surface_moss_patch", ModConfiguredFeatures.SURFACE_MOSS_PATCH, List.of(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    private static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        return PlacementUtils.register(new ResourceLocation(Ecologics.MOD_ID, name).toString(), feature, modifiers);
    }
}
