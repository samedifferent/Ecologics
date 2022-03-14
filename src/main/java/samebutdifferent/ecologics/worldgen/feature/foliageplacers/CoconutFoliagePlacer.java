package samebutdifferent.ecologics.worldgen.feature.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModFoliagePlacerTypes;

import java.util.Random;
import java.util.function.BiConsumer;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CoconutFoliagePlacer extends FoliagePlacer {
    public static final Codec<CoconutFoliagePlacer> CODEC = RecordCodecBuilder.create((placer) -> fillFoliagePlacerFields(placer).apply(placer, CoconutFoliagePlacer::new));

    public CoconutFoliagePlacer(IntProvider pRadius, IntProvider pOffset) {
        super(pRadius, pOffset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.COCONUT_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeFeatureConfig pConfig, int pMaxFreeTreeHeight, TreeNode pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos startingPos = pAttachment.getCenter();

        placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, startingPos);

        createQuadrant(Direction.NORTH, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.EAST, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.SOUTH, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.WEST, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
    }

    @Override
    public int getRandomHeight(Random pRandom, int pHeight, TreeFeatureConfig pConfig) {
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(Random pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }

    private static void createQuadrant(Direction direction, BlockPos startingPos, TestableWorld pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeFeatureConfig pConfig) {
        BlockPos.Mutable pos = startingPos.mutableCopy();
        
        pos.move(direction);
        placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);

        if (pRandom.nextInt(2) == 0) {
            pBlockSetter.accept(pos.down(), ModBlocks.HANGING_COCONUT.getDefaultState());
        }
        if (pRandom.nextInt(2) == 0) {
            pBlockSetter.accept(pos.down().offset(direction.rotateYCounterclockwise()), ModBlocks.HANGING_COCONUT.getDefaultState());
        }

        for (int i = 0; i < 2; i++) {
            pos.move(direction);
            placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);
            pos.move(Direction.DOWN);
            placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);
        }

        pos.set(startingPos);
        pos.move(direction).move(direction.rotateYCounterclockwise());
        placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);
        pos.move(Direction.DOWN).move(direction.rotateYCounterclockwise());
        placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);
        pos.move(direction);
        placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos.offset(direction.rotateYClockwise()));
        for (int i = 0; i < 3; i++) {
            placeFoliageBlock(pLevel, pBlockSetter, pRandom, pConfig, pos);
            pos.move(Direction.DOWN);
        }
    }
}
