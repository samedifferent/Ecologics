package samebutdifferent.ecologics.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
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
    public static final ConfiguredFeature<NoneFeatureConfiguration, ?> DESERT_RUIN = ModFeatures.DESERT_RUIN.get().configured(FeatureConfiguration.NONE);

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerConfiguredFeature(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static void register() {
        registerConfiguredFeature("coconut", COCONUT);
        registerConfiguredFeature("seashell", SEASHELL);
        registerConfiguredFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerConfiguredFeature("prickly_pear", PRICKLY_PEAR);
        registerConfiguredFeature("desert_ruin", DESERT_RUIN);
    }
}
