package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;

public class ModEntityTypes
{
    public static void init() {
    	for (Pair<Identifier, EntityType<?>> registry : ENTITY_TYPES) {
    		Registry.register(BuiltInRegistries.ENTITY_TYPE, registry.getA(), registry.getB());
    	}
    }
    
    public static <T extends EntityType<?>> T registerEntityType(String name, T entity) {
    	ENTITY_TYPES.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), entity));
    	return entity;
    }
    
    public static final ArrayList<Pair<Identifier, EntityType<?>>> ENTITY_TYPES = new ArrayList<>();

    // Utility
    public static final EntityType<Boat> AZALEA_BOAT = registerEntityType("azalea_boat", EntityType.Builder.of(boatFactory(() -> ModItems.AZALEA_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "azalea_boat"))));
    public static final EntityType<ChestBoat> AZALEA_CHEST_BOAT = registerEntityType("azalea_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItems.AZALEA_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "azalea_chest_boat"))));
    public static final EntityType<Boat> FLOWERING_AZALEA_BOAT = registerEntityType("flowering_azalea_boat", EntityType.Builder.of(boatFactory(() -> ModItems.FLOWERING_AZALEA_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "flowering_azalea_boat"))));
    public static final EntityType<ChestBoat> FLOWERING_AZALEA_CHEST_BOAT = registerEntityType("flowering_azalea_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItems.FLOWERING_AZALEA_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "flowering_azalea_chest_boat"))));
    public static final EntityType<Boat> COCONUT_BOAT = registerEntityType("coconut_boat", EntityType.Builder.of(boatFactory(() -> ModItems.COCONUT_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut_boat"))));
    public static final EntityType<ChestBoat> COCONUT_CHEST_BOAT = registerEntityType("coconut_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItems.COCONUT_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut_chest_boat"))));
    public static final EntityType<Boat> WALNUT_BOAT = registerEntityType("walnut_boat", EntityType.Builder.of(boatFactory(() -> ModItems.WALNUT_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "walnut_boat"))));
    public static final EntityType<ChestBoat> WALNUT_CHEST_BOAT = registerEntityType("walnut_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItems.WALNUT_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "walnut_chest_boat"))));

    // Mobs
    public static final EntityType<CoconutCrab> COCONUT_CRAB = registerEntityType("coconut_crab", EntityType.Builder.<CoconutCrab>of(CoconutCrab::new, MobCategory.CREATURE).sized(1.2F, 1.2F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "coconut_crab"))));
    public static final EntityType<Penguin> PENGUIN = registerEntityType("penguin", EntityType.Builder.<Penguin>of(Penguin::new, MobCategory.CREATURE).sized(0.7F, 0.9F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "penguin"))));
    public static final EntityType<Squirrel> SQUIRREL = registerEntityType("squirrel", EntityType.Builder.<Squirrel>of(Squirrel::new, MobCategory.CREATURE).sized(0.9F, 0.8F).clientTrackingRange(8).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "squirrel"))));
    
    private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItemGetter) {
        return (etype, level) -> new Boat(etype, level, boatItemGetter);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> boatItemGetter) {
        return (etype, level) -> new ChestBoat(etype, level, boatItemGetter);
    }
}
