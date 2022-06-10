package samebutdifferent.ecologics.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;
import org.jetbrains.annotations.NotNull;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModPlacementModifierTypes;

import java.util.Random;

public class ThinIcePlacement extends RepeatingPlacement {
    private static final ThinIcePlacement INSTANCE = new ThinIcePlacement();
    public static final Codec<ThinIcePlacement> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected int count(@NotNull RandomSource randomSource, @NotNull BlockPos blockPos) {
        return ConstantInt.of(Ecologics.CONFIG.THIN_ICE_PATCH_COUNT).sample(randomSource);
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifierTypes.THIN_ICE;
    }
}
