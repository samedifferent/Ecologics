package samebutdifferent.ecologics;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
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

	public static final Map<Holder<Potion>, Pair<ItemLike, Holder<Potion>>> BREWING_RECIPES = new HashMap<>();
	public static final Map<ItemLike, Float> COMPOSTABLES = new HashMap<>();
	public static final Map<Block, Block> STRIPPABLES = new HashMap<>();
	public static final Map<Block, Pair<Integer, Integer>> FLAMMABLES = new HashMap<>();
	
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
        ModWoodType.init();
        registerBrewingRecipes();
        registerCompostables();
        registerStrippables();
        registerFlammables();
        registerSpawnPlacements();
    }

    public static void registerBrewingRecipes() {
    	BREWING_RECIPES.put(Potions.AWKWARD, new Pair<>(ModItems.PENGUIN_FEATHER, ModPotions.SLIDING));
    	BREWING_RECIPES.put(ModPotions.SLIDING, new Pair<>(Items.REDSTONE, ModPotions.LONG_SLIDING));
    }

    public static void registerCompostables() {
    	COMPOSTABLES.put(ModItems.COCONUT_SLICE, 0.3F);
    	COMPOSTABLES.put(ModItems.COCONUT_HUSK, 0.65F);
    	COMPOSTABLES.put(ModBlocks.COCONUT_LEAVES, 0.3F);
    	COMPOSTABLES.put(ModBlocks.COCONUT_SEEDLING, 0.3F);
    	COMPOSTABLES.put(ModBlocks.WALNUT_LEAVES, 0.3F);
    	COMPOSTABLES.put(ModBlocks.WALNUT_SAPLING, 0.3F);
    	COMPOSTABLES.put(ModBlocks.AZALEA_FLOWER, 0.65F);
    }

    public static void registerStrippables() {
    	STRIPPABLES.put(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
    	STRIPPABLES.put(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);
    	STRIPPABLES.put(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG);
    	STRIPPABLES.put(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
    	STRIPPABLES.put(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
    	STRIPPABLES.put(ModBlocks.FLOWERING_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
    	STRIPPABLES.put(ModBlocks.FLOWERING_AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
    	STRIPPABLES.put(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
    }

    public static void registerFlammables() {
        // COCONUT
    	FLAMMABLES.put(ModBlocks.COCONUT_PLANKS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.COCONUT_SLAB, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.COCONUT_FENCE_GATE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.COCONUT_FENCE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.COCONUT_STAIRS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.COCONUT_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_COCONUT_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_COCONUT_WOOD, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.COCONUT_WOOD, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.COCONUT_LEAVES, new Pair<>(30, 60));
        // WALNUT
    	FLAMMABLES.put(ModBlocks.WALNUT_PLANKS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.WALNUT_SLAB, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.WALNUT_FENCE_GATE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.WALNUT_FENCE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.WALNUT_STAIRS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.WALNUT_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_WALNUT_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_WALNUT_WOOD, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.WALNUT_WOOD, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.WALNUT_LEAVES, new Pair<>(30, 60));
        // AZALEA
    	FLAMMABLES.put(ModBlocks.AZALEA_PLANKS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.AZALEA_SLAB, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.AZALEA_FENCE_GATE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.AZALEA_FENCE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.AZALEA_STAIRS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.AZALEA_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_AZALEA_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.STRIPPED_AZALEA_WOOD, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.AZALEA_WOOD, new Pair<>(5, 5));
        // FLOWERING_AZALEA
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_PLANKS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_SLAB, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_FENCE_GATE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_FENCE, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_STAIRS, new Pair<>(5, 20));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_LOG, new Pair<>(5, 5));
    	FLAMMABLES.put(ModBlocks.FLOWERING_AZALEA_WOOD, new Pair<>(5, 5));
    }

    public static void registerSpawnPlacements() {
    	// TODO: Replace this.
        // CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.PENGUIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Penguin::checkPenguinSpawnRules);
        // CommonPlatformHelper.registerSpawnPlacement(ModEntityTypes.SQUIRREL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    public static void registerEntityAttributes(Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes) {
        attributes.put(ModEntityTypes.COCONUT_CRAB, CoconutCrab.createAttributes());
        attributes.put(ModEntityTypes.PENGUIN, Penguin.createAttributes());
        attributes.put(ModEntityTypes.SQUIRREL, Squirrel.createAttributes());
    }
}