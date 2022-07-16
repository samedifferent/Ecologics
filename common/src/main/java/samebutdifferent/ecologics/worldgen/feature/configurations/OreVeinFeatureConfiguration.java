package samebutdifferent.ecologics.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record OreVeinFeatureConfiguration(BlockStateProvider stateProvider) implements FeatureConfiguration {
    public static final Codec<OreVeinFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((OreVeinFeatureConfiguration) -> OreVeinFeatureConfiguration.stateProvider)
    ).apply(instance, OreVeinFeatureConfiguration::new));
}
