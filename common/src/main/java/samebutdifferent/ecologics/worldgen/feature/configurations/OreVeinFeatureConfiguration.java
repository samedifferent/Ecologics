package samebutdifferent.ecologics.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record OreVeinFeatureConfiguration(BlockStateProvider stateProvider, RuleTest target, VerticalAnchor minY, VerticalAnchor maxY) implements FeatureConfiguration {
    public static final Codec<OreVeinFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.stateProvider),
            RuleTest.CODEC.fieldOf("target").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.target),
            VerticalAnchor.CODEC.fieldOf("minY").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.minY),
            VerticalAnchor.CODEC.fieldOf("maxY").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.maxY)
    ).apply(instance, OreVeinFeatureConfiguration::new));
}
