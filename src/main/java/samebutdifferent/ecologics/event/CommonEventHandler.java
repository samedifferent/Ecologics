package samebutdifferent.ecologics.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModConfiguredFeatures;
import samebutdifferent.ecologics.registry.ModEntityTypes;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        ResourceLocation biomeName = event.getName();
        if (biomeName != null) {
            if (biomeName.getPath().equals("beach")) {
                builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.TREES_BEACH).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SEASHELLS);
            }
/*            if (biomeName.getPath().equals("frozen_river") || biomeName.getPath().equals("frozen_ocean")) {
                builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.THIN_ICE_PATCHES);
            }*/
        }
        if (event.getCategory() == Biome.BiomeCategory.DESERT) {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CAMEL.get(), 1, 3, 3));
        }
    }
}
