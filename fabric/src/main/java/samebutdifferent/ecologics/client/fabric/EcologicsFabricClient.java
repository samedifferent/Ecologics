package samebutdifferent.ecologics.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.registry.ModBlocks;

public class EcologicsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EcologicsClient.init();

        EcologicsClient.addWoodTypes();

        EntityModelLayerRegistry.registerModelLayer(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CoconutCrabModel.LAYER_LOCATION, CoconutCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SquirrelModel.LAYER_LOCATION, SquirrelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.LAYER_LOCATION, PenguinModel::createBodyLayer);
        for (ModBoat.Type type : ModBoat.Type.values()) {
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, type.getModelLocation()), "main"), () -> BoatModel.createBodyModel(false));
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), () -> BoatModel.createBodyModel(true));
        }

        ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES.get());
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES.get());
    }
}
