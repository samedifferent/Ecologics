package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

import java.util.function.Supplier;

public class ModTrunkPlacerTypes {
    public static final Supplier<TrunkPlacerType<SlantedTrunkPlacer>> SLANTED_TRUNK_PLACER = () -> Registry.register(Registry.TRUNK_PLACER_TYPE_REGISTRY, new ResourceLocation(Ecologics.MOD_ID, "slanted_trunk_placer", new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC)));

}
