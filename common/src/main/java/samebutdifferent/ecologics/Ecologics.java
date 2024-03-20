package samebutdifferent.ecologics;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.registry.*;

import java.util.Map;

public class Ecologics {
    public static final String MOD_ID = "ecologics";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
        ModFeatures.init();
        ModTrunkPlacerTypes.init();
        ModFoliagePlacerTypes.init();
        ModMobEffects.init();
        ModPotions.init();
    }

    public static void commonSetup() {
        registerWoodTypes();
        registerBrewingRecipes();
        registerCompostables();
        registerStrippables();
        registerFlammables();
        registerSpawnPlacements();
    }

    public static void registerWoodTypes() {
        CommonPlatformHelper.registerWoodType(ModWoodType.WALNUT);
    }

    public static void registerBrewingRecipes() {
        CommonPlatformHelper.registerBrewingRecipe(Potions.AWKWARD, ModItems.PENGUIN_FEATHER.get(), ModPotions.SLIDING.get());
        CommonPlatformHelper.registerBrewingRecipe(ModPotions.SLIDING.get(), Items.REDSTONE, ModPotions.LONG_SLIDING.get());
    }

    public static void registerCompostables() {
        CommonPlatformHelper.registerCompostable(0.3F, ModItems.COCONUT_SLICE.get());
        CommonPlatformHelper.registerCompostable(0.65F, ModItems.COCONUT_HUSK.get());
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.COCONUT_LEAVES.get());
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.COCONUT_SEEDLING.get());
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.WALNUT_LEAVES.get());
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.WALNUT_SAPLING.get());
    }

    public static void registerStrippables() {
        Map<Block, Block> strippables = new ImmutableMap.Builder<Block, Block>()
                .put(ModBlocks.WALNUT_LOG.get(), ModBlocks.STRIPPED_WALNUT_LOG.get())
                .put(ModBlocks.WALNUT_WOOD.get(), ModBlocks.STRIPPED_WALNUT_WOOD.get()).build();
        CommonPlatformHelper.registerStrippables(strippables);
    }

    public static void registerFlammables() {
        // COCONUT
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_LEAVES, 30, 60);
        // WALNUT
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_PLANKS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_SLAB, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_FENCE_GATE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_FENCE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_STAIRS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_WALNUT_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_WALNUT_WOOD, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_WOOD, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.WALNUT_LEAVES, 30, 60);
    }

    public static void registerSpawnPlacements() {
        CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.PENGUIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Penguin::checkPenguinSpawnRules);
        CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.SQUIRREL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    public static void registerEntityAttributes(Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes) {
        attributes.put(ModEntityTypes.COCONUT_CRAB.get(), CoconutCrab.createAttributes());
        attributes.put(ModEntityTypes.PENGUIN.get(), Penguin.createAttributes());
        attributes.put(ModEntityTypes.SQUIRREL.get(), Squirrel.createAttributes());
    }
}