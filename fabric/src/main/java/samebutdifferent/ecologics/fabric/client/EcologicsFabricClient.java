package samebutdifferent.ecologics.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.object.boat.BoatModel;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.List;

public class EcologicsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EcologicsClient.init();

        ModelLayerRegistry.registerModelLayer(CoconutCrabModel.COCONUT_CRAB, CoconutCrabModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(CoconutCrabModel.COCONUT_CRAB_BABY, CoconutCrabModel::createBaby);
        ModelLayerRegistry.registerModelLayer(SquirrelModel.SQUIRREL, SquirrelModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(SquirrelModel.SQUIRREL_BABY, SquirrelModel::createBaby);
        ModelLayerRegistry.registerModelLayer(PenguinModel.PENGUIN, PenguinModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PenguinModel.PENGUIN_BABY, PenguinModel::createBaby);

        ModelLayerRegistry.registerModelLayer(EcologicsClient.AZALEA_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.AZALEA_CHEST_BOAT, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.FLOWERING_AZALEA_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.FLOWERING_AZALEA_CHEST_BOAT, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.COCONUT_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.COCONUT_CHEST_BOAT, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.WALNUT_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(EcologicsClient.WALNUT_CHEST_BOAT, BoatModel::createChestBoatModel);

        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), ModBlocks.COCONUT_LEAVES);
    }
}
