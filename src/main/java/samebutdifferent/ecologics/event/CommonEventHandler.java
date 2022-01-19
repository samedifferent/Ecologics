package samebutdifferent.ecologics.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModVegetationFeatures;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        ResourceLocation biomeName = event.getName();
        if (biomeName != null && biomeName.getPath().equals("beach")) {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationFeatures.TREES_BEACH).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationFeatures.SEASHELLS);
        }
    }
}
