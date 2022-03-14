package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DiskFeature;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ThinIceFeature extends DiskFeature {
    public ThinIceFeature(Codec<DiskFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DiskFeatureConfig> context) {
        StructureWorldAccess level = context.getWorld();

        BlockPos blockpos;
        for(blockpos = context.getOrigin(); level.isAir(blockpos) && blockpos.getY() > level.getBottomY() + 2; blockpos = blockpos.down()) {
        }

        return level.getBlockState(blockpos).isOf(Blocks.ICE) && super.generate(new FeatureContext<>(context.getFeature(), level, context.getGenerator(), context.getRandom(), blockpos, context.getConfig()));
    }
}
