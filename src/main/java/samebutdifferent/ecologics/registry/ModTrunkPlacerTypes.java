package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModTrunkPlacerTypes {

    public static final TrunkPlacerType<SlantedTrunkPlacer> SLANTED_TRUNK_PLACER = new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String pKey, TrunkPlacerType<P> pTrunkPlacerType) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(Ecologics.MOD_ID, pKey), pTrunkPlacerType);
    }

    public static void register() {
        register("slanted_trunk_placer", SLANTED_TRUNK_PLACER);
    }
}
