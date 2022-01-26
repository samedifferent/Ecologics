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
    private final Pair<ResourceLocation, BoatModel> coconut;

    public ModBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        coconut = new Pair<>(new ResourceLocation(Ecologics.MOD_ID, "textures/entity/boat/coconut.png"), new BoatModel(context.bakeLayer(COCONUT_LAYER_LOCATION)));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        switch (((ModBoat)boat).getWoodType()) {
            case "coconut":
                return coconut;
            default:
                return coconut;
        }
    }
}
