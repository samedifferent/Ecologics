package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;

public class ModPlacedFeatures {
    public static final PlacedFeature TREES_BEACH = ModConfiguredFeatures.COCONUT.placed(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    public static final PlacedFeature SEASHELL = ModConfiguredFeatures.SEASHELL.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final PlacedFeature THIN_ICE_PATCH = ModConfiguredFeatures.THIN_ICE_PATCH.placed(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final PlacedFeature PRICKLY_PEAR = ModConfiguredFeatures.PRICKLY_PEAR.placed(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerPlacedFeature("trees_beach", TREES_BEACH);
        registerPlacedFeature("seashell", SEASHELL);
        registerPlacedFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerPlacedFeature("prickly_pear", PRICKLY_PEAR);
    }
}
