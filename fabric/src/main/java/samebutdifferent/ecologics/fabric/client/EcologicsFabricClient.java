package samebutdifferent.ecologics.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import samebutdifferent.ecologics.client.EcologicsClient;

public class EcologicsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EcologicsClient.init();
    }
}
