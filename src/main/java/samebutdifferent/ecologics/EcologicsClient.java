package samebutdifferent.ecologics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
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
import samebutdifferent.ecologics.client.renderer.entity.CamelRenderer;
import samebutdifferent.ecologics.client.renderer.entity.CoconutCrabRenderer;
import samebutdifferent.ecologics.client.renderer.entity.ModBoatRenderer;
import samebutdifferent.ecologics.client.renderer.entity.PenguinRenderer;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Set RenderLayer's for Blocks
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_HUSK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SANDCASTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COCONUT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THIN_ICE, RenderLayer.getTranslucent());

        // Register sign types
        // TODO:

        // Register block colors
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), ModBlocks.COCONUT_LEAVES);

        // Register item colors
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return ColorProviderRegistry.BLOCK.get(blockState.getBlock()).getColor(blockState, null, null, tintIndex);
        }, ModBlocks.COCONUT_LEAVES);

        // Register entity renderers
        EntityRendererRegistry.register(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CAMEL, CamelRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PENGUIN, PenguinRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BOAT, ModBoatRenderer::new);
        // TODO: BlockEntityRendererRegistry.register(ModBlockEntityTypes.SIGN, SignBlockEntityRenderer::new);

        // Register layer definitions
        EntityModelLayerRegistry.registerModelLayer(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModBoatRenderer.COCONUT_LAYER_LOCATION, BoatEntityModel::getTexturedModelData);
    }
}
