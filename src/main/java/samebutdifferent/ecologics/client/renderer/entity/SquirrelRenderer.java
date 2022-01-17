package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.Squirrel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class SquirrelRenderer extends GeoEntityRenderer<Squirrel> {

    public SquirrelRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SquirrelModel());
        this.shadowRadius = 0.6F;
    }
}
