package samebutdifferent.ecologics.event;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModPlacedFeatures;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        ResourceLocation biomeName = event.getName();
        if (biomeName != null) {
            if (biomeName.getPath().equals("beach")) {
                builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TREES_BEACH).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEASHELL);
            }
/*            if (biomeName.getPath().equals("frozen_river") || biomeName.getPath().equals("frozen_ocean")) {
                builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.THIN_ICE_PATCHES);
            }*/
        }
        if (event.getCategory() == Biome.BiomeCategory.DESERT) {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CAMEL.get(), 1, 3, 3));
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PRICKLY_PEAR);
        }
    }

    @SubscribeEvent
    public static void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState state = event.getState();
        if (state.is(Blocks.CACTUS)) {
            if (level.getBlockState(pos.above()).is(Blocks.CACTUS) && level.getBlockState(pos.below()).is(Blocks.CACTUS)) {
                if (level.isEmptyBlock(pos.above(2))) {
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
        BlockState state = level.getBlockState(event.getPos());
        if (player.getMainHandItem().getItem() instanceof PickaxeItem && state.is(ModBlocks.POT.get()) && player.isCrouching() && event.getHand().equals(InteractionHand.MAIN_HAND)) {
            level.setBlockAndUpdate(event.getPos(), state.cycle(PotBlock.CHISEL));
            level.playSound(null, event.getPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            player.swing(InteractionHand.MAIN_HAND);
            player.getMainHandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        }
    }
}
