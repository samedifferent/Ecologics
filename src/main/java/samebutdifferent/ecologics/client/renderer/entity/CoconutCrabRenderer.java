package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.entity.CoconutCrab;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CoconutCrabRenderer extends GeoEntityRenderer<CoconutCrab> {

    public CoconutCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CoconutCrabModel());
        this.shadowRadius = 0.6F;
    }
}
