package samebutdifferent.ecologics;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
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

public class Ecologics implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ecologics";
    public static final ItemGroup TAB = CustomItemGroupBuilder.create(MOD_ID).icon(() -> new ItemStack(ModBlocks.COCONUT_LOG)).build();

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        Reflection.initialize(
                ModBlocks.class,
                ModItems.class,
                ModSoundEvents.class,
                ModEntityTypes.class,
                ModBlockEntityTypes.class,
                ModFeatures.class,
                ModFoliagePlacerTypes.class,
                ModMobEffects.class,
                ModPotions.class
        );
        GeckoLib.initialize();

        addEvents();
        addEntityAttributes();

        ModTrunkPlacerTypes.register();
        ModConfiguredFeatures.register();
        ModPlacedFeatures.register();

        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.PENGUIN_FEATHER, ModPotions.SLIDING);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.SLIDING, Items.REDSTONE, ModPotions.LONG_SLIDING);

        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.COCONUT_SLICE, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_HUSK.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.COCONUT_LEAVES.asItem(), 0.3F);

        SpawnRestriction.register(ModEntityTypes.CAMEL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Camel::checkCamelSpawnRules);
    }

    public void addPlacedFeatures() {
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("beach"),
                GenerationStep.Feature.VEGETAL_DECORATION,
                getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.TREES_BEACH))
        );
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("beach"),
                GenerationStep.Feature.VEGETAL_DECORATION,
                getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.SEASHELL))
        );
        BiomeModifications.addFeature(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_river") || biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_ocean") || biomeSelector.getBiomeKey().getValue().equals("snowy_plains"),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.THIN_ICE_PATCH))
        );
        BiomeModifications.addFeature(
                (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                GenerationStep.Feature.VEGETAL_DECORATION,
                getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.PRICKLY_PEAR))
        );
        BiomeModifications.addFeature(
                (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                GenerationStep.Feature.VEGETAL_DECORATION,
                getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.DESERT_RUIN))
        );
    }

    public void addSpawns() {
        BiomeModifications.addSpawn(
                (biomeSelector) -> biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_river") || biomeSelector.getBiomeKey().getValue().getPath().equals("frozen_ocean") || biomeSelector.getBiomeKey().getValue().equals("snowy_plains"),
                SpawnGroup.CREATURE,
                ModEntityTypes.PENGUIN,
                2,
                4,
                7
        );
        BiomeModifications.addSpawn(
                (biomeSelector) -> Biome.getCategory(biomeSelector.getBiomeRegistryEntry()).equals(Biome.Category.DESERT),
                SpawnGroup.CREATURE,
                ModEntityTypes.CAMEL,
                1,
                1,
                1
        );
        BiomeModifications.addSpawn(
                (biomeSelector) -> biomeSelector.getBiomeKey().equals(BiomeKeys.PLAINS),
                SpawnGroup.CREATURE,
                ModEntityTypes.SQUIRREL,
                10,
                2,
                3
        );
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
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            addPlacedFeatures();
            addSpawns();
        });
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
