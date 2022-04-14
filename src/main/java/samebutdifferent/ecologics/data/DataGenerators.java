package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import samebutdifferent.ecologics.Ecologics;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
//        generator.addProvider(new BlockstateGenerator(generator, event.getExistingFileHelper()));
//        generator.addProvider(new ItemModelGenerator(generator, event.getExistingFileHelper()));
//        generator.addProvider(new LangGenerator(generator, "en_us"));
//        generator.addProvider(new BlockTagGenerator(generator, event.getExistingFileHelper()));
//        generator.addProvider(new RecipeGenerator(generator));
//        generator.addProvider(new LootTableGenerator(generator));
    }
}
