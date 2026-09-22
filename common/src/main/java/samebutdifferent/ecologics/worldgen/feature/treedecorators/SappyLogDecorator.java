package samebutdifferent.ecologics.worldgen.feature.treedecorators;

import java.util.Comparator;
import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import samebutdifferent.ecologics.registry.ModTreeDecoratorTypes;

public class SappyLogDecorator extends TreeDecorator
{
	public static final MapCodec<SappyLogDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(ExtraCodecs.NON_NEGATIVE_FLOAT.optionalFieldOf("probability", 1.0F).forGetter(p -> p.probability), BlockStateProvider.CODEC.fieldOf("block_state_provider").forGetter(p -> p.blockStateProvider)).apply(i, SappyLogDecorator::new));
	private final float probability;
	private final Holder<BlockStateProvider> blockStateProvider;
	
    public SappyLogDecorator(final float probability, final Holder<BlockStateProvider> blockStateProvider) {
        this.probability = probability;
        this.blockStateProvider = blockStateProvider;
    }
	
	@Override
	protected TreeDecoratorType<?> type() {
		return ModTreeDecoratorTypes.SAPPY_LOG_DECORATOR;
	}

    @Override
    public void place(final TreeDecorator.Context context) {
        RandomSource random = context.random();
        List<BlockPos> logs = context.logs();

        if (logs.isEmpty()) return;

        int offset = random.nextFloat() < 0.5 ? 1 : 2;
        BlockPos lowestLog = logs.stream().min(Comparator.comparingInt(BlockPos::getY)).orElse(logs.get(0));
        BlockPos secondLogPos = lowestLog.above(offset);
        // We attempt to set the trunk. We roll the probability and try to place the log.
        if (random.nextFloat() < this.probability && logs.contains(secondLogPos)) {
            context.setBlock(secondLogPos, blockStateProvider.value().getOptionalState(context.level(), random, secondLogPos));
        }
    }

}
