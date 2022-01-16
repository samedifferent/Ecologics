package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> COCONUT = Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()), new SlantedTrunkPlacer(7, 4, 0), BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()), new CoconutFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String pKey, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, pKey), pConfiguredFeature);
    }

    public static void register() {
        register("coconut", COCONUT);
    }
}
