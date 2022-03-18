package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.entity.Camel;

@Environment(EnvType.CLIENT)
public class CamelRenderer extends MobEntityRenderer<Camel, CamelModel<Camel>> {
    public CamelRenderer(EntityRendererFactory.Context context) {
        super(context, new CamelModel<>(context.getPart(CamelModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public Identifier getTexture(Camel pEntity) {
        return new Identifier(Ecologics.MOD_ID, "textures/entity/camel.png");
    }

    @Override
    public void render(Camel pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, VertexConsumerProvider pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
