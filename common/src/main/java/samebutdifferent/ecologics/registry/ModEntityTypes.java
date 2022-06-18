package samebutdifferent.ecologics.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import samebutdifferent.ecologics.entity.*;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static void init() {}

    public static final Supplier<EntityType<CoconutCrab>> COCONUT_CRAB = CommonPlatformHelper.registerEntityType("coconut_crab", CoconutCrab::new, MobCategory.CREATURE, 1.2F, 1.2F, 10);
    public static final Supplier<EntityType<Camel>> CAMEL = CommonPlatformHelper.registerEntityType("camel", Camel::new, MobCategory.CREATURE, 1.6F, 2.1F, 10);
    public static final Supplier<EntityType<ModBoat>> BOAT = CommonPlatformHelper.registerEntityType("boat", ModBoat::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<ModChestBoat>> CHEST_BOAT = CommonPlatformHelper.registerEntityType("chest_boat", ModChestBoat::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<Penguin>> PENGUIN = CommonPlatformHelper.registerEntityType("penguin", Penguin::new, MobCategory.CREATURE, 0.7F, 0.9F, 10);
    public static final Supplier<EntityType<Squirrel>> SQUIRREL = CommonPlatformHelper.registerEntityType("squirrel", Squirrel::new, MobCategory.CREATURE, 0.6F, 0.8F, 8);
}
