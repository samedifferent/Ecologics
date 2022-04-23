package samebutdifferent.ecologics.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.item.BlockItem;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.renderer.entity.*;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        // Block render layers
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_HUSK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SANDCASTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_COCONUT_HUSK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THIN_ICE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALNUT_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALNUT_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_WALNUT_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLOWERING_AZALEA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLOWERING_AZALEA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_AZALEA_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SURFACE_MOSS, RenderLayer.getCutoutMipped());


        // Entity renderers
        EntityRendererRegistry.register(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CAMEL, CamelRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PENGUIN, PenguinRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BOAT, ModBoatRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SQUIRREL, SquirrelRenderer::new);

        // Render layer definitions
        EntityModelLayerRegistry.registerModelLayer(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModBoatRenderer.COCONUT_LAYER_LOCATION, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModBoatRenderer.WALNUT_LAYER_LOCATION, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModBoatRenderer.AZALEA_LAYER_LOCATION, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModBoatRenderer.FLOWERING_AZALEA_LAYER_LOCATION, BoatEntityModel::getTexturedModelData);

        // Register block/item colors
        ColorProviderRegistry.BLOCK.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getFoliageColor(pLevel, pPos) : FoliageColors.getDefaultColor(), ModBlocks.COCONUT_LEAVES);
        ColorProviderRegistry.ITEM.register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().getDefaultState();
            return ColorProviderRegistry.BLOCK.get(blockstate.getBlock()).getColor(blockstate, null, null, pTintIndex);
        }, ModBlocks.COCONUT_LEAVES);
    }
}
