package samebutdifferent.ecologics.neoforge.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import samebutdifferent.ecologics.Ecologics;

@EventBusSubscriber(modid = Ecologics.MOD_ID) 
public class ModDataGen 
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getReloadableLookupProvider();

        generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));
    }
}
