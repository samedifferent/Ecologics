package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import samebutdifferent.ecologics.util.FastNoiseLite;
import samebutdifferent.ecologics.worldgen.feature.configurations.OreVeinFeatureConfiguration;

// Credit to Corgi Taco for helping with this
public class OreVeinFeature extends Feature<OreVeinFeatureConfiguration> {
    public OreVeinFeature(Codec<OreVeinFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OreVeinFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        FastNoiseLite veinShapeNoise = new FastNoiseLite((int) level.getSeed());
        veinShapeNoise.SetFrequency(0.05F);
        veinShapeNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);

        FastNoiseLite inVeinShapeBlockRoughnessNoise = new FastNoiseLite((int) level.getSeed() + 904908054);
        inVeinShapeBlockRoughnessNoise.SetFrequency(0.5F); // Insanely high freq to cover a lot of space.
        inVeinShapeBlockRoughnessNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);

        FastNoiseLite cellLocationNoise = new FastNoiseLite((int) level.getSeed() + 904908054);
        cellLocationNoise.SetFrequency(0.005F); // Insanely high freq to cover a lot of space.
        cellLocationNoise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);

        BlockPos origin = context.origin();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(origin);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int minY = 10;
                int maxY = 117;
                for (int y = minY; y < maxY; y++) {
                    mutable.set(origin).move(x, y, z);

                    // Determines if this cell in the world will have a vein.
                    float cellLocationNoiseSample = cellLocationNoise.GetNoise(mutable.getX(), mutable.getY(), mutable.getZ());

                    // Making this number lower means the vein areas are much larger.
                    if (cellLocationNoiseSample < -0.37) {
                        continue;
                    }

                    float veinShapeNoiseSample = veinShapeNoise.GetNoise(mutable.getX(), mutable.getY(), mutable.getZ());

                    // Determines the vein shape / tube look.
                    if (veinShapeNoiseSample > 0.35) {
                        if (level.getBlockState(mutable).is(Blocks.NETHERRACK)) {
                            level.setBlock(mutable, context.config().stateProvider().getState(context.random(), mutable), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
