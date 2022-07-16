package samebutdifferent.ecologics.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record OreVeinFeatureConfiguration(BlockStateProvider stateProvider, RuleTest target, int minY, int maxY) implements FeatureConfiguration {
    public static final Codec<OreVeinFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.stateProvider),
            RuleTest.CODEC.fieldOf("target").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.target),
            Codec.INT.fieldOf("minY").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.minY),
            Codec.INT.fieldOf("maxY").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.maxY)
    ).apply(instance, OreVeinFeatureConfiguration::new));
}
