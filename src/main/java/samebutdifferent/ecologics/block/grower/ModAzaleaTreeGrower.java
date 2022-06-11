package samebutdifferent.ecologics.block.grower;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

public class ModAzaleaTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_204307_, boolean p_204308_) {
        return ModConfiguredFeatures.AZALEA_TREE.getHolder().orElseThrow();
    }
}
