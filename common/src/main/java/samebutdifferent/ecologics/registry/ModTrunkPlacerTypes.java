package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.trunkplacers.SlantedTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, Ecologics.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<SlantedTrunkPlacer>> SLANTED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("slanted_trunk_placer", () -> new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC));

}
