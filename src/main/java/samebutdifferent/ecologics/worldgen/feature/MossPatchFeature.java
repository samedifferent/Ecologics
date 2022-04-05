package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import samebutdifferent.ecologics.block.SurfaceMossBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.worldgen.feature.configurations.MossPatchFeatureConfiguration;

public class MossPatchFeature extends Feature<MossPatchFeatureConfiguration> {
    public MossPatchFeature(Codec<MossPatchFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<MossPatchFeatureConfiguration> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        if (world.isAir(pos)) {
            Direction[] directions = Direction.values();

            for (Direction direction : directions) {
                if (direction != Direction.DOWN && VineBlock.shouldConnectTo(world, pos.offset(direction), direction)) {
                    world.setBlockState(pos, ModBlocks.SURFACE_MOSS.getDefaultState().with(SurfaceMossBlock.LAYERS, world.getRandom().nextInt(3) + 1).with(SurfaceMossBlock.FACING, direction.getOpposite()), 2);
                    return true;
                }
            }

        }
        return false;
    }
}
