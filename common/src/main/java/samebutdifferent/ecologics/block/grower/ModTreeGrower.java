package samebutdifferent.ecologics.block.grower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class ModTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return null;
    }

    protected abstract ResourceLocation getConfiguredFeatureLocation();

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
        Optional<Holder.Reference<ConfiguredFeature<?, ?>>> optional = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, getConfiguredFeatureLocation()));
        if (optional.isEmpty()) {
            return false;
        } else {
            ConfiguredFeature<?, ?> configuredfeature = optional.get().value();
            BlockState blockstate = level.getFluidState(pos).createLegacyBlock();
            level.setBlock(pos, blockstate, 4);
            if (configuredfeature.place(level, chunkGenerator, random, pos)) {
                if (level.getBlockState(pos) == blockstate) {
                    level.sendBlockUpdated(pos, state, blockstate, 2);
                }

                return true;
            } else {
                level.setBlock(pos, state, 4);
                return false;
            }
        }
    }
}
