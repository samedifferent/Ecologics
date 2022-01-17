package samebutdifferent.ecologics.block.grower;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModVegetationFeatures;

import java.util.Random;

public class CoconutTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModVegetationFeatures.COCONUT;
    }
}
