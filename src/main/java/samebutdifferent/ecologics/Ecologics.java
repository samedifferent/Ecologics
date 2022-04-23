package samebutdifferent.ecologics;

import com.google.common.reflect.Reflection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.entity.Camel;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.registry.*;
import samebutdifferent.ecologics.util.CustomItemGroupBuilder;
import software.bernie.geckolib3.GeckoLib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static net.minecraft.world.biome.BiomeKeys.LUSH_CAVES;
import static net.minecraft.world.biome.BiomeKeys.PLAINS;
import static net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION;

public class Ecologics implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ecologics";
    public static final ItemGroup TAB = CustomItemGroupBuilder.create(MOD_ID).icon(() -> new ItemStack(ModBlocks.COCONUT_LOG)).build();

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()

            .create();
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("ecologics.json");
    public static ModConfiguration CONFIG;

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        CONFIG = AutoConfig.register(ModConfiguration.class, GsonConfigSerializer::new).getConfig();



        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
        ModFeatures.init();
        ModFoliagePlacerTypes.init();
        ModMobEffects.init();
        ModPotions.init();
        ModSigns.init();
        // dopadream was here :3

        GeckoLib.initialize();

        addEvents();
        addEntityAttributes();

        ModTrunkPlacerTypes.register();

        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.PENGUIN_FEATHER, ModPotions.SLIDING);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.SLIDING, Items.REDSTONE, ModPotions.LONG_SLIDING);

        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);
        StrippableBlockRegistry.register(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
        StrippableBlockRegistry.register(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
        StrippableBlockRegistry.register(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
        StrippableBlockRegistry.register(ModBlocks.FLOWERING_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
        StrippableBlockRegistry.register(ModBlocks.FLOWERING_AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.COCONUT_SLICE, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_HUSK.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.WALNUT_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.WALNUT_SAPLING.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.AZALEA_FLOWER.asItem(), 0.65F);

        SpawnRestriction.register(ModEntityTypes.CAMEL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Camel::checkCamelSpawnRules);

        if (BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.ROOTED_AZALEA_TREE.value()).isPresent()) {
            BiomeModifications.create(new Identifier(MOD_ID, "remove_azalea_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(LUSH_CAVES)), (c) -> {
                if (Ecologics.CONFIG.REPLACE_AZALEA_TREE) {
                    c.getGenerationSettings().removeBuiltInFeature(UndergroundPlacedFeatures.ROOTED_AZALEA_TREE.value());
                    c.getGenerationSettings().addFeature(VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.ROOTED_AZALEA_TREE.value()).get());
                }
                if (Ecologics.CONFIG.GENERATE_SURFACE_MOSS) {
                    c.getGenerationSettings().removeBuiltInFeature(UndergroundPlacedFeatures.CLASSIC_VINES_CAVE_FEATURE.value());
                    c.getGenerationSettings().addFeature(VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.SURFACE_MOSS_PATCH.value()).get());
                }
            });
        }
        if (BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.TREES_WALNUT.value()).isPresent()) {
            BiomeModifications.create(new Identifier(MOD_ID, "remove_oak_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(PLAINS)), (c) -> {
                if (Ecologics.CONFIG.GENERATE_WALNUT_TREES) {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_PLAINS.value());
                    c.getGenerationSettings().addFeature(VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.TREES_WALNUT.value()).get());
                }
            });
        }
    }

    public void addPlacedFeatures() {
        if (Ecologics.CONFIG.GENERATE_COCONUT_TREES) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("beach"),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.TREES_BEACH.value()))
            );
        }
        if (Ecologics.CONFIG.GENERATE_SEASHELLS) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("beach"),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.SEASHELL.value()))
            );
        }
        if (Ecologics.CONFIG.GENERATE_THIN_ICE_PATCHES) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_river") || biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_ocean") || biomeSelector.getBiomeKey().getValue().equals("snowy_plains"),
                    GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                    getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.THIN_ICE_PATCH.value()))
            );
        }
        if (Ecologics.CONFIG.GENERATE_PRICKLY_PEARS) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.PRICKLY_PEAR.value()))
            );
        }
        if (Ecologics.CONFIG.GENERATE_DESERT_RUINS) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.DESERT_RUIN.value()))
            );
        }
    }

    public void addSpawns() {
        if (Ecologics.CONFIG.SPAWN_PENGUINS) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_river") || biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_ocean") || biomeSelector.getBiomeKey().getValue().equals("snowy_plains"),
                    SpawnGroup.CREATURE,
                    ModEntityTypes.PENGUIN,
                    2,
                    4,
                    7
            );
        }
        if (Ecologics.CONFIG.SPAWN_CAMELS) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                    SpawnGroup.CREATURE,
                    ModEntityTypes.CAMEL,
                    1,
                    1,
                    1
            );
        }
        if (Ecologics.CONFIG.SPAWN_SQUIRRELS) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(BiomeKeys.PLAINS),
                    SpawnGroup.CREATURE,
                    ModEntityTypes.SQUIRREL,
                    10,
                    2,
                    3
            );
        }
    }

    public void addEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            if (state.isOf(ModBlocks.POT) && player.isInSneakingPose()) {
                if (player.getMainHandStack().getItem() instanceof PickaxeItem && hand.equals(Hand.MAIN_HAND)){
                    world.setBlockState(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swingHand(Hand.MAIN_HAND);
                    player.getMainHandStack().damage(1, player, (plr) -> plr.sendToolBreakStatus(Hand.MAIN_HAND));
                }
                if (player.getOffHandStack().getItem() instanceof PickaxeItem && !(player.getMainHandStack().getItem() instanceof PickaxeItem) && hand.equals(Hand.OFF_HAND)){
                    world.setBlockState(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swingHand(Hand.OFF_HAND);
                    player.getOffHandStack().damage(1, player, (plr) -> plr.sendToolBreakStatus(Hand.MAIN_HAND));
                }
            }
            return ActionResult.PASS;
        });
        addPlacedFeatures();
        addSpawns();
    }

    public void addEntityAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntityTypes.COCONUT_CRAB, CoconutCrab.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.CAMEL, Camel.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.PENGUIN, Penguin.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.SQUIRREL, Squirrel.createAttributes());
    }

    private RegistryKey<PlacedFeature> getPlacedFeature(Identifier placedFeatureId) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, placedFeatureId);
    }

    private Identifier getPlacedFeatureIdentifier(PlacedFeature placedFeature) {
        return BuiltinRegistries.PLACED_FEATURE.getId(placedFeature);
    }
}
