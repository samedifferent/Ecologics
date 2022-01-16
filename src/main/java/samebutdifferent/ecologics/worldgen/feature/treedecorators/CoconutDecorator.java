package samebutdifferent.ecologics.worldgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class CoconutDecorator extends TreeDecorator {
    public static final Codec<CoconutDecorator> CODEC = Codec.unit(CoconutDecorator::new);

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.COCONUT_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, List<BlockPos> pLogPositions, List<BlockPos> pLeafPositions) {
        pLeafPositions.forEach((blockPos -> {
            if (pRandom.nextInt(4) == 0) {
                BlockPos blockpos = blockPos.below();
                if (Feature.isAir(pLevel, blockpos)) {
                    pBlockSetter.accept(blockPos, ModBlocks.COCONUT.get().defaultBlockState());;
                }
            }
        }));
    }
}
