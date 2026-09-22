package samebutdifferent.ecologics.worldgen.feature.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModFoliagePlacerTypes;

public class CoconutFoliagePlacer extends FoliagePlacer 
{
    public static final MapCodec<CoconutFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) -> foliagePlacerParts(placer).apply(placer, CoconutFoliagePlacer::new));

    public CoconutFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.COCONUT_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeFeature config, int maxHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos startingPos = attachment.pos();

        tryPlaceLeaf(level, setter, random, config, startingPos);

        createQuadrant(Direction.NORTH, startingPos, level, setter, random, config);
        createQuadrant(Direction.EAST, startingPos, level, setter, random, config);
        createQuadrant(Direction.SOUTH, startingPos, level, setter, random, config);
        createQuadrant(Direction.WEST, startingPos, level, setter, random, config);
    }

    @Override
    public int foliageHeight(RandomSource random, int pHeight, TreeFeature tree) {
        return 0;
    }

    private static void createQuadrant(Direction direction, BlockPos startingPos, WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeFeature tree) {
        BlockPos.MutableBlockPos pos = startingPos.mutable();
        
        pos.move(direction);
        tryPlaceLeaf(level, setter, random, tree, pos);

        if (random.nextInt(2) == 0) {
            if (level.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir)) {
                setter.set(pos.below(), ModBlocks.HANGING_COCONUT.defaultBlockState());
            }
        }
        if (random.nextInt(2) == 0) {
            if (level.isStateAtPosition(pos.below().relative(direction.getCounterClockWise()), BlockBehaviour.BlockStateBase::isAir)) {
                setter.set(pos.below().relative(direction.getCounterClockWise()), ModBlocks.HANGING_COCONUT.defaultBlockState());
            }
        }

        for (int i = 0; i < 2; i++) {
            pos.move(direction);
            tryPlaceLeaf(level, setter, random, tree, pos);
            pos.move(Direction.DOWN);
            tryPlaceLeaf(level, setter, random, tree, pos);
        }

        pos.set(startingPos);
        pos.move(direction).move(direction.getCounterClockWise());
        tryPlaceLeaf(level, setter, random, tree, pos);
        pos.move(Direction.DOWN).move(direction.getCounterClockWise());
        tryPlaceLeaf(level, setter, random, tree, pos);
        pos.move(direction);
        tryPlaceLeaf(level, setter, random, tree, pos.relative(direction.getClockWise()));
        for (int i = 0; i < 3; i++) {
            tryPlaceLeaf(level, setter, random, tree, pos);
            pos.move(Direction.DOWN);
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return (localX + localZ) > 9;
    }
}
