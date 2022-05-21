package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.placement.CoconutTreePlacement;
import samebutdifferent.ecologics.worldgen.feature.placement.ThinIcePlacement;

public class ModPlacementModifierTypes {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, Ecologics.MOD_ID);

    public static final RegistryObject<PlacementModifierType<ThinIcePlacement>> THIN_ICE = PLACEMENT_MODIFIER_TYPES.register("thin_ice", () -> () -> ThinIcePlacement.CODEC);
    public static final RegistryObject<PlacementModifierType<CoconutTreePlacement>> COCONUT_TREE = PLACEMENT_MODIFIER_TYPES.register("coconut_tree", () -> () -> CoconutTreePlacement.CODEC);
}
