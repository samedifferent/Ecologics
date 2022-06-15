package samebutdifferent.ecologics.client.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EcologicsForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EcologicsClient.init();
        event.enqueueWork(() -> {
            EcologicsClient.addWoodTypes();
        });
    }
}
