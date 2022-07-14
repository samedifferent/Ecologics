package samebutdifferent.ecologics.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.GlowLichenFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GlowLichenConfiguration;
import samebutdifferent.ecologics.block.SurfaceMossBlock;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SurfaceMossFeature extends GlowLichenFeature {
    public SurfaceMossFeature(Codec<GlowLichenConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GlowLichenConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        Random random = context.random();
        GlowLichenConfiguration configuration = context.config();
        if (!isAirOrWater(worldGenLevel.getBlockState(blockPos))) {
            return false;
        } else {
            List<Direction> directions = getShuffledDirections(configuration, random);
            if (placeGlowLichenIfPossible(worldGenLevel, blockPos, worldGenLevel.getBlockState(blockPos), configuration, random, directions)) {
                return true;
            } else {
                BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

                for (Direction direction : directions) {
                    mutableBlockPos.set(blockPos);
                    List<Direction> directionsExcept = getShuffledDirectionsExcept(configuration, random, direction.getOpposite());

                    for (int i = 0; i < configuration.searchRange; ++i) {
                        mutableBlockPos.setWithOffset(blockPos, direction);
                        BlockState blockState = worldGenLevel.getBlockState(mutableBlockPos);
                        if (!isAirOrWater(blockState) && !blockState.is(ModBlocks.SURFACE_MOSS.get())) {
                            break;
                        }

                        if (placeGlowLichenIfPossible(worldGenLevel, mutableBlockPos, blockState, configuration, random, directionsExcept)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean placeGlowLichenIfPossible(WorldGenLevel level, BlockPos pos, BlockState state, GlowLichenConfiguration config, Random random, List<Direction> directions) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
        Iterator<Direction> iterator = directions.iterator();

        Direction direction;
        BlockState blockState;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            direction = iterator.next();
            blockState = level.getBlockState(mutableBlockPos.setWithOffset(pos, direction));
        } while(!blockState.is(config.canBePlacedOn));

        SurfaceMossBlock surfaceMossBlock = ModBlocks.SURFACE_MOSS.get();
        BlockState blockState2 = surfaceMossBlock.getStateForPlacement(state, level, pos, direction);
        if (blockState2 == null) {
            return false;
        } else {
            level.setBlock(pos, blockState2.setValue(SurfaceMossBlock.LAYERS, random.nextInt(3) + 1), 3);
            level.getChunk(pos).markPosForPostprocessing(pos);
            if (random.nextFloat() < config.chanceOfSpreading) {
                surfaceMossBlock.spreadFromFaceTowardRandomDirection(blockState2.setValue(SurfaceMossBlock.LAYERS, random.nextInt(3) + 1), level, pos, direction, random, true);
            }

            return true;
        }
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }
}
