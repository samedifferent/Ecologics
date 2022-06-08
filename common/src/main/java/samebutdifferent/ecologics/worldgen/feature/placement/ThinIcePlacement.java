package samebutdifferent.ecologics.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;
import samebutdifferent.ecologics.registry.ModConfiguration;
import samebutdifferent.ecologics.registry.ModPlacementModifierTypes;

import java.util.Random;

public class ThinIcePlacement extends RepeatingPlacement {
    private static final ThinIcePlacement INSTANCE = new ThinIcePlacement();
    public static final Codec<ThinIcePlacement> CODEC = Codec.unit(() -> INSTANCE);

    protected int count(Random random, BlockPos pos) {
        return ConstantInt.of(ModConfiguration.THIN_ICE_PATCH_COUNT.get()).sample(random);
    }

    public PlacementModifierType<?> type() {
        return ModPlacementModifierTypes.THIN_ICE.get();
    }
}
