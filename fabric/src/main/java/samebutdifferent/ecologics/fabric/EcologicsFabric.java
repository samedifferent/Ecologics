package samebutdifferent.ecologics.fabric;

import samebutdifferent.ecologics.Ecologics;
import net.fabricmc.api.ModInitializer;

public class EcologicsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Ecologics.init();
    }
}