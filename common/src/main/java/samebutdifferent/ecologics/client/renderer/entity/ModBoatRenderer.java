package samebutdifferent.ecologics.client.renderer.entity;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;

public class ModBoatRenderer extends BoatRenderer {
    public static final ModelLayerLocation COCONUT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "boat/coconut"), "main");
    public static final ModelLayerLocation WALNUT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "boat/walnut"), "main");
    public static final ModelLayerLocation AZALEA_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "boat/azalea"), "main");
    public static final ModelLayerLocation FLOWERING_AZALEA_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "boat/flowering_azalea"), "main");

    private final Pair<ResourceLocation, BoatModel> coconut;
    private final Pair<ResourceLocation, BoatModel> walnut;
    private final Pair<ResourceLocation, BoatModel> azalea;
    private final Pair<ResourceLocation, BoatModel> flowering_azalea;

    public ModBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        coconut = new Pair<>(new ResourceLocation(Ecologics.MOD_ID, "textures/entity/boat/coconut.png"), new BoatModel(context.bakeLayer(COCONUT_LAYER_LOCATION)));
        walnut = new Pair<>(new ResourceLocation(Ecologics.MOD_ID, "textures/entity/boat/walnut.png"), new BoatModel(context.bakeLayer(WALNUT_LAYER_LOCATION)));
        azalea = new Pair<>(new ResourceLocation(Ecologics.MOD_ID, "textures/entity/boat/azalea.png"), new BoatModel(context.bakeLayer(AZALEA_LAYER_LOCATION)));
        flowering_azalea = new Pair<>(new ResourceLocation(Ecologics.MOD_ID, "textures/entity/boat/flowering_azalea.png"), new BoatModel(context.bakeLayer(FLOWERING_AZALEA_LAYER_LOCATION)));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        switch (((ModBoat)boat).getWoodType()) {
            case "walnut":
                return walnut;
            case "azalea":
                return azalea;
            case "flowering_azalea":
                return flowering_azalea;
            default:
                return coconut;
        }
    }
}
