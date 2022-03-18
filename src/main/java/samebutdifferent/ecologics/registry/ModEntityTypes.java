package samebutdifferent.ecologics.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.*;

public class ModEntityTypes {
    public static final EntityType<CoconutCrab> COCONUT_CRAB = Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "coconut_crab"), EntityType.Builder.create(CoconutCrab::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.8F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "coconut_crab").toString()));
    public static final EntityType<Camel> CAMEL = Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "camel"), EntityType.Builder.create(Camel::new, SpawnGroup.CREATURE).setDimensions(1.2F, 2.1F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "camel").toString()));
    public static final EntityType<ModBoat> BOAT = Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "boat"), EntityType.Builder.<ModBoat>create(ModBoat::new, SpawnGroup.MISC).setDimensions(1.375F, 0.5625F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "boat").toString()));
    public static final EntityType<Penguin> PENGUIN = Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "penguin"), EntityType.Builder.create(Penguin::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.6F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "penguin").toString()));
    public static final EntityType<Squirrel> SQUIRREL = Registry.register(Registry.ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "squirrel"), EntityType.Builder.create(Squirrel::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.8F).maxTrackingRange(10).build(new Identifier(Ecologics.MOD_ID, "squirrel").toString()));
}