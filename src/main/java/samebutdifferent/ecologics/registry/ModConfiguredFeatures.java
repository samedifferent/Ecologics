package samebutdifferent.ecologics.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;
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
    public static final ConfiguredFeature<TreeFeatureConfig,?> AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.AZALEA_LOG.getDefaultState(), 2).add(ModBlocks.FLOWERING_AZALEA_LOG.getDefaultState(), 1).build()), new BendingTrunkPlacer(4, 2, 0, 3, UniformIntProvider.create(1, 2)), new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.AZALEA_LEAVES.getDefaultState(), 3).add(Blocks.FLOWERING_AZALEA_LEAVES.getDefaultState(), 1).build()), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 50), new TwoLayersFeatureSize(1, 0, 1)).dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT.getDefaultState())).forceDirt().build());
    public static final ConfiguredFeature<RootSystemFeatureConfig, ?> ROOTED_AZALEA_TREE = new ConfiguredFeature<>(Feature.ROOT_SYSTEM, new RootSystemFeatureConfig(PlacedFeatures.createEntry(TreeConfiguredFeatures.AZALEA_TREE), 3, 3, BlockTags.AZALEA_ROOT_REPLACEABLE, BlockStateProvider.of(Blocks.ROOTED_DIRT), 20, 100, 3, 2, BlockStateProvider.of(Blocks.HANGING_ROOTS), 20, 2, BlockPredicate.bothOf(BlockPredicate.anyOf(BlockPredicate.matchingBlocks(List.of(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.WATER)), BlockPredicate.matchingBlockTag(BlockTags.LEAVES), BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE_PLANTS)), BlockPredicate.matchingBlockTag(BlockTags.AZALEA_GROWS_ON, Direction.DOWN.getVector()))));
    public static final ConfiguredFeature<MossPatchFeatureConfiguration, ?> SURFACE_MOSS_PATCH = new ConfiguredFeature<>(ModFeatures.SURFACE_MOSS_PATCH, new MossPatchFeatureConfiguration(96, 7, 3, true,true,true, List.of(Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE, Blocks.TUFF, Blocks.DEEPSLATE)));

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
        registerConfiguredFeature("azalea_tree", AZALEA_TREE);
        registerConfiguredFeature("rooted_azalea_tree", ROOTED_AZALEA_TREE);
        registerConfiguredFeature("surface_moss_patch", SURFACE_MOSS_PATCH);
    }
}
