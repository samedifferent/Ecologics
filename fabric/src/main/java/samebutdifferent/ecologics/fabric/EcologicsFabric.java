package samebutdifferent.ecologics.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import samebutdifferent.ecologics.Ecologics;
import net.fabricmc.api.ModInitializer;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

public class EcologicsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfigFabric.class, GsonConfigSerializer::new);
        Ecologics.init();
        Ecologics.commonSetup();
    }
}