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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
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
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

import java.util.HashMap;
import java.util.Map;

public class EcologicsFabric implements ModInitializer {
    private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Ecologics.MOD_ID, "tab")); //FabricItemGroup.builder(new ResourceLocation(Ecologics.MOD_ID, "tab")).icon(() -> new ItemStack(ModBlocks.COCONUT_LOG.get())).build();

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
    	Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB.location(), FabricItemGroup.builder().title(Component.translatable("itemGroup.ecologics.tab")).icon(() -> { return new ItemStack(ModBlocks.COCONUT_LOG.get()); } ).build());
    }
    
    /*private static void assignItemsToTab(FabricItemGroupEntries entries) {
        ItemGroupEvents.modifyEntriesEvent(TAB).register(content -> content.acceptAll(Stream.of(
                ModBlocks.COCONUT_LOG,
                ModBlocks.STRIPPED_COCONUT_LOG,
                ModBlocks.COCONUT_WOOD,
                ModBlocks.STRIPPED_COCONUT_WOOD,
                ModBlocks.COCONUT_PLANKS,
                ModBlocks.COCONUT_STAIRS,
                ModBlocks.COCONUT_SLAB,
                ModBlocks.COCONUT_FENCE,
                ModBlocks.COCONUT_FENCE_GATE,
                ModBlocks.COCONUT_DOOR,
                ModBlocks.COCONUT_TRAPDOOR,
                ModBlocks.COCONUT_BUTTON,
                ModBlocks.COCONUT_PRESSURE_PLATE,
                
                ModBlocks.WALNUT_LOG,
                ModBlocks.STRIPPED_WALNUT_LOG,
                ModBlocks.WALNUT_WOOD,
                ModBlocks.STRIPPED_WALNUT_WOOD,
                ModBlocks.WALNUT_PLANKS,
                ModBlocks.WALNUT_STAIRS,
                ModBlocks.WALNUT_SLAB,
                ModBlocks.WALNUT_FENCE,
                ModBlocks.WALNUT_FENCE_GATE,
                ModBlocks.WALNUT_DOOR,
                ModBlocks.WALNUT_TRAPDOOR,
                ModBlocks.WALNUT_BUTTON,
                ModBlocks.WALNUT_PRESSURE_PLATE,
                
                ModBlocks.AZALEA_LOG,
                ModBlocks.STRIPPED_AZALEA_LOG,
                ModBlocks.AZALEA_WOOD,
                ModBlocks.STRIPPED_AZALEA_WOOD,
                ModBlocks.AZALEA_PLANKS,
                ModBlocks.AZALEA_STAIRS,
                ModBlocks.AZALEA_SLAB,
                ModBlocks.AZALEA_FENCE,
                ModBlocks.AZALEA_FENCE_GATE,
                ModBlocks.AZALEA_DOOR,
                ModBlocks.AZALEA_TRAPDOOR,
                ModBlocks.AZALEA_BUTTON,
                ModBlocks.AZALEA_PRESSURE_PLATE,

                ModBlocks.FLOWERING_AZALEA_LOG,
                ModBlocks.FLOWERING_AZALEA_WOOD,
                ModBlocks.FLOWERING_AZALEA_PLANKS,
                ModBlocks.FLOWERING_AZALEA_STAIRS,
                ModBlocks.FLOWERING_AZALEA_SLAB,
                ModBlocks.FLOWERING_AZALEA_FENCE,
                ModBlocks.FLOWERING_AZALEA_FENCE_GATE,
                ModBlocks.FLOWERING_AZALEA_DOOR,
                ModBlocks.FLOWERING_AZALEA_TRAPDOOR,
                
                ModItems.COCONUT_SIGN,
                ModItems.COCONUT_HANGING_SIGN,
                ModItems.WALNUT_SIGN,
                ModItems.WALNUT_HANGING_SIGN,
                ModItems.AZALEA_SIGN,
                ModItems.AZALEA_HANGING_SIGN,
                ModItems.FLOWERING_AZALEA_SIGN,
                ModItems.FLOWERING_AZALEA_HANGING_SIGN,
                
                ModItems.COCONUT_BOAT,
                ModItems.COCONUT_CHEST_BOAT,
                ModItems.WALNUT_BOAT,
                ModItems.WALNUT_CHEST_BOAT,
                ModItems.AZALEA_BOAT,
                ModItems.AZALEA_CHEST_BOAT,
                ModItems.FLOWERING_AZALEA_BOAT,
                ModItems.FLOWERING_AZALEA_CHEST_BOAT,
                
                ModBlocks.COCONUT_LEAVES,
                ModBlocks.WALNUT_LEAVES,
                ModBlocks.COCONUT_SEEDLING,
                ModBlocks.WALNUT_SAPLING,
                
                ModBlocks.COCONUT,
                ModBlocks.SEASHELL,
                ModBlocks.SEASHELL_BLOCK,
                ModBlocks.SEASHELL_TILES,
                ModBlocks.SEASHELL_TILE_STAIRS,
                ModBlocks.SEASHELL_TILE_SLAB,
                ModBlocks.SEASHELL_TILE_WALL,
                ModBlocks.POT,
                ModBlocks.THIN_ICE,
                ModBlocks.ICE_BRICKS,
                ModBlocks.ICE_BRICK_STAIRS,
                ModBlocks.ICE_BRICK_SLAB,
                ModBlocks.ICE_BRICK_WALL,
                ModBlocks.SNOW_BRICKS,
                ModBlocks.SNOW_BRICK_STAIRS,
                ModBlocks.SNOW_BRICK_SLAB,
                ModBlocks.SNOW_BRICK_WALL,

                ModBlocks.AZALEA_FLOWER,
                ModBlocks.SURFACE_MOSS,
                ModItems.COCONUT_SLICE,
                ModItems.COCONUT_HUSK,
                ModItems.CRAB_CLAW,
                ModItems.CRAB_MEAT,
                ModItems.TROPICAL_STEW,
                ModItems.COCONUT_CRAB_SPAWN_EGG,
                ModItems.PENGUIN_SPAWN_EGG,
                ModItems.SQUIRREL_SPAWN_EGG,
                ModItems.SANDCASTLE,
                ModItems.MUSIC_DISC_COCONUT,
                ModItems.PRICKLY_PEAR,
                ModItems.COOKED_PRICKLY_PEAR,
                ModItems.PENGUIN_FEATHER,
                ModItems.WALNUT

        ).map(item -> item.get().asItem().getDefaultInstance()).toList()));
    }*/

    private static void assignItemsToTab(FabricItemGroupEntries entries) {
        entries.accept(ModBlocks.COCONUT_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_COCONUT_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_COCONUT_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_PLANKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_FENCE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_FENCE_GATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_DOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_TRAPDOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_BUTTON.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_PRESSURE_PLATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
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
            
        entries.accept(ModBlocks.AZALEA_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_AZALEA_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.STRIPPED_AZALEA_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_PLANKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_FENCE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_FENCE_GATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_DOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_TRAPDOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_BUTTON.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.AZALEA_PRESSURE_PLATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);

        entries.accept(ModBlocks.FLOWERING_AZALEA_LOG.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_WOOD.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_PLANKS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_STAIRS.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_SLAB.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_FENCE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_FENCE_GATE.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_DOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.FLOWERING_AZALEA_TRAPDOOR.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
        entries.accept(ModItems.COCONUT_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COCONUT_HANGING_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_HANGING_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.AZALEA_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.AZALEA_HANGING_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.FLOWERING_AZALEA_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.FLOWERING_AZALEA_HANGING_SIGN.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
        entries.accept(ModItems.COCONUT_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.COCONUT_CHEST_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.WALNUT_CHEST_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.AZALEA_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.AZALEA_CHEST_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.FLOWERING_AZALEA_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModItems.FLOWERING_AZALEA_CHEST_BOAT.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
            
        entries.accept(ModBlocks.COCONUT_LEAVES.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.WALNUT_LEAVES.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ModBlocks.COCONUT_SEEDLING.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
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

        entries.accept(ModBlocks.AZALEA_FLOWER.get(), TabVisibility.PARENT_AND_SEARCH_TABS);
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
        BiomeModifications.create(new ResourceLocation(Ecologics.MOD_ID, "remove_azalea_trees")).add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> (biomeSelectionContext.getBiomeKey().equals(Biomes.LUSH_CAVES)), (c) -> {
            if (config.lushCaves.replaceAzaleaTree) {
                c.getGenerationSettings().removeFeature(CavePlacements.ROOTED_AZALEA_TREE);
                c.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, getPlacedFeatureKey("rooted_azalea_tree"));
            }
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
        /*if (config.desert.spawnCamels) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.DESERT),
                    MobCategory.CREATURE,
                    ModEntityTypes.CAMEL.get(),
                    1,
                    1,
                    1
            );
        }*/
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