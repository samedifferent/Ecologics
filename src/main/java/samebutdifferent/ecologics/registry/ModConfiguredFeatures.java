package samebutdifferent.ecologics.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> COCONUT = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.COCONUT_LOG), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.of(ModBlocks.COCONUT_LEAVES), new CoconutFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final ConfiguredFeature<SimpleBlockFeatureConfig, ?> SEASHELL =  new ConfiguredFeature<>(ModFeatures.COASTAL, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.EAST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.WEST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.NORTH), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.SOUTH), 1))));
    public static final ConfiguredFeature<DiskFeatureConfig, ?> THIN_ICE_PATCH = new ConfiguredFeature<>(ModFeatures.THIN_ICE, new DiskFeatureConfig(ModBlocks.THIN_ICE.getDefaultState(), UniformIntProvider.create(2, 3), 1, List.of(Blocks.ICE.getDefaultState())));
    public static final ConfiguredFeature<?, ?> PRICKLY_PEAR = new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 0), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 1), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 2), 1).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 3), 1).build())));
    public static final ConfiguredFeature<DefaultFeatureConfig, ?> DESERT_RUIN = new ConfiguredFeature<>(ModFeatures.DESERT_RUIN, FeatureConfig.DEFAULT);
    public static final ConfiguredFeature<TreeFeatureConfig, ?> WALNUT = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WALNUT_LOG), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.of(ModBlocks.WALNUT_LEAVES), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 200), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static void register() {
        registerConfiguredFeature("coconut", COCONUT);
        registerConfiguredFeature("seashell", SEASHELL);
        registerConfiguredFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerConfiguredFeature("prickly_pear", PRICKLY_PEAR);
        registerConfiguredFeature("desert_ruin", DESERT_RUIN);
        registerConfiguredFeature("walnut", WALNUT);
    }
}
