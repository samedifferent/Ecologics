package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class CoastalFeature extends Feature<SimpleBlockConfiguration> {
    public CoastalFeature(Codec<SimpleBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        SimpleBlockConfiguration config = pContext.config();
        WorldGenLevel level = pContext.level();
        BlockPos pos = pContext.origin();
        BlockState state = config.toPlace().getState(pContext.random(), pos);
        if (level.getBlockState(pos.below()).is(Blocks.SAND)) {
            if (this.isWaterNearby(pos, level)) {
                level.setBlock(pos, state, 2);
                return true;
            }
        }
        return false;
    }

    protected boolean isWaterNearby(BlockPos pos, WorldGenLevel level) {
        int horizontalSearchRange = 4;
        int verticalSearchRange = 2;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(int i = 0; i <= verticalSearchRange; i = i > 0 ? -i : 1 - i) {
            for(int i1 = 0; i1 < horizontalSearchRange; ++i1) {
                for(int i2 = 0; i2 <= i1; i2 = i2 > 0 ? -i2 : 1 - i2) {
                    for(int i3 = i2 < i1 && i2 > -i1 ? i1 : 0; i3 <= i1; i3 = i3 > 0 ? -i3 : 1 - i3) {
                        mutableBlockPos.setWithOffset(pos, i2, i - 1, i3);
                        if (level.getFluidState(mutableBlockPos).is(FluidTags.WATER)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
