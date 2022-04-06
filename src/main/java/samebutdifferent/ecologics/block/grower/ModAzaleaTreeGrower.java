package samebutdifferent.ecologics.block.grower;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

public class ModAzaleaTreeGrower extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.AZALEA_TREE;
    }
}
