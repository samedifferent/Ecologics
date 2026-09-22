package samebutdifferent.ecologics.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import samebutdifferent.ecologics.Ecologics;

public class ModFeatures 
{
    public static void init() {}

    public static final ResourceKey<Feature> ROOTED_AZALEA_TREE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "rooted_azalea_tree"));
    public static final ResourceKey<Feature> AZALEA = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "azalea_tree"));
    public static final ResourceKey<Feature> COCONUT = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut_tree"));
    public static final ResourceKey<Feature> WALNUT = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "walnut_tree"));
    
    public static final ResourceKey<Feature> RED_MAPLE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "red_maple_tree"));
    public static final ResourceKey<Feature> ORANGE_MAPLE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "orange_maple_tree"));
    public static final ResourceKey<Feature> YELLOW_MAPLE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "yellow_maple_tree"));
    public static final ResourceKey<Feature> GREEN_MAPLE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "green_maple_tree"));
}
