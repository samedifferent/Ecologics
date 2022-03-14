package samebutdifferent.ecologics.client.renderer.entity;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;

public class ModBoatRenderer extends BoatEntityRenderer {
    public static final EntityModelLayer COCONUT_LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "boat/coconut"), "main");
    private final Pair<Identifier, BoatEntityModel> coconut;

    public ModBoatRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        coconut = new Pair<>(new Identifier(Ecologics.MOD_ID, "textures/entity/boat/coconut.png"), new BoatEntityModel(context.getPart(COCONUT_LAYER_LOCATION)));
    }

    /* TODO:
    @Override
    public Pair<Identifier, BoatEntityModel> getModelWithLocation(BoatEntity boat) {
        switch (((ModBoat)boat).getWoodType()) {
            case "coconut":
                return coconut;
            default:
                return coconut;
        }
    }*/
}
