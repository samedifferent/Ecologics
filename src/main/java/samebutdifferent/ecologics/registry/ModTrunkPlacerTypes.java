package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModTrunkPlacerTypes {

    public static final TrunkPlacerType<SlantedTrunkPlacer> SLANTED_TRUNK_PLACER = new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacerType(String pKey, TrunkPlacerType<P> pTrunkPlacerType) {
        return Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(Ecologics.MOD_ID, pKey), pTrunkPlacerType);
    }

    public static void register() {
        registerTrunkPlacerType("slanted_trunk_placer", SLANTED_TRUNK_PLACER);
    }
}
