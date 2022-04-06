package samebutdifferent.ecologics.mixin;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.AzaleaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.registry.ModConfiguration;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

@Mixin(AzaleaTreeGrower.class)
public class MixinAzaleaTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
        if (ModConfiguration.REPLACE_AZALEA_TREE.get()) {
            return ModConfiguredFeatures.AZALEA_TREE;
        } else {
            return TreeFeatures.AZALEA_TREE;
        }
    }
}
