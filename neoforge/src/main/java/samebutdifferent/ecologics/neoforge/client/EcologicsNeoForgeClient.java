package samebutdifferent.ecologics.neoforge.client;

import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.object.boat.BoatModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.List;

@EventBusSubscriber(modid = Ecologics.MOD_ID, value = Dist.CLIENT)
public class EcologicsNeoForgeClient 
{
	@SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EcologicsClient.init();
        event.enqueueWork(EcologicsClient::addSignTypes);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.foliage()), ModBlocks.COCONUT_LEAVES);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CoconutCrabModel.COCONUT_CRAB, CoconutCrabModel::createBodyLayer);
        event.registerLayerDefinition(CoconutCrabModel.COCONUT_CRAB_BABY, CoconutCrabModel::createBaby);
        event.registerLayerDefinition(SquirrelModel.SQUIRREL, SquirrelModel::createBodyLayer);
        event.registerLayerDefinition(SquirrelModel.SQUIRREL_BABY, SquirrelModel::createBaby);
        event.registerLayerDefinition(PenguinModel.PENGUIN, PenguinModel::createBodyLayer);
        event.registerLayerDefinition(PenguinModel.PENGUIN_BABY, PenguinModel::createBaby);
        
        event.registerLayerDefinition(EcologicsClient.AZALEA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.AZALEA_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(EcologicsClient.FLOWERING_AZALEA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.FLOWERING_AZALEA_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(EcologicsClient.COCONUT_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.COCONUT_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(EcologicsClient.WALNUT_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.WALNUT_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}
