package samebutdifferent.ecologics.fabric;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.FloweringAzaleaLogBlock;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModCreativeModeTabContents;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

public class EcologicsFabric implements ModInitializer {
    private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "tab")); //FabricItemGroup.builder(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "tab")).icon(() -> new ItemStack(ModBlocks.COCONUT_LOG.get())).build();

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

    private void registerCreativeTab() {
    	Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB.location(), FabricItemGroup.builder().title(Component.translatable("itemGroup.ecologics.tab")).icon(() -> { return new ItemStack(ModBlocks.COCONUT_LOG); } ).build());
    	ModCreativeModeTabContents.populateTabDatabase();
    }
    
    private static void assignItemsToTab(FabricItemGroupEntries entries) {
        for (ItemLike entry : ModCreativeModeTabContents.TAB_ITEMS) {
        	entries.accept(entry, TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
    
    public void registerEntityAttributes() {
        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach(FabricDefaultAttributeRegistry::register);
    }

    public void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            if (state.is(ModBlocks.POT) && player.isCrouching()) {
                if (player.getMainHandItem().getItem() instanceof PickaxeItem && hand.equals(InteractionHand.MAIN_HAND)){
                    world.setBlockAndUpdate(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swing(InteractionHand.MAIN_HAND);
                    player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                if (player.getOffhandItem().getItem() instanceof PickaxeItem && !(player.getMainHandItem().getItem() instanceof PickaxeItem) && hand.equals(InteractionHand.OFF_HAND)){
                    world.setBlockAndUpdate(hitResult.getBlockPos(), state.cycle(PotBlock.CHISEL));
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    player.swing(InteractionHand.OFF_HAND);
                    player.getOffhandItem().hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
                }
            }
            ItemStack stack = player.getItemInHand(hand);
            Direction direction = hitResult.getDirection().getAxis() == Direction.Axis.Y ? hitResult.getDirection().getOpposite() : hitResult.getDirection();
            if (stack.is(Items.SHEARS)) {
                if (state.is(Blocks.FLOWERING_AZALEA)) {
                    FloweringAzaleaLogBlock.shearAzalea(world, player, hitResult.getBlockPos(), stack, hand, direction, Blocks.AZALEA.defaultBlockState());
                    player.swing(hand, true);
                }
                if (state.is(Blocks.FLOWERING_AZALEA_LEAVES)) {
                    FloweringAzaleaLogBlock.shearAzalea(world, player, hitResult.getBlockPos(), stack, hand, direction, Blocks.AZALEA_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, state.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, state.getValue(LeavesBlock.DISTANCE)));
                    player.swing(hand, true);
                }
            }
            return InteractionResult.PASS;
        });
    }

    public void addFeatures() {
        ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        if (config.beach.generateCoconutTrees) {
            BiomeModifications.addFeature(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.BEACH),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    getPlacedFeatureKey("coconut")
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
        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "remove_azalea_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(Biomes.LUSH_CAVES)), (c) -> {
            if (config.lushCaves.replaceAzaleaTree) {
                c.getGenerationSettings().removeFeature(CavePlacements.ROOTED_AZALEA_TREE);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("rooted_azalea_tree"));
            }
            if (config.lushCaves.generateSurfaceMoss) {
                c.getGenerationSettings().removeFeature(CavePlacements.CLASSIC_VINES);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("surface_moss_patch"));
            }
        });
        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "remove_oak_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(Biomes.PLAINS)), (c) -> {
            if (config.plains.generateWalnutTrees) {
                c.getGenerationSettings().removeFeature(VegetationPlacements.TREES_PLAINS);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("walnut"));
            }
        });
    }

    public void addSpawns() {
        ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        if (config.snowy.spawnPenguins) {
            BiomeModifications.addSpawn((biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.FROZEN_RIVER) || biomeSelector.getBiomeKey().equals(Biomes.FROZEN_OCEAN) || biomeSelector.getBiomeKey().equals(Biomes.SNOWY_PLAINS), MobCategory.CREATURE, ModEntityTypes.PENGUIN, 2, 4, 5);
        }
        if (config.desert.spawnCamels) {
            BiomeModifications.addSpawn((biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.DESERT), MobCategory.CREATURE, EntityType.CAMEL, 1, 1, 1);
        }
        if (config.plains.spawnSquirrels) {
            BiomeModifications.addSpawn((biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.PLAINS), MobCategory.CREATURE, ModEntityTypes.SQUIRREL, 10, 2, 3);
        }
    }

    private ResourceKey<PlacedFeature> getPlacedFeatureKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, key));
    }
}