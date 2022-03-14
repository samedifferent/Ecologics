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
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> COCONUT = Feature.TREE.configure(new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.COCONUT_LOG), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.of(ModBlocks.COCONUT_LEAVES), new CoconutFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final ConfiguredFeature<SimpleBlockFeatureConfig, ?> SEASHELL =  ModFeatures.COASTAL.configure(new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.EAST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.WEST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.NORTH), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.SOUTH), 1))));
    public static final ConfiguredFeature<DiskFeatureConfig, ?> THIN_ICE_PATCH = ModFeatures.THIN_ICE.configure(new DiskFeatureConfig(ModBlocks.THIN_ICE.getDefaultState(), UniformIntProvider.create(2, 3), 1, List.of(Blocks.ICE.getDefaultState())));
    public static final ConfiguredFeature<?, ?> PRICKLY_PEAR = Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 0), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 1), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 2), 1).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 3), 1).build())));
    public static final ConfiguredFeature<DefaultFeatureConfig, ?> DESERT_RUIN = ModFeatures.DESERT_RUIN.configure(FeatureConfig.DEFAULT);

    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static void register() {
        registerConfiguredFeature("coconut", COCONUT);
        registerConfiguredFeature("seashell", SEASHELL);
        registerConfiguredFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerConfiguredFeature("prickly_pear", PRICKLY_PEAR);
        registerConfiguredFeature("desert_ruin", DESERT_RUIN);
    }
}
