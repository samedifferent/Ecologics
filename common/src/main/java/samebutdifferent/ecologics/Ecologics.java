package samebutdifferent.ecologics;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.Holder;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.block.ModCeilingHangingSignBlock;
import samebutdifferent.ecologics.block.ModStandingSignBlock;
import samebutdifferent.ecologics.block.ModWallHangingSignBlock;
import samebutdifferent.ecologics.block.ModWallSignBlock;
import samebutdifferent.ecologics.block.interaction.BucketSpileDispenseItemBehaviour;
import samebutdifferent.ecologics.block.interaction.GlassBottleSyrupDispenseItemBehaviour;
import samebutdifferent.ecologics.block.interaction.MapleSapBucketDispenseItemBehaviour;
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
import samebutdifferent.ecologics.registry.ModFoods;
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

    public static final Map<Holder<Potion>, Pair<ItemLike, Holder<Potion>>> BREWING_RECIPES = new HashMap<>();
    public static final Map<ItemLike, Float> COMPOSTABLES = new HashMap<>();
    public static final Map<Block, Block> STRIPPABLES = new HashMap<>();
    public static final Map<Block, Pair<Integer, Integer>> FLAMMABLES = new HashMap<>();
    
    public static ModCompat farmersDelight = null;
    public static ModCompat rusticDelight = null;
    public static ModCompat vanillaBackport = null;
    
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
        ModMobEffects.init();
        ModPotions.init();
        ModParticleTypes.init();
    }

    public static void commonSetup() {
        ModFoods.init();
        ModWoodType.init();
        registerBrewingRecipes();
        registerCompostables();
        registerStrippables();
        registerFlammables();
        registerDispenserBehaviour();
        MapleSapCauldronInteraction.registerCauldronInteractions();
        fixSignDrops();
    }

    public static void registerBrewingRecipes() {
        BREWING_RECIPES.put(Potions.AWKWARD, new Pair<>(ModItems.PENGUIN_FEATHER, ModPotions.SLIDING));
        BREWING_RECIPES.put(ModPotions.SLIDING, new Pair<>(Items.REDSTONE, ModPotions.LONG_SLIDING));
    }

    public static void registerCompostables() {
        COMPOSTABLES.put(ModItems.COCONUT_SLICE, 0.3F);
        COMPOSTABLES.put(ModItems.COCONUT_HUSK, 0.65F);
        COMPOSTABLES.put(ModItems.PRICKLY_PEAR, 0.65F);
        COMPOSTABLES.put(ModItems.COOKED_PRICKLY_PEAR, 0.65F);
        COMPOSTABLES.put(ModBlocks.COCONUT_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.COCONUT_SEEDLING, 0.3F);
        COMPOSTABLES.put(ModBlocks.WALNUT_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.WALNUT_SAPLING, 0.3F);
        COMPOSTABLES.put(ModBlocks.RED_MAPLE_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.ORANGE_MAPLE_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.YELLOW_MAPLE_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.GREEN_MAPLE_LEAVES, 0.3F);
        COMPOSTABLES.put(ModBlocks.MAPLE_SAPLING, 0.3F);
        COMPOSTABLES.put(ModBlocks.AZALEA_FLOWER, 0.65F);
    }

    public static void registerStrippables() {
        STRIPPABLES.put(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
        STRIPPABLES.put(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);
        STRIPPABLES.put(ModBlocks.MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG);
        STRIPPABLES.put(ModBlocks.SAPPY_MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG);
        STRIPPABLES.put(ModBlocks.MAPLE_WOOD, ModBlocks.STRIPPED_MAPLE_WOOD);
        STRIPPABLES.put(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG);
        STRIPPABLES.put(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
        STRIPPABLES.put(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
        STRIPPABLES.put(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
        STRIPPABLES.put(ModBlocks.FLOWERING_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
        STRIPPABLES.put(ModBlocks.FLOWERING_AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
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
        // MAPLE
        FLAMMABLES.put(ModBlocks.MAPLE_PLANKS, new Pair<>(5, 20));
        FLAMMABLES.put(ModBlocks.MAPLE_SLAB, new Pair<>(5, 20));
        FLAMMABLES.put(ModBlocks.MAPLE_FENCE_GATE, new Pair<>(5, 20));
        FLAMMABLES.put(ModBlocks.MAPLE_FENCE, new Pair<>(5, 20));
        FLAMMABLES.put(ModBlocks.MAPLE_STAIRS, new Pair<>(5, 20));
        FLAMMABLES.put(ModBlocks.MAPLE_LOG, new Pair<>(5, 5));
        FLAMMABLES.put(ModBlocks.SAPPY_MAPLE_LOG, new Pair<>(5, 5));
        FLAMMABLES.put(ModBlocks.STRIPPED_MAPLE_LOG, new Pair<>(5, 5));
        FLAMMABLES.put(ModBlocks.STRIPPED_MAPLE_WOOD, new Pair<>(5, 5));
        FLAMMABLES.put(ModBlocks.MAPLE_WOOD, new Pair<>(5, 5));
        FLAMMABLES.put(ModBlocks.RED_MAPLE_LEAVES, new Pair<>(30, 60));
        FLAMMABLES.put(ModBlocks.ORANGE_MAPLE_LEAVES, new Pair<>(30, 60));
        FLAMMABLES.put(ModBlocks.YELLOW_MAPLE_LEAVES, new Pair<>(30, 60));
        FLAMMABLES.put(ModBlocks.GREEN_MAPLE_LEAVES, new Pair<>(30, 60));
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
    
    public static void registerDispenserBehaviour() {
        DispenseItemBehavior vanillaBucketBehaviour = DispenserBlock.DISPENSER_REGISTRY.get(Items.BUCKET);
        DispenseItemBehavior vanillaBottleBehaviour = DispenserBlock.DISPENSER_REGISTRY.get(Items.GLASS_BOTTLE);
        DispenserBlock.registerBehavior(Items.BUCKET, new BucketSpileDispenseItemBehaviour(vanillaBucketBehaviour));
        DispenserBlock.registerBehavior(Items.GLASS_BOTTLE, new GlassBottleSyrupDispenseItemBehaviour(vanillaBottleBehaviour));
        DispenserBlock.registerBehavior(ModItems.MAPLE_SAP_BUCKET, new MapleSapBucketDispenseItemBehaviour());
    }
    
    // Workaround for 1.21.1. This issue is only present on this specific Minecraft version and not on other versions.
    public static void fixSignDrops() {
        ((ModStandingSignBlock)ModBlocks.AZALEA_SIGN).fixLootTable("azalea_sign");
        ((ModStandingSignBlock)ModBlocks.FLOWERING_AZALEA_SIGN).fixLootTable("flowering_azalea_sign");
        ((ModStandingSignBlock)ModBlocks.COCONUT_SIGN).fixLootTable("coconut_sign");
        ((ModStandingSignBlock)ModBlocks.MAPLE_SIGN).fixLootTable("maple_sign");
        ((ModStandingSignBlock)ModBlocks.WALNUT_SIGN).fixLootTable("walnut_sign");
        ((ModWallSignBlock)ModBlocks.AZALEA_WALL_SIGN).fixLootTable("azalea_sign");
        ((ModWallSignBlock)ModBlocks.FLOWERING_AZALEA_WALL_SIGN).fixLootTable("flowering_azalea_sign");
        ((ModWallSignBlock)ModBlocks.COCONUT_WALL_SIGN).fixLootTable("coconut_sign");
        ((ModWallSignBlock)ModBlocks.MAPLE_WALL_SIGN).fixLootTable("maple_sign");
        ((ModWallSignBlock)ModBlocks.WALNUT_WALL_SIGN).fixLootTable("walnut_sign");
        ((ModCeilingHangingSignBlock)ModBlocks.AZALEA_HANGING_SIGN).fixLootTable("azalea_hanging_sign");
        ((ModCeilingHangingSignBlock)ModBlocks.FLOWERING_AZALEA_HANGING_SIGN).fixLootTable("flowering_azalea_hanging_sign");
        ((ModCeilingHangingSignBlock)ModBlocks.COCONUT_HANGING_SIGN).fixLootTable("coconut_hanging_sign");
        ((ModCeilingHangingSignBlock)ModBlocks.MAPLE_HANGING_SIGN).fixLootTable("maple_hanging_sign");
        ((ModCeilingHangingSignBlock)ModBlocks.WALNUT_HANGING_SIGN).fixLootTable("walnut_hanging_sign");
        ((ModWallHangingSignBlock)ModBlocks.AZALEA_WALL_HANGING_SIGN).fixLootTable("azalea_hanging_sign");
        ((ModWallHangingSignBlock)ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN).fixLootTable("flowering_azalea_hanging_sign");
        ((ModWallHangingSignBlock)ModBlocks.COCONUT_WALL_HANGING_SIGN).fixLootTable("coconut_hanging_sign");
        ((ModWallHangingSignBlock)ModBlocks.MAPLE_WALL_HANGING_SIGN).fixLootTable("maple_hanging_sign");
        ((ModWallHangingSignBlock)ModBlocks.WALNUT_WALL_HANGING_SIGN).fixLootTable("walnut_hanging_sign");
    }
}