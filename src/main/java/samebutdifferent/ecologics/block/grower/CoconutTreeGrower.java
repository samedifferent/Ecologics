package samebutdifferent.ecologics.block.grower;

import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CoconutTreeGrower extends SaplingGenerator {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getTreeFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.COCONUT;
    }
}
