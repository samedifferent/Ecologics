package samebutdifferent.ecologics.worldgen.feature.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import samebutdifferent.ecologics.registry.ModTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class SlantedTrunkPlacer extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC = RecordCodecBuilder.create((placer) -> trunkPlacerParts(placer).apply(placer, SlantedTrunkPlacer::new));

    public SlantedTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SLANTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        BlockPos.MutableBlockPos mutableBlockPos = pPos.mutable();
        placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos.relative(direction.getOpposite()), pConfig, (state) -> state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos.relative(pRandom.nextInt(2) == 0 ? direction.getClockWise() : direction.getCounterClockWise()), pConfig);
        for (int i = 0; i < pFreeTreeHeight; i++) {
            if (pRandom.nextFloat() < 0.4F && i > 2) {
                mutableBlockPos.move(direction);
            }
            placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos, pConfig);
            mutableBlockPos.move(Direction.UP);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(mutableBlockPos, 0, false));
    }
}
