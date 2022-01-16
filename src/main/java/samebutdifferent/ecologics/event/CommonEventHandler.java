package samebutdifferent.ecologics.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import samebutdifferent.ecologics.Ecologics;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
    }
}
