package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.placement.CoconutTreePlacement;
import samebutdifferent.ecologics.worldgen.feature.placement.ThinIcePlacement;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Ecologics.MOD_ID);

    public static final RegistryObject<PlacedFeature> TREES_BEACH = PLACED_FEATURES.register("trees_beach", () -> new PlacedFeature(ModConfiguredFeatures.COCONUT.getHolder().orElseThrow(), List.of(new CoconutTreePlacement(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> SEASHELL = PLACED_FEATURES.register("seashell", () -> new PlacedFeature(ModConfiguredFeatures.SEASHELL.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(4)));
    public static final RegistryObject<PlacedFeature> THIN_ICE_PATCH = PLACED_FEATURES.register("thin_ice_patch", () -> new PlacedFeature(ModConfiguredFeatures.THIN_ICE_PATCH.getHolder().orElseThrow(), List.of(new ThinIcePlacement(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PRICKLY_PEAR = PLACED_FEATURES.register("prickly_pear", () -> new PlacedFeature(ModConfiguredFeatures.PRICKLY_PEAR.getHolder().orElseThrow(), List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> DESERT_RUIN = PLACED_FEATURES.register("desert_ruin", () -> new PlacedFeature(ModConfiguredFeatures.DESERT_RUIN.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(13), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> TREES_WALNUT = PLACED_FEATURES.register("trees_walnut", () -> new PlacedFeature(ModConfiguredFeatures.WALNUT.getHolder().orElseThrow(), List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> ROOTED_AZALEA_TREE = PLACED_FEATURES.register("rooted_azalea_tree", () -> new PlacedFeature(ModConfiguredFeatures.ROOTED_AZALEA_TREE.getHolder().orElseThrow(), List.of(CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> SURFACE_MOSS_PATCH = PLACED_FEATURES.register("surface_moss_patch", () -> new PlacedFeature(ModConfiguredFeatures.SURFACE_MOSS_PATCH.getHolder().orElseThrow(), List.of(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome())));

}
