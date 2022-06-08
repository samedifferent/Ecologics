package samebutdifferent.ecologics.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PricklyPearBlock;
import samebutdifferent.ecologics.block.SeashellBlock;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Ecologics.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> COCONUT = CONFIGURED_FEATURES.register("coconut", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()), new CoconutFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SEASHELL = CONFIGURED_FEATURES.register("seashell", () -> new ConfiguredFeature<>(ModFeatures.COASTAL.get(), new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.EAST), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.WEST), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.NORTH), 1).add(ModBlocks.SEASHELL.get().defaultBlockState().setValue(SeashellBlock.FACING, Direction.SOUTH), 1)))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> THIN_ICE_PATCH = CONFIGURED_FEATURES.register("thin_ice_patch", () -> new ConfiguredFeature<>(ModFeatures.THIN_ICE.get(), new DiskConfiguration(ModBlocks.THIN_ICE.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(Blocks.ICE.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PRICKLY_PEAR = CONFIGURED_FEATURES.register("prickly_pear", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 0), 2).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 1), 2).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 2), 1).add(ModBlocks.PRICKLY_PEAR.get().defaultBlockState().setValue(PricklyPearBlock.AGE, 3), 1).build()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DESERT_RUIN = CONFIGURED_FEATURES.register("desert_ruin", () -> new ConfiguredFeature<>(ModFeatures.DESERT_RUIN.get(), FeatureConfiguration.NONE));
    public static final RegistryObject<ConfiguredFeature<?, ?>> WALNUT = CONFIGURED_FEATURES.register("walnut", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.WALNUT_LOG.get()), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.simple(ModBlocks.WALNUT_LEAVES.get()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(3), 200), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> AZALEA_TREE = CONFIGURED_FEATURES.register("azalea_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.AZALEA_LOG.get().defaultBlockState(), 2).add(ModBlocks.FLOWERING_AZALEA_LOG.get().defaultBlockState(), 1).build()), new BendingTrunkPlacer(4, 2, 0, 3, UniformInt.of(1, 2)), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3).add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1).build()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 0, 1)).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT.defaultBlockState())).forceDirt().build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ROOTED_AZALEA_TREE = CONFIGURED_FEATURES.register("rooted_azalea_tree", () -> new ConfiguredFeature<>(Feature.ROOT_SYSTEM, new RootSystemConfiguration(PlacementUtils.inlinePlaced(ModConfiguredFeatures.AZALEA_TREE.getHolder().orElseThrow()), 3, 3, BlockTags.AZALEA_ROOT_REPLACEABLE, BlockStateProvider.simple(Blocks.ROOTED_DIRT), 20, 100, 3, 2, BlockStateProvider.simple(Blocks.HANGING_ROOTS), 20, 2, BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(List.of(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.WATER)), BlockPredicate.matchesTag(BlockTags.LEAVES), BlockPredicate.matchesTag(BlockTags.REPLACEABLE_PLANTS)), BlockPredicate.matchesTag(BlockTags.AZALEA_GROWS_ON, Direction.DOWN.getNormal())))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SURFACE_MOSS_PATCH = CONFIGURED_FEATURES.register("surface_moss_patch", () -> new ConfiguredFeature<>(ModFeatures.SURFACE_MOSS_PATCH.get(), new MossPatchFeatureConfiguration(96, 7, 3, true, true, true, List.of(Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE, Blocks.TUFF, Blocks.DEEPSLATE))));

}
