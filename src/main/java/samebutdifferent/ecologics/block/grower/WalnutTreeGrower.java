package samebutdifferent.ecologics.block.grower;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

public class WalnutTreeGrower extends SaplingGenerator {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getTreeFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.WALNUT;
    }
}
