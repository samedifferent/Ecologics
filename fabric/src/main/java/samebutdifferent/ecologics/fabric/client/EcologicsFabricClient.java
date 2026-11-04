package samebutdifferent.ecologics.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.MapleSapCauldronTintSource;
import samebutdifferent.ecologics.client.MapleSapParticle;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.fabric.integration.vanillabackport.VanillaBackportClient;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModParticleTypes;

public class EcologicsFabricClient implements ClientModInitializer 
{
	@Override @SuppressWarnings("unused")
    public void onInitializeClient() {
        EcologicsClient.init();

        EntityModelLayerRegistry.registerModelLayer(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CoconutCrabModel.LAYER_LOCATION, CoconutCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SquirrelModel.LAYER_LOCATION, SquirrelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.LAYER_LOCATION, PenguinModel::createBodyLayer);
        for (ModBoat.Type type : ModBoat.Type.values()) {
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }

        EcologicsClient.BLOCK_RENDERS.forEach((block, csl) -> { BlockRenderLayerMap.INSTANCE.putBlock(block, csl); });
        
        ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.GREEN_MAPLE_LEAVES);
        ColorProviderRegistry.BLOCK.register(new MapleSapCauldronTintSource(), ModBlocks.MAPLE_SAP_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> { return 0xC0FFD6AD; }, ModBlocks.SPILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), ModBlocks.GREEN_MAPLE_LEAVES);
        if (FabricLoader.getInstance().isModLoaded("biomemoss")) {
            ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor(), ModBlocks.MOSS_LAYER, ModBlocks.SURFACE_MOSS);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.8D, 0.4D), ModBlocks.MOSS_LAYER);
        }
        else {
        	ColorProviderRegistry.BLOCK.register((state, level, pos, tintIndex) -> 0x70922D, ModBlocks.SURFACE_MOSS);        	
        }
        
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DRIPPING_MAPLE_SAP, MapleSapParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FALLING_MAPLE_SAP, MapleSapParticle.Provider::new);
        if (FabricLoader.getInstance().isModLoaded("vanillabackport")) {
        	VanillaBackportClient.registerRenderers();
        }
    }
}
