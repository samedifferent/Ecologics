package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.BaseDiskFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

public class ThinIceFeature extends BaseDiskFeature {
    public ThinIceFeature(Codec<DiskConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<DiskConfiguration> context) {
        WorldGenLevel level = context.level();

        BlockPos blockpos;
        for(blockpos = context.origin(); level.isEmptyBlock(blockpos) && blockpos.getY() > level.getMinBuildHeight() + 2; blockpos = blockpos.below()) {
        }

        return level.getBlockState(blockpos).is(Blocks.ICE) && super.place(new FeaturePlaceContext<>(context.topFeature(), level, context.chunkGenerator(), context.random(), blockpos, context.config()));
    }
}
