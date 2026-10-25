package samebutdifferent.ecologics.neoforge.client;

import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.MapleSapCauldronTintSource;
import samebutdifferent.ecologics.client.MapleSapParticle;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModParticleTypes;

@EventBusSubscriber(modid = Ecologics.MOD_ID, value = Dist.CLIENT)
public class EcologicsNeoForgeClient 
{
    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EcologicsClient.init();
        event.enqueueWork(() -> {
            EcologicsClient.addSignTypes();
            EcologicsClient.BLOCK_RENDERS.forEach((block, csl) -> {
            	ItemBlockRenderTypes.setRenderLayer(block, csl);
            });
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.FOLIAGE_DEFAULT, ModBlocks.COCONUT_LEAVES);
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.FOLIAGE_DEFAULT, ModBlocks.GREEN_MAPLE_LEAVES);
        event.register(new MapleSapCauldronTintSource(), ModBlocks.MAPLE_SAP_CAULDRON);
        event.register((state, level, pos, tintIndex) -> { return 0xC0FFD6AD; }, ModBlocks.SPILE);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.DRIPPING_MAPLE_SAP, MapleSapParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.FALLING_MAPLE_SAP, MapleSapParticle.Provider::new);
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
        event.registerLayerDefinition(EcologicsClient.MAPLE_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.MAPLE_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(EcologicsClient.WALNUT_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(EcologicsClient.WALNUT_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}
