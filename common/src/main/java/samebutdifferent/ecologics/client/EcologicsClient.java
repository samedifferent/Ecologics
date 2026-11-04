package samebutdifferent.ecologics.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.client.renderer.entity.CoconutCrabRenderer;
import samebutdifferent.ecologics.client.renderer.entity.ModBoatRenderer;
import samebutdifferent.ecologics.client.renderer.entity.PenguinRenderer;
import samebutdifferent.ecologics.client.renderer.entity.SquirrelRenderer;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient 
{
	public static final Map<Block, RenderType> BLOCK_RENDERS = new HashMap<>();
	
    public static void init() {
        // Render Layers
        BLOCK_RENDERS.put(ModBlocks.AZALEA_DOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.AZALEA_TRAPDOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.FLOWERING_AZALEA_DOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.FLOWERING_AZALEA_TRAPDOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.AZALEA_FLOWER, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.POTTED_AZALEA_FLOWER, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.COCONUT_DOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.COCONUT_TRAPDOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.COCONUT_LEAVES, RenderType.cutoutMipped());
        BLOCK_RENDERS.put(ModBlocks.COCONUT_SEEDLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.POTTED_COCONUT_SEEDLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.WALNUT_DOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.WALNUT_TRAPDOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.WALNUT_LEAVES, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.WALNUT_SAPLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.POTTED_WALNUT_SAPLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.MAPLE_DOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.MAPLE_TRAPDOOR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.RED_MAPLE_LEAVES, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.ORANGE_MAPLE_LEAVES, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.YELLOW_MAPLE_LEAVES, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.GREEN_MAPLE_LEAVES, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.MAPLE_SAPLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.POTTED_MAPLE_SAPLING, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.SANDCASTLE, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.PRICKLY_PEAR, RenderType.cutout());
        BLOCK_RENDERS.put(ModBlocks.ICE_BRICKS, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.ICE_BRICK_STAIRS, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.ICE_BRICK_SLAB, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.ICE_BRICK_WALL, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.THIN_ICE, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.SURFACE_MOSS, RenderType.cutoutMipped());
        BLOCK_RENDERS.put(ModBlocks.SPILE, RenderType.translucent());
        BLOCK_RENDERS.put(ModBlocks.MAPLE_SAP_CAULDRON, RenderType.translucent());

        // Entity Renderers
        EntityRenderers.register(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
        EntityRenderers.register(ModEntityTypes.PENGUIN, PenguinRenderer::new);
        EntityRenderers.register(ModEntityTypes.SQUIRREL, SquirrelRenderer::new);
        EntityRenderers.register(ModEntityTypes.BOAT, context -> new ModBoatRenderer<>(context, false));
        EntityRenderers.register(ModEntityTypes.CHEST_BOAT, context -> new ModBoatRenderer<>(context, true));

        // Block Entity Renderers
        BlockEntityRenderers.register(ModBlockEntityTypes.SIGN, SignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_SIGN, HangingSignRenderer::new);
        
        addSignType(ModWoodType.COCONUT);
        addSignType(ModWoodType.MAPLE);
        addSignType(ModWoodType.WALNUT);
        addSignType(ModWoodType.AZALEA);
        addSignType(ModWoodType.FLOWERING_AZALEA);
    }
    
    private static void addSignType(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "entity/signs/" + woodType.name().replace(Ecologics.MOD_ID + ":", ""))));
    }
}
