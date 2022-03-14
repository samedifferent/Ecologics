package samebutdifferent.ecologics;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.Camel;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.mixin.SignTypeAccessor;
import samebutdifferent.ecologics.registry.*;
import software.bernie.geckolib3.GeckoLib;

public class Ecologics implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ecologics";
    public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "ecologics"))
            .icon(() -> new ItemStack(ModBlocks.COCONUT_LOG))
            .build();

    @Override
    public void onInitialize() {
        new ModBlocks();
        new ModItems();
        new ModSoundEvents();
        new ModEntityTypes();
        new ModBlockEntityTypes();
        new ModFeatures();
        GeckoLib.initialize();

        SignTypeAccessor.registerNew(ModWoodType.COCONUT);
        ModTrunkPlacerTypes.register();
        ModFoliagePlacerTypes.register();
        ModConfiguredFeatures.register();
        ModPlacedFeatures.register();
        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.COCONUT_SLICE, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_HUSK.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_LEAVES.asItem(), 0.3F);

        FabricDefaultAttributeRegistry.register(ModEntityTypes.COCONUT_CRAB, CoconutCrab.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.CAMEL, Camel.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.PENGUIN, Penguin.createAttributes());

        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (LootTables.BURIED_TREASURE_CHEST.equals(id)) {

            }
        });

        // Add beach features
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().equals(new Identifier("minecraft:beach")),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Ecologics.MOD_ID, "trees_beach")));
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().equals(new Identifier("minecraft:beach")),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Ecologics.MOD_ID, "seashell")));

        // Add frozen features
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().equals(new Identifier("minecraft:frozen_river")) || biomeSelector.getBiomeKey().getValue().equals(new Identifier("minecraft:frozen_ocean")),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Ecologics.MOD_ID, "thin_ice_patch")));

        // Add desert features
        BiomeModifications.addSpawn(
                (biomeSelector) -> biomeSelector.getBiome().getCategory().equals(Biome.Category.DESERT),
                SpawnGroup.CREATURE,
                ModEntityTypes.CAMEL, 1, 1, 1);
    }
}
