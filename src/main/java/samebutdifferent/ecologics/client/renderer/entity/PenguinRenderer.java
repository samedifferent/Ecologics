package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.renderer.entity.layers.PenguinCodItemLayer;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class PenguinRenderer extends GeoEntityRenderer<Penguin> {

    public PenguinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PenguinModel());
        this.shadowRadius = 0.4F;
        this.addLayer(new PenguinCodItemLayer(this));
    }
}
