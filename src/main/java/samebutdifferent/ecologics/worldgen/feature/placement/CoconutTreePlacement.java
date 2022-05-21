package samebutdifferent.ecologics.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;
import samebutdifferent.ecologics.registry.ModConfiguration;
import samebutdifferent.ecologics.registry.ModPlacementModifierTypes;

import java.util.Random;

public class CoconutTreePlacement extends RepeatingPlacement {
    private static final CoconutTreePlacement INSTANCE = new CoconutTreePlacement();
    public static final Codec<CoconutTreePlacement> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected int count(Random random, BlockPos blockPos) {
        int countConfig = ModConfiguration.COCONUT_TREE_COUNT.get();
        float chanceConfig = ModConfiguration.COCONUT_TREE_EXTRA_COUNT_CHANCE.get().floatValue();
        int extraCountConfig = ModConfiguration.COCONUT_TREE_EXTRA_COUNT.get();
        float weight = 1.0F / chanceConfig;
        SimpleWeightedRandomList<IntProvider> list = SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(countConfig), (int)weight - 1).add(ConstantInt.of(countConfig + extraCountConfig), 1).build();
        return new WeightedListInt(list).sample(random);
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifierTypes.COCONUT_TREE.get();
    }
}
