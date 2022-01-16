package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.foliageplacers.CoconutFoliagePlacer;

public class ModFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Ecologics.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<CoconutFoliagePlacer>> COCONUT_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("coconut_foliage_placer", () -> new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC));
}
