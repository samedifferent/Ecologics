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
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
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
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> COCONUT = register("coconut", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.COCONUT_LOG), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.of(ModBlocks.COCONUT_LEAVES), new CoconutFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> SEASHELL = register("seashell", ModFeatures.COASTAL, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.EAST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.WEST), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.NORTH), 1).add(ModBlocks.SEASHELL.getDefaultState().with(SeashellBlock.FACING, Direction.SOUTH), 1))));
    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> THIN_ICE_PATCH = register("thin_ice_patch", ModFeatures.THIN_ICE, new DiskFeatureConfig(ModBlocks.THIN_ICE.getDefaultState(), UniformIntProvider.create(2, 3), 1, List.of(Blocks.ICE.getDefaultState())));
    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> PRICKLY_PEAR = register("prickly_pear", Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 0), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 1), 2).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 2), 1).add(ModBlocks.PRICKLY_PEAR.getDefaultState().with(PricklyPearBlock.AGE, 3), 1).build())));
    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> DESERT_RUIN = register("desert_ruin", ModFeatures.DESERT_RUIN, FeatureConfig.DEFAULT);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WALNUT = register("walnut", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WALNUT_LOG), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.of(ModBlocks.WALNUT_LEAVES), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 200), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> AZALEA_TREE = register("azalea_tree", Feature.TREE, new TreeFeatureConfig.Builder(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.AZALEA_LOG.getDefaultState(), 2).add(ModBlocks.FLOWERING_AZALEA_LOG.getDefaultState(), 1).build()), new BendingTrunkPlacer(4, 2, 0, 3, UniformIntProvider.create(1, 2)), new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.AZALEA_LEAVES.getDefaultState(), 3).add(Blocks.FLOWERING_AZALEA_LEAVES.getDefaultState(), 1).build()), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 50), new TwoLayersFeatureSize(1, 0, 1)).dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT.getDefaultState())).forceDirt().build());
    public static final RegistryEntry<ConfiguredFeature<RootSystemFeatureConfig, ?>> ROOTED_AZALEA_TREE = register("rooted_azalea_tree", Feature.ROOT_SYSTEM, new RootSystemFeatureConfig(PlacedFeatures.createEntry(TreeConfiguredFeatures.AZALEA_TREE), 3, 3, BlockTags.AZALEA_ROOT_REPLACEABLE, BlockStateProvider.of(Blocks.ROOTED_DIRT), 20, 100, 3, 2, BlockStateProvider.of(Blocks.HANGING_ROOTS), 20, 2, BlockPredicate.bothOf(BlockPredicate.anyOf(BlockPredicate.matchingBlocks(List.of(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.WATER)), BlockPredicate.matchingBlockTag(BlockTags.LEAVES), BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE_PLANTS)), BlockPredicate.matchingBlockTag(BlockTags.AZALEA_GROWS_ON, Direction.DOWN.getVector()))));
    public static final RegistryEntry<ConfiguredFeature<MossPatchFeatureConfiguration, ?>> SURFACE_MOSS_PATCH = register("surface_moss_patch", ModFeatures.SURFACE_MOSS_PATCH, new MossPatchFeatureConfiguration(96, 7, 3, true,true,true, List.of(Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE, Blocks.TUFF, Blocks.DEEPSLATE)));

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.method_40360(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Ecologics.MOD_ID, id).toString(), new ConfiguredFeature<>(feature, config));
    }
}
