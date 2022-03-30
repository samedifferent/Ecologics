package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import samebutdifferent.ecologics.block.SurfaceMossBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;

public class MossPatchFeature extends Feature<MossPatchFeatureConfiguration> {
    public MossPatchFeature(Codec<MossPatchFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MossPatchFeatureConfiguration> pContext) {
        WorldGenLevel level = pContext.level();
        BlockPos pos = pContext.origin();
        if (level.isEmptyBlock(pos)) {
            Direction[] directions = Direction.values();

            for (Direction direction : directions) {
                if (direction != Direction.DOWN && VineBlock.isAcceptableNeighbour(level, pos.relative(direction), direction)) {
                    level.setBlock(pos, ModBlocks.SURFACE_MOSS.get().defaultBlockState().setValue(SurfaceMossBlock.LAYERS, level.getRandom().nextInt(3) + 1).setValue(SurfaceMossBlock.FACING, direction.getOpposite()), 2);
                    return true;
                }
            }

        }
        return false;
    }
}
