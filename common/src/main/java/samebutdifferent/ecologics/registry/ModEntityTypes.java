package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.*;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModEntityTypes
{
    public static void init() {
    	for (Pair<ResourceLocation, EntityType<?>> registry : ENTITY_TYPES) {
    		Registry.register(BuiltInRegistries.ENTITY_TYPE, registry.getA(), registry.getB());
    	}
    }
    
    public static <T extends EntityType> T registerEntityType(String name, T entity) {
    	ENTITY_TYPES.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), entity));
    	return entity;
    }
    
    public static final ArrayList<Pair<ResourceLocation, EntityType<?>>> ENTITY_TYPES = new ArrayList();

    // Utility
    public static final EntityType<ModBoat> BOAT = registerEntityType("boat", EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
    public static final EntityType<ModChestBoat> CHEST_BOAT = registerEntityType("chest_boat", EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));

    // Mobs
    public static final EntityType<CoconutCrab> COCONUT_CRAB = registerEntityType("coconut_crab", EntityType.Builder.<CoconutCrab>of(CoconutCrab::new, MobCategory.CREATURE).sized(1.2F, 1.2F).clientTrackingRange(10).build("coconut_crab"));
    public static final EntityType<Penguin> PENGUIN = registerEntityType("penguin", EntityType.Builder.<Penguin>of(Penguin::new, MobCategory.CREATURE).sized(0.7F, 0.9F).clientTrackingRange(10).build("penguin"));
    public static final EntityType<Squirrel> SQUIRREL = registerEntityType("squirrel", EntityType.Builder.<Squirrel>of(Squirrel::new, MobCategory.CREATURE).sized(0.9F, 0.8F).clientTrackingRange(8).build("squirrel"));
}
