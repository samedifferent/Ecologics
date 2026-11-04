package samebutdifferent.ecologics.neoforge.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
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
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.neoforge.integration.vanillabackport.VanillaBackportClient;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModParticleTypes;

@EventBusSubscriber(modid = Ecologics.MOD_ID, value = Dist.CLIENT)
public class EcologicsNeoForgeClient 
{
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(EcologicsClient::init);
        event.enqueueWork(() -> { 
            EcologicsClient.BLOCK_RENDERS.forEach((block, csl) -> { ItemBlockRenderTypes.setRenderLayer(block, csl); });
            if (ModList.get().isLoaded("vanillabackport")) {
                VanillaBackportClient.registerRenderers();
            }
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES);
        event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.GREEN_MAPLE_LEAVES);
        event.register(new MapleSapCauldronTintSource(), ModBlocks.MAPLE_SAP_CAULDRON);
        event.register((state, level, pos, tintIndex) -> { return 0xC0FFD6AD; }, ModBlocks.SPILE);
        if (ModList.get().isLoaded("biomemoss")) {
            event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor(), ModBlocks.MOSS_LAYER, ModBlocks.SURFACE_MOSS);
        }
        else {
            event.register((state, level, pos, tintIndex) -> 0x70922D, ModBlocks.SURFACE_MOSS);            
        }
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((pStack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
        }, ModBlocks.COCONUT_LEAVES);
        event.register((pStack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
        }, ModBlocks.GREEN_MAPLE_LEAVES);
        if (ModList.get().isLoaded("biomemoss")) {
            event.register((pStack, tintIndex) -> GrassColor.get(0.8D, 0.4D), ModBlocks.MOSS_LAYER);
        }
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.DRIPPING_MAPLE_SAP, MapleSapParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.FALLING_MAPLE_SAP, MapleSapParticle.Provider::new);
    }
    
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CoconutCrabModel.LAYER_LOCATION, CoconutCrabModel::createBodyLayer);
        event.registerLayerDefinition(SquirrelModel.LAYER_LOCATION, SquirrelModel::createBodyLayer);
        event.registerLayerDefinition(PenguinModel.LAYER_LOCATION, PenguinModel::createBodyLayer);
        for (ModBoat.Type type : ModBoat.Type.values()) {
            event.registerLayerDefinition(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            event.registerLayerDefinition(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }
    }
}
