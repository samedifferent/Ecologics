package samebutdifferent.ecologics.block.grower;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

public class CoconutTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean pLargeHive) {
        return ModConfiguredFeatures.COCONUT;
    }
}
