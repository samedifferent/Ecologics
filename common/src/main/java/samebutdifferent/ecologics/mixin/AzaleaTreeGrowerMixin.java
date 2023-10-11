package samebutdifferent.ecologics.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.AzaleaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import samebutdifferent.ecologics.block.grower.ModAzaleaTreeGrower;
import samebutdifferent.ecologics.platform.ConfigPlatformHelper;

@Mixin(AzaleaTreeGrower.class)
public class AzaleaTreeGrowerMixin extends AbstractTreeGrower {

    @Nullable
    @Override
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        if (ConfigPlatformHelper.replaceAzaleaTree()) {
            return null;
        } else {
            return TreeFeatures.AZALEA_TREE;
        }
    }

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
        if (ConfigPlatformHelper.replaceAzaleaTree()) {
            ModAzaleaTreeGrower grower = new ModAzaleaTreeGrower();
            return grower.growTree(level, chunkGenerator, pos, state, random);
        } else {
            return super.growTree(level, chunkGenerator, pos, state, random);
        }
    }
}
