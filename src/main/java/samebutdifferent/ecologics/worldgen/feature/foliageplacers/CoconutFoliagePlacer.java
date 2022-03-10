package samebutdifferent.ecologics.worldgen.feature.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModFoliagePlacerTypes;

import java.util.Random;
import java.util.function.BiConsumer;

public class CoconutFoliagePlacer extends FoliagePlacer {
    public static final Codec<CoconutFoliagePlacer> CODEC = RecordCodecBuilder.create((placer) -> foliagePlacerParts(placer).apply(placer, CoconutFoliagePlacer::new));

    public CoconutFoliagePlacer(IntProvider pRadius, IntProvider pOffset) {
        super(pRadius, pOffset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.COCONUT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos startingPos = pAttachment.pos();

        tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, startingPos);

        createQuadrant(Direction.NORTH, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.EAST, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.SOUTH, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
        createQuadrant(Direction.WEST, startingPos, pLevel, pBlockSetter, pRandom, pConfig);
    }

    @Override
    public int foliageHeight(Random pRandom, int pHeight, TreeConfiguration pConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }

    private static void createQuadrant(Direction direction, BlockPos startingPos, LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig) {
        BlockPos.MutableBlockPos pos = startingPos.mutable();
        
        pos.move(direction);
        tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);

        if (pRandom.nextInt(2) == 0) {
            pBlockSetter.accept(pos.below(), ModBlocks.HANGING_COCONUT.get().defaultBlockState());
        }
        if (pRandom.nextInt(2) == 0) {
            pBlockSetter.accept(pos.below().relative(direction.getCounterClockWise()), ModBlocks.HANGING_COCONUT.get().defaultBlockState());
        }

        for (int i = 0; i < 2; i++) {
            pos.move(direction);
            tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
            pos.move(Direction.DOWN);
            tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
        }

        pos.set(startingPos);
        pos.move(direction).move(direction.getCounterClockWise());
        tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
        pos.move(Direction.DOWN).move(direction.getCounterClockWise());
        tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
        pos.move(direction);
        tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos.relative(direction.getClockWise()));
        for (int i = 0; i < 3; i++) {
            tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
            pos.move(Direction.DOWN);
        }
    }
}
