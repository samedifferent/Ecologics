package samebutdifferent.ecologics.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import samebutdifferent.ecologics.block.FloweringAzaleaLogBlock;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.platform.forge.CommonPlatformHelperImpl;
import samebutdifferent.ecologics.registry.*;
import samebutdifferent.ecologics.registry.forge.ModConfigForge;
import samebutdifferent.ecologics.registry.forge.ModGlobalLootModifiers;
import samebutdifferent.ecologics.util.forge.CodecUtils;

import java.util.HashMap;
import java.util.Map;

@Mod(Ecologics.MOD_ID)
public class EcologicsForge {
    public EcologicsForge() {
        Ecologics.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigForge.COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CommonPlatformHelperImpl.BLOCKS.register(bus);
        CommonPlatformHelperImpl.ITEMS.register(bus);
        CommonPlatformHelperImpl.SOUND_EVENTS.register(bus);
        CommonPlatformHelperImpl.ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.BLOCK_ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.FEATURES.register(bus);
        CommonPlatformHelperImpl.TRUNK_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.FOLIAGE_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.CONFIGURED_FEATURES.register(bus);
        CommonPlatformHelperImpl.PLACED_FEATURES.register(bus);
        CommonPlatformHelperImpl.MOB_EFFECTS.register(bus);
        CommonPlatformHelperImpl.POTIONS.register(bus);
        ModGlobalLootModifiers.GLM.register(bus);

        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void registerEntityAttributes(EntityAttributeCreationEvent event) {
        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach((entity, builder) -> event.put(entity, builder.build()));
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ecologics.commonSetup();
        });
    }

    @SubscribeEvent
    public static void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState state = event.getState();
        if (state.is(Blocks.CACTUS)) {
            if (level.getBlockState(pos.above()).is(Blocks.CACTUS) && level.getBlockState(pos.below()).is(Blocks.CACTUS)) {
                if (level.isEmptyBlock(pos.above(2)) && level.getRandom().nextFloat() <= ModConfigForge.PRICKLY_PEAR_GROWTH_CHANCE.get()) {
                    level.setBlock(pos.above(2), ModBlocks.PRICKLY_PEAR.get().defaultBlockState(), 2);
                    level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getWorld();
        Player player = event.getPlayer();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        InteractionHand hand = event.getHand();
        if (state.is(ModBlocks.POT.get()) && player.isCrouching()) {
            if (player.getMainHandItem().getItem() instanceof PickaxeItem && hand.equals(InteractionHand.MAIN_HAND)){
                level.setBlockAndUpdate(pos, state.cycle(PotBlock.CHISEL));
                level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swing(InteractionHand.MAIN_HAND);
                player.getMainHandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }
            if (player.getOffhandItem().getItem() instanceof PickaxeItem && !(player.getMainHandItem().getItem() instanceof PickaxeItem) && hand.equals(InteractionHand.OFF_HAND)){
                level.setBlockAndUpdate(pos, state.cycle(PotBlock.CHISEL));
                level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swing(InteractionHand.OFF_HAND);
                player.getOffhandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.OFF_HAND));
            }
        }
        if (!event.getWorld().isClientSide) {
            ItemStack stack = event.getItemStack();
            Direction direction = event.getHitVec().getDirection().getAxis() == Direction.Axis.Y ? event.getHitVec().getDirection().getOpposite() : event.getHitVec().getDirection();
            if (stack.is(Items.SHEARS)) {
                if (state.is(Blocks.FLOWERING_AZALEA)) {
                    FloweringAzaleaLogBlock.shearAzalea(level, player, pos, stack, hand, direction, Blocks.AZALEA.defaultBlockState());
                    player.swing(hand, true);
                }
                if (state.is(Blocks.FLOWERING_AZALEA_LEAVES)) {
                    FloweringAzaleaLogBlock.shearAzalea(level, player, pos, stack, hand, direction, Blocks.AZALEA_LEAVES.defaultBlockState());
                    player.swing(hand, true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        CodecUtils.clearCache();
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        ResourceLocation biomeName = event.getName();
        if (biomeName != null) {
            if (biomeName.equals(Biomes.BEACH.location())) {
                if (ModConfigForge.GENERATE_COCONUT_TREES.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getOrCreateHolder(new ).TREES_BEACH.getHolder().orElseThrow());
                if (ModConfigForge.GENERATE_SEASHELLS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEASHELL.getHolder().orElseThrow());
            }
            if (biomeName.equals(Biomes.DESERT.location())) {
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CAMEL.get(), ModConfigForge.CAMEL_SPAWN_WEIGHT.get(), 1, 1));
                if (ModConfigForge.GENERATE_PRICKLY_PEARS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PRICKLY_PEAR.getHolder().orElseThrow());
                if (ModConfigForge.GENERATE_DESERT_RUINS.get()) builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacedFeatures.DESERT_RUIN.getHolder().orElseThrow());
            }
            if (biomeName.equals(Biomes.FROZEN_RIVER.location()) || biomeName.equals(Biomes.FROZEN_OCEAN.location()) || biomeName.equals(Biomes.SNOWY_PLAINS.location())) {
                if (ModConfigForge.GENERATE_THIN_ICE_PATCHES.get()) builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.THIN_ICE_PATCH.getHolder().orElseThrow());
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.PENGUIN.get(), ModConfigForge.PENGUIN_SPAWN_WEIGHT.get(), 4, 7));
            }
            if (biomeName.equals(Biomes.LUSH_CAVES.location())) {
                if (ModConfigForge.GENERATE_SURFACE_MOSS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SURFACE_MOSS_PATCH.getHolder().orElseThrow());
                if (ModConfigForge.REPLACE_AZALEA_TREE.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(placedFeatureSupplier -> CodecUtils.serializeAndCompareFeature(placedFeatureSupplier.value(), CavePlacements.ROOTED_AZALEA_TREE.value()));
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.ROOTED_AZALEA_TREE.getHolder().orElseThrow());
                }
            }
            if (biomeName.equals(Biomes.PLAINS.location())) {
                if (ModConfigForge.REMOVE_PLAINS_OAK_TREES.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(placedFeatureSupplier -> CodecUtils.serializeAndCompareFeature(placedFeatureSupplier.value(), VegetationPlacements.TREES_PLAINS.value()));
                }
                if (ModConfigForge.GENERATE_WALNUT_TREES.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.TREES_WALNUT.getHolder().orElseThrow());
                }
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.SQUIRREL.get(), ModConfigForge.SQUIRREL_SPAWN_WEIGHT.get(), 2, 3));
            }
        }
    }
}