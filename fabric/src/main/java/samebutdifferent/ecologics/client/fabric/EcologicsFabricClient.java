package samebutdifferent.ecologics.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.registry.ModBlocks;

public class EcologicsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EcologicsClient.init();

        EcologicsClient.addSignTypes();

        EntityModelLayerRegistry.registerModelLayer(CoconutCrabModel.COCONUT_CRAB, CoconutCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CoconutCrabModel.COCONUT_CRAB_BABY, CoconutCrabModel::createBaby);
        EntityModelLayerRegistry.registerModelLayer(SquirrelModel.SQUIRREL, SquirrelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SquirrelModel.SQUIRREL_BABY, SquirrelModel::createBaby);
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.PENGUIN, PenguinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.PENGUIN_BABY, PenguinModel::createBaby);
        EcologicsClient.BLOCK_RENDERS.forEach((block, csl) -> {
        	BlockRenderLayerMap.putBlock(block, csl);
        });
        /*for (ModBoat.Type type : ModBoat.Type.values()) {
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }*/

        ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.FOLIAGE_DEFAULT, ModBlocks.COCONUT_LEAVES);
    }
}
