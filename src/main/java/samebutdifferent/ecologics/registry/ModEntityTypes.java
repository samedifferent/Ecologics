package samebutdifferent.ecologics.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.*;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final EntityType<CoconutCrab> COCONUT_CRAB = registerEntityType("coconut_crab", () -> EntityType.Builder.create(CoconutCrab::new, SpawnGroup.CREATURE).setDimensions(1.0F, 1.0F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "coconut_crab").toString()));
    public static final EntityType<Camel> CAMEL = registerEntityType("camel", () -> EntityType.Builder.create(Camel::new, SpawnGroup.CREATURE).setDimensions(1.6F, 2.1F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "camel").toString()));
    public static final EntityType<ModBoat> BOAT = registerEntityType("boat", () -> EntityType.Builder.<ModBoat>create(ModBoat::new, SpawnGroup.MISC).setDimensions(1.375F, 0.5625F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "boat").toString()));
    public static final EntityType<Penguin> PENGUIN = registerEntityType("penguin", () -> EntityType.Builder.create(Penguin::new, SpawnGroup.CREATURE).setDimensions(1.0F, 1.0F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "penguin").toString()));
    public static final EntityType<Squirrel> SQUIRREL = registerEntityType("squirrel", () -> EntityType.Builder.create(Squirrel::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.8F).maxTrackingRange(8).build(new Identifier(Ecologics.MOD_ID, "squirrel").toString()));

    private static <T extends EntityType<?>> T registerEntityType(String name, Supplier<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, name), entityType.get());
    }
}
