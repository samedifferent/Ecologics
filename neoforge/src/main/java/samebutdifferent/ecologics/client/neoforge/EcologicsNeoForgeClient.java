package samebutdifferent.ecologics.client.neoforge;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
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
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.registry.ModBlocks;

@EventBusSubscriber(modid = Ecologics.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EcologicsNeoForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EcologicsClient.init();
        event.enqueueWork(() -> {
            EcologicsClient.addSignTypes();
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES);
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
        if (ModList.get().isLoaded("biomemoss")) {
            event.register((pStack, tintIndex) -> GrassColor.get(0.8D, 0.4D), ModBlocks.MOSS_LAYER);
        }

    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        event.registerLayerDefinition(CoconutCrabModel.LAYER_LOCATION, CoconutCrabModel::createBodyLayer);
        event.registerLayerDefinition(SquirrelModel.LAYER_LOCATION, SquirrelModel::createBodyLayer);
        event.registerLayerDefinition(PenguinModel.LAYER_LOCATION, PenguinModel::createBodyLayer);
        for (ModBoat.Type type : ModBoat.Type.values()) {
            event.registerLayerDefinition(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            event.registerLayerDefinition(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }
    }
}
