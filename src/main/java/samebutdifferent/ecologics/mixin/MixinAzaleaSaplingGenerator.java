package samebutdifferent.ecologics.mixin;

import net.minecraft.block.sapling.AzaleaSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;

import java.util.Random;

@Mixin(AzaleaSaplingGenerator.class)
public class MixinAzaleaSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    public RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return RegistryEntry.of(ModConfiguredFeatures.AZALEA_TREE);
    }
}
