package samebutdifferent.ecologics.mixin;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.AzaleaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

@Mixin(AzaleaTreeGrower.class)
public class MixinAzaleaTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        if (Ecologics.CONFIG.REPLACE_AZALEA_TREE) {
            return ModConfiguredFeatures.AZALEA_TREE.getHolder().orElseThrow();
        } else {
            return TreeFeatures.AZALEA_TREE;
        }
    }
}
