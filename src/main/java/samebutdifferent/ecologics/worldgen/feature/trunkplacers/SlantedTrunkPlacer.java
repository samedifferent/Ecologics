package samebutdifferent.ecologics.worldgen.feature.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import samebutdifferent.ecologics.registry.ModTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class SlantedTrunkPlacer extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC = RecordCodecBuilder.create((placer) -> fillTrunkPlacerFields(placer).apply(placer, SlantedTrunkPlacer::new));

    public SlantedTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerTypes.SLANTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, int pFreeTreeHeight, BlockPos pPos, TreeFeatureConfig pConfig) {
        Direction direction = Direction.Type.HORIZONTAL.random(pRandom);
        BlockPos.Mutable mutableBlockPos = pPos.mutableCopy();
        getAndSetState(pLevel, pBlockSetter, pRandom, mutableBlockPos.offset(direction.getOpposite()), pConfig, (state) -> state.with(PillarBlock.AXIS, direction.getAxis()));
        getAndSetState(pLevel, pBlockSetter, pRandom, mutableBlockPos.offset(pRandom.nextInt(2) == 0 ? direction.rotateYClockwise() : direction.rotateYCounterclockwise()), pConfig);
        for (int i = 0; i < pFreeTreeHeight; i++) {
            if (pRandom.nextFloat() < 0.4F && i > 2) {
                mutableBlockPos.move(direction);
            }
            getAndSetState(pLevel, pBlockSetter, pRandom, mutableBlockPos, pConfig);
            mutableBlockPos.move(Direction.UP);
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(mutableBlockPos, 0, false));
    }
}
