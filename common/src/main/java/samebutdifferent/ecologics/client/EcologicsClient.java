package samebutdifferent.ecologics.client;

import net.minecraft.client.renderer.RenderType;
import samebutdifferent.ecologics.client.renderer.entity.CamelRenderer;
import samebutdifferent.ecologics.client.renderer.entity.CoconutCrabRenderer;
import samebutdifferent.ecologics.client.renderer.entity.PenguinRenderer;
import samebutdifferent.ecologics.client.renderer.entity.SquirrelRenderer;
import samebutdifferent.ecologics.platform.ClientPlatformHelper;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient {
    public static void init() {
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_HUSK, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_DOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_TRAPDOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.SANDCASTLE, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_LEAVES, RenderType.cutoutMipped());
        ClientPlatformHelper.setRenderLayer(ModBlocks.POTTED_COCONUT_HUSK, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.THIN_ICE, RenderType.translucent());
        ClientPlatformHelper.setRenderLayer(ModBlocks.WALNUT_SAPLING, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.WALNUT_DOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.POTTED_WALNUT_SAPLING, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.AZALEA_FLOWER, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.AZALEA_DOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.FLOWERING_AZALEA_DOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.AZALEA_TRAPDOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.FLOWERING_AZALEA_TRAPDOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.POTTED_AZALEA_FLOWER, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.SURFACE_MOSS, RenderType.cutoutMipped());

        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.CAMEL, CamelRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.PENGUIN, PenguinRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.SQUIRREL, SquirrelRenderer::new);
//        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.BOAT, ModBoatRenderer::new);
//        ClientPlatformHelper.registerBlockEntityRenderer(ModBlockEntityTypes.SIGN, SignRenderer::new);
    }
}