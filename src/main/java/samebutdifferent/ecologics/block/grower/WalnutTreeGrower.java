package samebutdifferent.ecologics.block.grower;

import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WalnutTreeGrower extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.WALNUT;
    }
}
