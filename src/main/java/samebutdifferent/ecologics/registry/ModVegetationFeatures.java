package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModVegetationFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> COCONUT = Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()), new CoconutFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

    public static final PlacedFeature TREES_BEACH = COCONUT.placed(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerConfiguredFeature(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerConfiguredFeature("coconut", COCONUT);
        registerPlacedFeature("trees_beach", TREES_BEACH);
    }
}
