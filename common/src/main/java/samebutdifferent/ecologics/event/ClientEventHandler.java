package samebutdifferent.ecologics.event;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.renderer.entity.*;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class ClientEventHandler {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COCONUT_HUSK.get(), RenderType.cutout());
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.COCONUT);
            Sheets.addWoodType(ModWoodType.WALNUT);
            Sheets.addWoodType(ModWoodType.AZALEA);
            Sheets.addWoodType(ModWoodType.FLOWERING_AZALEA);
        });
    }

    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES.get());
        QuarkCompatClient.registerBlockColors(event);
    }

    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, ModBlocks.COCONUT_LEAVES.get());
        QuarkCompatClient.registerItemColors(event);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        QuarkCompatClient.registerRenderers(event);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        event.registerLayerDefinition(ModBoatRenderer.COCONUT_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.WALNUT_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.AZALEA_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.FLOWERING_AZALEA_LAYER_LOCATION, BoatModel::createBodyModel);
    }

    public static void stitchTextures(TextureStitchEvent.Pre event) {
        QuarkCompatClient.stitchTextures(event);
    }
}
