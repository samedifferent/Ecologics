package samebutdifferent.ecologics.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.ShelfRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.client.renderer.entity.CoconutCrabRenderer;
import samebutdifferent.ecologics.client.renderer.entity.PenguinRenderer;
import samebutdifferent.ecologics.client.renderer.entity.SquirrelRenderer;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;
import samebutdifferent.ecologics.registry.ModEntityTypes;

public class EcologicsClient {
    public static final ModelLayerLocation AZALEA_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "boat/azalea"), "main");
    public static final ModelLayerLocation AZALEA_CHEST_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "chest_boat/azalea"), "main");
    public static final ModelLayerLocation FLOWERING_AZALEA_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "boat/flowering_azalea"), "main");
    public static final ModelLayerLocation FLOWERING_AZALEA_CHEST_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "chest_boat/flowering_azalea"), "main");
    public static final ModelLayerLocation COCONUT_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "boat/coconut"), "main");
    public static final ModelLayerLocation COCONUT_CHEST_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "chest_boat/coconut"), "main");
    public static final ModelLayerLocation WALNUT_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "boat/walnut"), "main");
    public static final ModelLayerLocation WALNUT_CHEST_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "chest_boat/walnut"), "main");
	
    public static void init() {
        // Entity Renderers
    	EntityRenderers.register(ModEntityTypes.COCONUT_CRAB, CoconutCrabRenderer::new);
    	EntityRenderers.register(ModEntityTypes.PENGUIN, PenguinRenderer::new);
    	EntityRenderers.register(ModEntityTypes.SQUIRREL, SquirrelRenderer::new);

    	EntityRenderers.register(ModEntityTypes.AZALEA_BOAT, context -> new BoatRenderer(context, AZALEA_BOAT));
    	EntityRenderers.register(ModEntityTypes.AZALEA_CHEST_BOAT, context -> new BoatRenderer(context, AZALEA_CHEST_BOAT));
    	EntityRenderers.register(ModEntityTypes.FLOWERING_AZALEA_BOAT, context -> new BoatRenderer(context, FLOWERING_AZALEA_BOAT));
    	EntityRenderers.register(ModEntityTypes.FLOWERING_AZALEA_CHEST_BOAT, context -> new BoatRenderer(context, FLOWERING_AZALEA_CHEST_BOAT));
    	EntityRenderers.register(ModEntityTypes.COCONUT_BOAT, context -> new BoatRenderer(context, COCONUT_BOAT));
    	EntityRenderers.register(ModEntityTypes.COCONUT_CHEST_BOAT, context -> new BoatRenderer(context, COCONUT_CHEST_BOAT));
    	EntityRenderers.register(ModEntityTypes.WALNUT_BOAT, context -> new BoatRenderer(context, WALNUT_BOAT));
    	EntityRenderers.register(ModEntityTypes.WALNUT_CHEST_BOAT, context -> new BoatRenderer(context, WALNUT_CHEST_BOAT));

        // Block Entity Renderers
        BlockEntityRenderers.register(ModBlockEntityTypes.SIGN, StandingSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_SIGN, HangingSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.SHELF, ShelfRenderer::new);
    }
    
    public static void addSignTypes() {
        addSignType(ModWoodType.COCONUT);
        addSignType(ModWoodType.WALNUT);
        addSignType(ModWoodType.AZALEA);
        addSignType(ModWoodType.FLOWERING_AZALEA);
    }
    
    private static void addSignType(WoodType woodType) {
    	Sheets.SIGN_SPRITES.put(woodType, new SpriteId(Sheets.SIGN_SHEET, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "entity/signs/" + woodType.name().replace(Ecologics.MOD_ID + ":", ""))));
	}
}
