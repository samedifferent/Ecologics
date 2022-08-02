package samebutdifferent.ecologics.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.client.renderer.entity.*;
import samebutdifferent.ecologics.platform.ClientPlatformHelper;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient {
    public static void init() {
        // Render Layers
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_SEEDLING, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_DOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_TRAPDOOR, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.SANDCASTLE, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.COCONUT_LEAVES, RenderType.cutoutMipped());
        ClientPlatformHelper.setRenderLayer(ModBlocks.POTTED_COCONUT_SEEDLING, RenderType.cutout());
        ClientPlatformHelper.setRenderLayer(ModBlocks.PRICKLY_PEAR, RenderType.cutout());
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

        // Entity Renderers
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.CAMEL, CamelRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.PENGUIN, PenguinRenderer::new);
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.BOAT, context -> new ModBoatRenderer<>(context, false));
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.CHEST_BOAT, context -> new ModBoatRenderer<>(context, true));
        ClientPlatformHelper.registerEntityRenderer(ModEntityTypes.SQUIRREL, SquirrelRenderer::new);

        // Block Entity Renderers
        ClientPlatformHelper.registerBlockEntityRenderer(ModBlockEntityTypes.SIGN, SignRenderer::new);
    }

    public static void addWoodTypes() {
        ClientPlatformHelper.addWoodType(ModWoodType.COCONUT);
        ClientPlatformHelper.addWoodType(ModWoodType.WALNUT);
        ClientPlatformHelper.addWoodType(ModWoodType.AZALEA);
        ClientPlatformHelper.addWoodType(ModWoodType.FLOWERING_AZALEA);
    }
}
