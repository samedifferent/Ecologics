package samebutdifferent.ecologics.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModPlacedFeatures;

public class CommonEventHandler {

    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        Identifier biomeName = event.getName();
        if (biomeName != null) {
            if (biomeName.getPath().equals("beach")) {
                builder.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TREES_BEACH).addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SEASHELL);
            }
/*            if (biomeName.getPath().equals("frozen_river") || biomeName.getPath().equals("frozen_ocean")) {
                builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.THIN_ICE_PATCHES);
            }*/
        }
        if (event.getCategory() == Biome.Category.DESERT) {
            event.getSpawns().addSpawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntityTypes.CAMEL.get(), 1, 1, 1));
            builder.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PRICKLY_PEAR).addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.DESERT_RUIN);
        }
    }

    public static void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        WorldAccess level = event.getWorld();
        BlockState state = event.getState();
        if (state.isOf(Blocks.CACTUS)) {
            if (level.getBlockState(pos.up()).isOf(Blocks.CACTUS) && level.getBlockState(pos.down()).isOf(Blocks.CACTUS)) {
                if (level.isAir(pos.up(2))) {
                    level.setBlockState(pos.up(2), ModBlocks.PRICKLY_PEAR.get().defaultBlockState(), 2);
                    level.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        World level = event.getWorld();
        PlayerEntity player = event.getPlayer();
        BlockState state = level.getBlockState(event.getPos());
        if (state.isIn(ModBlocks.POT.get()) && player.isInSneakingPose()) {
            if (player.getMainHandStack().getItem() instanceof PickaxeItem && event.getHand().equals(Hand.MAIN_HAND)){
                level.setBlockState(event.getPos(), state.cycle(PotBlock.CHISEL));
                level.playSound(null, event.getPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swingHand(Hand.MAIN_HAND);
                player.getMainHandStack().damage(1, player, (plr) -> plr.sendToolBreakStatus(Hand.MAIN_HAND));
            }
            if (player.getOffHandStack().getItem() instanceof PickaxeItem && !(player.getMainHandStack().getItem() instanceof PickaxeItem) && event.getHand().equals(Hand.OFF_HAND)){
                level.setBlockState(event.getPos(), state.cycle(PotBlock.CHISEL));
                level.playSound(null, event.getPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swingHand(Hand.OFF_HAND);
                player.getOffHandStack().damage(1, player, (plr) -> plr.sendToolBreakStatus(Hand.MAIN_HAND));
            }
        }
    }
}
