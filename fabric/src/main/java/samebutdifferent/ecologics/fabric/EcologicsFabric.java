package samebutdifferent.ecologics.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

import java.util.HashMap;
import java.util.Map;

public class EcologicsFabric implements ModInitializer {
    private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Ecologics.MOD_ID, "tab"));

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfigFabric.class, GsonConfigSerializer::new);
        Ecologics.init();
        registerEntityAttributes();
        registerEvents();
        addFeatures();
        replaceFeatures();
        addSpawns();
        Ecologics.commonSetup();
        registerCreativeTab();
        ItemGroupEvents.modifyEntriesEvent(TAB).register(EcologicsFabric::assignItemsToTab);
    }

    //TODO: Update this.
    private void registerCreativeTab() {
    	Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB.location(), FabricItemGroup.builder().title(Component.translatable("itemGroup.ecologics.tab")).icon(() -> { return new ItemStack(ModBlocks.SANDCASTLE.get()); } ).build());
    }

    private static void assignItemsToTab(FabricItemGroupEntries entries) {

        entries.accept(ModBlocks.WALNUT_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_WALNUT_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_WALNUT_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_PLANKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_FENCE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_FENCE_GATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_DOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_TRAPDOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_BUTTON.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_PRESSURE_PLATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
        entries.accept(ModItems.WALNUT_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_HANGING_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);

        entries.accept(ModItems.WALNUT_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_CHEST_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);

        entries.accept(ModBlocks.PALM_LEAVES.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_LEAVES.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.PALM_SAPLING.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_SAPLING.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
        entries.accept(ModBlocks.COCONUT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL_BLOCK.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL_TILES.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL_TILE_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL_TILE_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SEASHELL_TILE_WALL.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.POT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.THIN_ICE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.ICE_BRICKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.ICE_BRICK_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.ICE_BRICK_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.ICE_BRICK_WALL.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SNOW_BRICKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SNOW_BRICK_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SNOW_BRICK_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.SNOW_BRICK_WALL.get(), TabVisibility.PARENT_AND_SEARCH_TABS);

        entries.accept(ModBlocks.SURFACE_MOSS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COCONUT_SLICE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COCONUT_HUSK.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.CRAB_CLAW.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.CRAB_MEAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.TROPICAL_STEW.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COCONUT_CRAB_SPAWN_EGG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.PENGUIN_SPAWN_EGG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.SQUIRREL_SPAWN_EGG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.SANDCASTLE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.MUSIC_DISC_COCONUT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.PRICKLY_PEAR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COOKED_PRICKLY_PEAR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.PENGUIN_FEATHER.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    
    public void registerEntityAttributes() {
        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach(FabricDefaultAttributeRegistry::register);
    }

    public void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            if (state.is(ModBlocks.POT.get()) && player.isCrouching()) {
                if (player.getMainHandItem().getItem() instanceof PickaxeItem && hand.equals(InteractionHand.MAIN_HAND)){
                    world.setBlockAndUpdate(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swing(InteractionHand.MAIN_HAND);
                    player.getMainHandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                }
                if (player.getOffhandItem().getItem() instanceof PickaxeItem && !(player.getMainHandItem().getItem() instanceof PickaxeItem) && hand.equals(InteractionHand.OFF_HAND)){
                    world.setBlockAndUpdate(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swing(InteractionHand.OFF_HAND);
                    player.getOffhandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.OFF_HAND));
                }
            }
            ItemStack stack = player.getItemInHand(hand);
            Direction direction = hitResult.getDirection().getAxis() == Direction.Axis.Y ? hitResult.getDirection().getOpposite() : hitResult.getDirection();
            return InteractionResult.PASS;
        });
    }

    public void addFeatures() {
        ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        if (config.beach.generatePalmTrees) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.BEACH),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    getPlacedFeatureKey("trees_beach")
            );
        }
        if (config.beach.generateSeashells) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.BEACH),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    getPlacedFeatureKey("seashell")
            );
        }
        if (config.snowy.generateThinIcePatches) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.FROZEN_RIVER) || biomeSelector.getBiomeKey().equals(Biomes.FROZEN_OCEAN) || biomeSelector.getBiomeKey().equals(Biomes.SNOWY_PLAINS),
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    getPlacedFeatureKey("thin_ice_patch")
            );
        }
        if (config.desert.generatePricklyPears) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.DESERT),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    getPlacedFeatureKey("prickly_pear")
            );
        }
        if (config.desert.generateDesertRuins) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.DESERT),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    getPlacedFeatureKey("desert_ruin")
            );
        }
    }

    public void replaceFeatures() {
        ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        BiomeModifications.create(new ResourceLocation(Ecologics.MOD_ID, "add_surface_moss")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(Biomes.LUSH_CAVES)), (c) -> {
            if (config.lushCaves.generateSurfaceMoss) {
                c.getGenerationSettings().removeFeature(CavePlacements.CLASSIC_VINES);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("surface_moss_patch"));
            }
        });
        BiomeModifications.create(new ResourceLocation(Ecologics.MOD_ID, "remove_oak_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(Biomes.PLAINS)), (c) -> {
            if (config.plains.generateWalnutTrees) {
                c.getGenerationSettings().removeFeature(VegetationPlacements.TREES_PLAINS);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("trees_walnut"));
            }
        });
    }

    public void addSpawns() {
        ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        if (config.snowy.spawnPenguins) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.FROZEN_RIVER) || biomeSelector.getBiomeKey().equals(Biomes.FROZEN_OCEAN) || biomeSelector.getBiomeKey().equals(Biomes.SNOWY_PLAINS),
                    MobCategory.CREATURE,
                    ModEntityTypes.PENGUIN.get(),
                    2,
                    4,
                    5
            );
        }
        if (config.plains.spawnSquirrels) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.PLAINS),
                    MobCategory.CREATURE,
                    ModEntityTypes.SQUIRREL.get(),
                    10,
                    2,
                    3
            );
        }
    }

    private ResourceKey<PlacedFeature> getPlacedFeatureKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Ecologics.MOD_ID, key));
    }
}