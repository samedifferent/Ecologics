package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.List;
import java.util.Random;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> COCONUT = Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()), new CoconutFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final ConfiguredFeature<SimpleBlockConfiguration, ?> SEASHELL =  ModFeatures.COASTAL.get().configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(new Random())))));
    public static final ConfiguredFeature<DiskConfiguration, ?> THIN_ICE_PATCH = ModFeatures.THIN_ICE.get().configured(new DiskConfiguration(ModBlocks.THIN_ICE.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(Blocks.ICE.defaultBlockState())));

    public static final PlacedFeature TREES_BEACH = COCONUT.placed(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    public static final PlacedFeature SEASHELLS = SEASHELL.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final PlacedFeature THIN_ICE_PATCHES = THIN_ICE_PATCH.placed(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerConfiguredFeature(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerConfiguredFeature("coconut", COCONUT);
        registerPlacedFeature("trees_beach", TREES_BEACH);
        registerConfiguredFeature("seashell", SEASHELL);
        registerPlacedFeature("seashells", SEASHELLS);
        registerConfiguredFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerPlacedFeature("thin_ice_patches", THIN_ICE_PATCHES);
    }
}
