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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.renderer.entity.*;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COCONUT_HUSK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COCONUT_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COCONUT_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SANDCASTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COCONUT_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_COCONUT_HUSK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.THIN_ICE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALNUT_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALNUT_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_WALNUT_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AZALEA_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AZALEA_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLOWERING_AZALEA_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AZALEA_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLOWERING_AZALEA_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_AZALEA_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SURFACE_MOSS.get(), RenderType.cutoutMipped());
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.COCONUT);
            Sheets.addWoodType(ModWoodType.WALNUT);
            Sheets.addWoodType(ModWoodType.AZALEA);
            Sheets.addWoodType(ModWoodType.FLOWERING_AZALEA);
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, ModBlocks.COCONUT_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.COCONUT_CRAB.get(), CoconutCrabRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CAMEL.get(), CamelRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.BOAT.get(), ModBoatRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.SIGN.get(), SignRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SQUIRREL.get(), SquirrelRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        event.registerLayerDefinition(ModBoatRenderer.COCONUT_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.WALNUT_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.AZALEA_LAYER_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModBoatRenderer.FLOWERING_AZALEA_LAYER_LOCATION, BoatModel::createBodyModel);
    }
}
