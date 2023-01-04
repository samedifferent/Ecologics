package samebutdifferent.ecologics.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
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
import java.util.stream.Stream;

public class EcologicsFabric implements ModInitializer {
    private static final CreativeModeTab TAB = FabricItemGroup.builder(new ResourceLocation(Ecologics.MOD_ID, "tab"))
            .icon(() -> new ItemStack(ModBlocks.COCONUT_LOG.get())).build();

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
    }

    private void registerCreativeTab() {
        ItemGroupEvents.modifyEntriesEvent(TAB).register(content -> content.acceptAll(Stream.of(
                ModBlocks.COCONUT_LOG,
                ModBlocks.STRIPPED_COCONUT_LOG,
                ModBlocks.COCONUT_WOOD,
                ModBlocks.STRIPPED_COCONUT_WOOD,
                ModBlocks.COCONUT_LEAVES,
                ModBlocks.COCONUT_PLANKS,
                ModBlocks.COCONUT_SLAB,
                ModBlocks.COCONUT_STAIRS,
                ModBlocks.COCONUT_FENCE,
                ModBlocks.COCONUT_FENCE_GATE,
                ModBlocks.COCONUT_DOOR,
                ModBlocks.COCONUT_TRAPDOOR,
                ModBlocks.COCONUT_BUTTON,
                ModBlocks.COCONUT_PRESSURE_PLATE,
                ModBlocks.COCONUT,
                ModBlocks.COCONUT_SEEDLING,
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
                ModBlocks.WALNUT_LOG,
                ModBlocks.STRIPPED_WALNUT_LOG,
                ModBlocks.WALNUT_WOOD,
                ModBlocks.STRIPPED_WALNUT_WOOD,
                ModBlocks.WALNUT_LEAVES,
                ModBlocks.WALNUT_PLANKS,
                ModBlocks.WALNUT_SLAB,
                ModBlocks.WALNUT_STAIRS,
                ModBlocks.WALNUT_FENCE,
                ModBlocks.WALNUT_FENCE_GATE,
                ModBlocks.WALNUT_DOOR,
                ModBlocks.WALNUT_TRAPDOOR,
                ModBlocks.WALNUT_BUTTON,
                ModBlocks.WALNUT_PRESSURE_PLATE,
                ModBlocks.WALNUT_SAPLING,
                ModBlocks.AZALEA_LOG,
                ModBlocks.FLOWERING_AZALEA_LOG,
                ModBlocks.STRIPPED_AZALEA_LOG,
                ModBlocks.AZALEA_WOOD,
                ModBlocks.FLOWERING_AZALEA_WOOD,
                ModBlocks.STRIPPED_AZALEA_WOOD,
                ModBlocks.AZALEA_PLANKS,
                ModBlocks.FLOWERING_AZALEA_PLANKS,
                ModBlocks.AZALEA_SLAB,
                ModBlocks.FLOWERING_AZALEA_SLAB,
                ModBlocks.AZALEA_STAIRS,
                ModBlocks.FLOWERING_AZALEA_STAIRS,
                ModBlocks.AZALEA_FENCE,
                ModBlocks.FLOWERING_AZALEA_FENCE,
                ModBlocks.AZALEA_FENCE_GATE,
                ModBlocks.FLOWERING_AZALEA_FENCE_GATE,
                ModBlocks.AZALEA_DOOR,
                ModBlocks.FLOWERING_AZALEA_DOOR,
                ModBlocks.AZALEA_TRAPDOOR,
                ModBlocks.FLOWERING_AZALEA_TRAPDOOR,
                ModBlocks.AZALEA_BUTTON,
                ModBlocks.AZALEA_PRESSURE_PLATE,
                ModBlocks.AZALEA_FLOWER,
                ModBlocks.SURFACE_MOSS,
                ModItems.COCONUT_SLICE,
                ModItems.COCONUT_HUSK,
                ModItems.CRAB_CLAW,
                ModItems.CRAB_MEAT,
                ModItems.TROPICAL_STEW,
                ModItems.COCONUT_CRAB_SPAWN_EGG,
                ModItems.CAMEL_SPAWN_EGG,
                ModItems.PENGUIN_SPAWN_EGG,
                ModItems.SQUIRREL_SPAWN_EGG,
                ModItems.SANDCASTLE,
                ModItems.MUSIC_DISC_COCONUT,
                ModItems.COCONUT_SIGN,
                ModItems.PRICKLY_PEAR,
                ModItems.COOKED_PRICKLY_PEAR,
                ModItems.PENGUIN_FEATHER,
                ModItems.WALNUT_SIGN,
                ModItems.WALNUT,
                ModItems.AZALEA_SIGN,
                ModItems.FLOWERING_AZALEA_SIGN,
                ModItems.COCONUT_BOAT,
                ModItems.WALNUT_BOAT,
                ModItems.AZALEA_BOAT,
                ModItems.FLOWERING_AZALEA_BOAT,
                ModItems.COCONUT_CHEST_BOAT,
                ModItems.WALNUT_CHEST_BOAT,
                ModItems.AZALEA_CHEST_BOAT,
                ModItems.FLOWERING_AZALEA_CHEST_BOAT
        ).map(item -> item.get().asItem().getDefaultInstance()).toList()));
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
        if (config.desert.spawnCamels) {
            BiomeModifications.addSpawn(
                    (biomeSelector) -> biomeSelector.getBiomeKey().equals(Biomes.DESERT),
                    MobCategory.CREATURE,
                    ModEntityTypes.CAMEL.get(),
                    1,
                    1,
                    1
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