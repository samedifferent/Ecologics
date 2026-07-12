package samebutdifferent.ecologics;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableMap;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import samebutdifferent.ecologics.block.ModCeilingHangingSignBlock;
import samebutdifferent.ecologics.block.ModStandingSignBlock;
import samebutdifferent.ecologics.block.ModWallHangingSignBlock;
import samebutdifferent.ecologics.block.ModWallSignBlock;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModFeatures;
import samebutdifferent.ecologics.registry.ModFoliagePlacerTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModMobEffects;
import samebutdifferent.ecologics.registry.ModPotions;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import samebutdifferent.ecologics.registry.ModStructures;
import samebutdifferent.ecologics.registry.ModTrunkPlacerTypes;
import samebutdifferent.ecologics.worldgen.structure.pieces.ModStructurePieces;

public class Ecologics 
{
    public static final String MOD_ID = "ecologics";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
        ModFeatures.init();
        ModStructures.init();
        ModStructurePieces.init();
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
        fixSignDrops();
    }

    public static void registerWoodTypes() {
        CommonPlatformHelper.registerWoodType(ModWoodType.COCONUT);
        CommonPlatformHelper.registerWoodType(ModWoodType.WALNUT);
        CommonPlatformHelper.registerWoodType(ModWoodType.AZALEA);
        CommonPlatformHelper.registerWoodType(ModWoodType.FLOWERING_AZALEA);
    }

    public static void registerBrewingRecipes() {
        CommonPlatformHelper.registerBrewingRecipe(Potions.AWKWARD, ModItems.PENGUIN_FEATHER, ModPotions.SLIDING);
        CommonPlatformHelper.registerBrewingRecipe(ModPotions.SLIDING, Items.REDSTONE, ModPotions.LONG_SLIDING);
    }

    public static void registerCompostables() {
        CommonPlatformHelper.registerCompostable(0.3F, ModItems.COCONUT_SLICE);
        CommonPlatformHelper.registerCompostable(0.65F, ModItems.COCONUT_HUSK);
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.COCONUT_LEAVES);
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.COCONUT_SEEDLING);
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.WALNUT_LEAVES);
        CommonPlatformHelper.registerCompostable(0.3F, ModBlocks.WALNUT_SAPLING);
        CommonPlatformHelper.registerCompostable(0.65F, ModBlocks.AZALEA_FLOWER);
    }

    public static void registerStrippables() {
        Map<Block, Block> strippables = new ImmutableMap.Builder<Block, Block>()
                .put(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG)
                .put(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD)
                .put(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG)
                .put(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD)
                .put(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG)
                .put(ModBlocks.FLOWERING_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG)
                .put(ModBlocks.FLOWERING_AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD)
                .put(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD).build();
        CommonPlatformHelper.registerStrippables(strippables);
    }

    public static void registerFlammables() {
        // COCONUT
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_PLANKS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_SLAB, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_FENCE_GATE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_FENCE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_STAIRS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_COCONUT_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_COCONUT_WOOD, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.COCONUT_WOOD, 5, 5);
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
        // AZALEA
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_PLANKS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_SLAB, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_FENCE_GATE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_FENCE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_STAIRS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_AZALEA_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.STRIPPED_AZALEA_WOOD, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.AZALEA_WOOD, 5, 5);
        // FLOWERING_AZALEA
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_PLANKS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_SLAB, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_FENCE_GATE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_FENCE, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_STAIRS, 5, 20);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_LOG, 5, 5);
        CommonPlatformHelper.setFlammable(ModBlocks.FLOWERING_AZALEA_WOOD, 5, 5);
    }

    public static void registerSpawnPlacements() {
        //CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.CAMEL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Camel::checkCamelSpawnRules);
        CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.PENGUIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Penguin::checkPenguinSpawnRules);
        CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.SQUIRREL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    public static void registerEntityAttributes(Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes) {
        attributes.put(ModEntityTypes.COCONUT_CRAB, CoconutCrab.createAttributes());
        //attributes.put(ModEntityTypes.CAMEL.get(), Camel.createAttributes());
        attributes.put(ModEntityTypes.PENGUIN, Penguin.createAttributes());
        attributes.put(ModEntityTypes.SQUIRREL, Squirrel.createAttributes());
    }
    
    // Workaround for 1.21.1. This issue is only present on this specific Minecraft version and not on other versions.
    public static void fixSignDrops() {
    	((ModStandingSignBlock)ModBlocks.AZALEA_SIGN).fixLootTable("azalea_sign");
    	((ModStandingSignBlock)ModBlocks.FLOWERING_AZALEA_SIGN).fixLootTable("flowering_azalea_sign");
    	((ModStandingSignBlock)ModBlocks.COCONUT_SIGN).fixLootTable("coconut_sign");
    	((ModStandingSignBlock)ModBlocks.WALNUT_SIGN).fixLootTable("walnut_sign");
    	((ModWallSignBlock)ModBlocks.AZALEA_WALL_SIGN).fixLootTable("azalea_sign");
    	((ModWallSignBlock)ModBlocks.FLOWERING_AZALEA_WALL_SIGN).fixLootTable("flowering_azalea_sign");
    	((ModWallSignBlock)ModBlocks.COCONUT_WALL_SIGN).fixLootTable("coconut_sign");
    	((ModWallSignBlock)ModBlocks.WALNUT_WALL_SIGN).fixLootTable("walnut_sign");
    	((ModCeilingHangingSignBlock)ModBlocks.AZALEA_HANGING_SIGN).fixLootTable("azalea_hanging_sign");
    	((ModCeilingHangingSignBlock)ModBlocks.FLOWERING_AZALEA_HANGING_SIGN).fixLootTable("flowering_azalea_hanging_sign");
    	((ModCeilingHangingSignBlock)ModBlocks.COCONUT_HANGING_SIGN).fixLootTable("coconut_hanging_sign");
    	((ModCeilingHangingSignBlock)ModBlocks.WALNUT_HANGING_SIGN).fixLootTable("walnut_hanging_sign");
    	((ModWallHangingSignBlock)ModBlocks.AZALEA_WALL_HANGING_SIGN).fixLootTable("azalea_hanging_sign");
    	((ModWallHangingSignBlock)ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN).fixLootTable("flowering_azalea_hanging_sign");
    	((ModWallHangingSignBlock)ModBlocks.COCONUT_WALL_HANGING_SIGN).fixLootTable("coconut_hanging_sign");
    	((ModWallHangingSignBlock)ModBlocks.WALNUT_WALL_HANGING_SIGN).fixLootTable("walnut_hanging_sign");
    }
}