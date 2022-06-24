package samebutdifferent.ecologics.event.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobCategory;
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
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.FloweringAzaleaLogBlock;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.forge.ModConfigForge;
import samebutdifferent.ecologics.registry.forge.ModFeaturesForge;
import samebutdifferent.ecologics.util.forge.CodecUtils;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        ResourceLocation biomeName = event.getName();
        if (biomeName != null) {
            if (biomeName.equals(Biomes.BEACH.location())) {
                if (ModConfigForge.GENERATE_COCONUT_TREES.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(ModFeaturesForge.TREES_BEACH.placedFeature().get()));
                if (ModConfigForge.GENERATE_SEASHELLS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(ModFeaturesForge.SEASHELL.placedFeature().get()));
            }
            if (biomeName.equals(Biomes.DESERT.location())) {
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CAMEL.get(), ModConfigForge.CAMEL_SPAWN_WEIGHT.get(), 1, 1));
                if (ModConfigForge.GENERATE_PRICKLY_PEARS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(ModFeaturesForge.PRICKLY_PEAR.placedFeature().get()));
                if (ModConfigForge.GENERATE_DESERT_RUINS.get()) builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Holder.direct(ModFeaturesForge.DESERT_RUIN.placedFeature().get()));
            }
            if (biomeName.equals(Biomes.FROZEN_RIVER.location()) || biomeName.equals(Biomes.FROZEN_OCEAN.location()) || biomeName.equals(Biomes.SNOWY_PLAINS.location())) {
                if (ModConfigForge.GENERATE_THIN_ICE_PATCHES.get()) builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Holder.direct(ModFeaturesForge.THIN_ICE_PATCH.placedFeature().get()));
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.PENGUIN.get(), ModConfigForge.PENGUIN_SPAWN_WEIGHT.get(), 4, 7));
            }
            if (biomeName.equals(Biomes.LUSH_CAVES.location())) {
                if (ModConfigForge.GENERATE_SURFACE_MOSS.get()) builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(ModFeaturesForge.SURFACE_MOSS_PATCH.placedFeature().get()));
                if (ModConfigForge.REPLACE_AZALEA_TREE.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(placedFeatureSupplier -> CodecUtils.serializeAndCompareFeature(placedFeatureSupplier.value(), CavePlacements.ROOTED_AZALEA_TREE.value()));
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(Holder.direct(ModFeaturesForge.ROOTED_AZALEA_TREE.placedFeature().get()));
                }
            }
            if (biomeName.equals(Biomes.PLAINS.location())) {
                if (ModConfigForge.REMOVE_PLAINS_OAK_TREES.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(placedFeatureSupplier -> CodecUtils.serializeAndCompareFeature(placedFeatureSupplier.value(), VegetationPlacements.TREES_PLAINS.value()));
                }
                if (ModConfigForge.GENERATE_WALNUT_TREES.get()) {
                    builder.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(Holder.direct(ModFeaturesForge.TREES_WALNUT.placedFeature().get()));
                }
                event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.SQUIRREL.get(), ModConfigForge.SQUIRREL_SPAWN_WEIGHT.get(), 2, 3));
            }
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        CodecUtils.clearCache();
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
}
