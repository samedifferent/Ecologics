package samebutdifferent.ecologics;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.block.grower.ModTreeGrower;
import samebutdifferent.ecologics.block.interaction.MapleSapCauldronInteraction;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCompat;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModFeatures;
import samebutdifferent.ecologics.registry.ModFoliagePlacerTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModMobEffects;
import samebutdifferent.ecologics.registry.ModParticleTypes;
import samebutdifferent.ecologics.registry.ModPotions;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import samebutdifferent.ecologics.registry.ModStructures;
import samebutdifferent.ecologics.registry.ModTreeDecoratorTypes;
import samebutdifferent.ecologics.registry.ModTrunkPlacerTypes;
import samebutdifferent.ecologics.worldgen.structure.pieces.ModStructurePieces;

public class Ecologics 
{
    public static final String MOD_ID = "ecologics";
    public static final Logger LOGGER = LogManager.getLogger();

	public static final Map<Block, Pair<Integer, Integer>> FLAMMABLES = new HashMap<>();
	
	public static ModCompat farmersDelight = null;
    public static ModCompat rusticDelight = null;
	
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
        ModTreeDecoratorTypes.init();
        ModParticleTypes.init();
        ModMobEffects.init();
        ModPotions.init();
    }

    public static void commonSetup() {
        ModWoodType.init();
        ModTreeGrower.init();
        registerFlammables();
        MapleSapCauldronInteraction.registerCauldronInteractions();
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

    public static void registerEntityAttributes(Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes) {
        attributes.put(ModEntityTypes.COCONUT_CRAB, CoconutCrab.createAttributes());
        attributes.put(ModEntityTypes.PENGUIN, Penguin.createAttributes());
        attributes.put(ModEntityTypes.SQUIRREL, Squirrel.createAttributes());
    }
}