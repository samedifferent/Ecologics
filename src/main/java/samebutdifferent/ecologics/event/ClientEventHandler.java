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
import samebutdifferent.ecologics.client.renderer.entity.CamelRenderer;
import samebutdifferent.ecologics.client.renderer.entity.CoconutCrabRenderer;
import samebutdifferent.ecologics.client.renderer.entity.ModBoatRenderer;
import samebutdifferent.ecologics.client.renderer.entity.PenguinRenderer;
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
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.COCONUT);
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
        event.registerBlockEntityRenderer(ModBlockEntityTypes.SIGN_BLOCK_ENTITY.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        event.registerLayerDefinition(ModBoatRenderer.COCONUT_LAYER_LOCATION, BoatModel::createBodyModel);
    }
}
