package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
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
import samebutdifferent.ecologics.worldgen.feature.placement.CoconutTreePlacement;
import samebutdifferent.ecologics.worldgen.feature.placement.ThinIcePlacement;

import java.util.List;
import java.util.function.Supplier;

public class ModPlacedFeatures {
    public static final Supplier<PlacedFeature> TREES_BEACH = registerPlacedFeature("trees_beach", () -> new PlacedFeature(ModConfiguredFeatures.COCONUT.getHolder().orElseThrow(), List.of(new CoconutTreePlacement(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> SEASHELL = registerPlacedFeature("seashell", () -> new PlacedFeature(ModConfiguredFeatures.SEASHELL.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(4)));
    public static final Supplier<PlacedFeature> THIN_ICE_PATCH = registerPlacedFeature("thin_ice_patch", () -> new PlacedFeature(ModConfiguredFeatures.THIN_ICE_PATCH.getHolder().orElseThrow(), List.of(new ThinIcePlacement(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> PRICKLY_PEAR = registerPlacedFeature("prickly_pear", () -> new PlacedFeature(ModConfiguredFeatures.PRICKLY_PEAR.getHolder().orElseThrow(), List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> DESERT_RUIN = registerPlacedFeature("desert_ruin", () -> new PlacedFeature(ModConfiguredFeatures.DESERT_RUIN.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(13), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> TREES_WALNUT = registerPlacedFeature("trees_walnut", () -> new PlacedFeature(ModConfiguredFeatures.WALNUT.get().orElseThrow(), List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> ROOTED_AZALEA_TREE = registerPlacedFeature("rooted_azalea_tree", () -> new PlacedFeature(ModConfiguredFeatures.ROOTED_AZALEA_TREE.getHolder().orElseThrow(), List.of(CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome())));
    public static final Supplier<PlacedFeature> SURFACE_MOSS_PATCH = registerPlacedFeature("surface_moss_patch", () -> new PlacedFeature(ModConfiguredFeatures.SURFACE_MOSS_PATCH.getHolder().orElseThrow(), List.of(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome())));

    private static Holder<PlacedFeature> registerPlacedFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        return PlacementUtils.register(new ResourceLocation(Ecologics.MOD_ID, name).toString(), feature, modifiers);
    }
}
