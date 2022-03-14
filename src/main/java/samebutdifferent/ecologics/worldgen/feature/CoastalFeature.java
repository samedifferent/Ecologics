package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CoastalFeature extends Feature<SimpleBlockFeatureConfig> {
    public CoastalFeature(Codec<SimpleBlockFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleBlockFeatureConfig> pContext) {
        SimpleBlockFeatureConfig config = pContext.getConfig();
        StructureWorldAccess level = pContext.getWorld();
        BlockPos pos = pContext.getOrigin();
        BlockState state = config.toPlace().getBlockState(pContext.getRandom(), pos);
        if (level.getBlockState(pos.down()).isOf(Blocks.SAND)) {
            if (this.isWaterNearby(pos, level)) {
                level.setBlockState(pos, state, 2);
                return true;
            }
        }
        return false;
    }

    protected boolean isWaterNearby(BlockPos pos, StructureWorldAccess level) {
        int horizontalSearchRange = 4;
        int verticalSearchRange = 2;
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();

        for(int i = 0; i <= verticalSearchRange; i = i > 0 ? -i : 1 - i) {
            for(int i1 = 0; i1 < horizontalSearchRange; ++i1) {
                for(int i2 = 0; i2 <= i1; i2 = i2 > 0 ? -i2 : 1 - i2) {
                    for(int i3 = i2 < i1 && i2 > -i1 ? i1 : 0; i3 <= i1; i3 = i3 > 0 ? -i3 : 1 - i3) {
                        mutableBlockPos.set(pos, i2, i - 1, i3);
                        if (level.getFluidState(mutableBlockPos).isIn(FluidTags.WATER)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
