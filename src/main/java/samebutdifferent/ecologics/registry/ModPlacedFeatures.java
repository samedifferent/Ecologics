package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;

import java.util.List;

public class ModPlacedFeatures {
    public static final PlacedFeature TREES_BEACH = new PlacedFeature(Holder.direct(ModConfiguredFeatures.COCONUT), List.of(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
    public static final PlacedFeature SEASHELL = new PlacedFeature(Holder.direct(ModConfiguredFeatures.SEASHELL), VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final PlacedFeature THIN_ICE_PATCH = new PlacedFeature(Holder.direct(ModConfiguredFeatures.THIN_ICE_PATCH), List.of(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PRICKLY_PEAR = new PlacedFeature(Holder.direct(ModConfiguredFeatures.PRICKLY_PEAR), List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature DESERT_RUIN = new PlacedFeature(Holder.direct(ModConfiguredFeatures.DESERT_RUIN), List.of(RarityFilter.onAverageOnceEvery(13), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature TREES_WALNUT = new PlacedFeature(Holder.direct(ModConfiguredFeatures.WALNUT), List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerPlacedFeature("trees_beach", TREES_BEACH);
        registerPlacedFeature("seashell", SEASHELL);
        registerPlacedFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerPlacedFeature("prickly_pear", PRICKLY_PEAR);
        registerPlacedFeature("desert_ruin", DESERT_RUIN);
        registerPlacedFeature("trees_walnut", TREES_WALNUT);
    }
}
