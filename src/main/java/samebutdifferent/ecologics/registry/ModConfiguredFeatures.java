package samebutdifferent.ecologics.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> COCONUT = Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()), new CoconutFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final ConfiguredFeature<SimpleBlockConfiguration, ?> SEASHELL =  ModFeatures.COASTAL.get().configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.EAST), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.WEST), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.NORTH), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.SOUTH), 1))));
    public static final ConfiguredFeature<DiskConfiguration, ?> THIN_ICE_PATCH = ModFeatures.THIN_ICE.get().configured(new DiskConfiguration(ModBlocks.THIN_ICE.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(Blocks.ICE.defaultBlockState())));
    public static final ConfiguredFeature<?, ?> PRICKLY_PEAR = Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 0), 2).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 1), 2).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 2), 1).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 3), 1).build())));

    public static final PlacedFeature TREES_BEACH = COCONUT.placed(PlacementUtils.countExtra(0, 0.5F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    public static final PlacedFeature SEASHELLS = SEASHELL.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final PlacedFeature THIN_ICE_PATCHES = THIN_ICE_PATCH.placed(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final PlacedFeature PRICKLY_PEARS = PRICKLY_PEAR.placed(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

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
        registerConfiguredFeature("prickly_pear", PRICKLY_PEAR);
        registerPlacedFeature("prickly_pears", PRICKLY_PEARS);
    }
}
