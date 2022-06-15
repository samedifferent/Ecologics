package samebutdifferent.ecologics.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import samebutdifferent.ecologics.client.EcologicsClient;

@Environment(EnvType.CLIENT)
public class EcologicsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EcologicsClient.init();
        EcologicsClient.addWoodTypes();
    }
}
