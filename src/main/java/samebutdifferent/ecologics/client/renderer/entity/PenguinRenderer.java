package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class PenguinRenderer extends GeoEntityRenderer<Penguin> {

    public PenguinRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PenguinModel());
        this.shadowRadius = 0.4F;
    }
}
