package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.Squirrel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class SquirrelRenderer extends GeoMobRenderer<Squirrel> {

    public SquirrelRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SquirrelModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void render(Squirrel entity, float entityYaw, float partialTicks, MatrixStack stack, VertexConsumerProvider bufferIn, int packedLightIn) {
        if (entity.isBaby()) {
            stack.scale(0.6F, 0.6F, 0.6F);
        }
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}