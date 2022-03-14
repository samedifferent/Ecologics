package samebutdifferent.ecologics.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.BlockFilterPlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.decorator.SurfaceWaterDepthFilterPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import samebutdifferent.ecologics.Ecologics;

public class ModPlacedFeatures {
    public static final PlacedFeature TREES_BEACH = ModConfiguredFeatures.COCONUT.withPlacement(PlacedFeatures.createCountExtraModifier(0, 0.5F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_HUSK.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
    public static final PlacedFeature SEASHELL = ModConfiguredFeatures.SEASHELL.withPlacement(VegetationPlacedFeatures.modifiers(4));
    public static final PlacedFeature THIN_ICE_PATCH = ModConfiguredFeatures.THIN_ICE_PATCH.withPlacement(CountPlacementModifier.of(15), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final PlacedFeature PRICKLY_PEAR = ModConfiguredFeatures.PRICKLY_PEAR.withPlacement(CountPlacementModifier.of(128), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final PlacedFeature DESERT_RUIN = ModConfiguredFeatures.DESERT_RUIN.withPlacement(RarityFilterPlacementModifier.of(13), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static PlacedFeature registerPlacedFeature(String pKey, PlacedFeature pPlacedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Ecologics.MOD_ID, pKey), pPlacedFeature);
    }

    public static void register() {
        registerPlacedFeature("trees_beach", TREES_BEACH);
        registerPlacedFeature("seashell", SEASHELL);
        registerPlacedFeature("thin_ice_patch", THIN_ICE_PATCH);
        registerPlacedFeature("prickly_pear", PRICKLY_PEAR);
        registerPlacedFeature("desert_ruin", DESERT_RUIN);
    }
}
