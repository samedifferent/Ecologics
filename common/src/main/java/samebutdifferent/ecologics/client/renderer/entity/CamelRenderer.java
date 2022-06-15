package samebutdifferent.ecologics.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.entity.Camel;

@Environment(EnvType.CLIENT)
public class CamelRenderer extends MobRenderer<Camel, CamelModel<Camel>> {
    public CamelRenderer(EntityRendererProvider.Context context) {
        super(context, new CamelModel<>(context.bakeLayer(CamelModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Camel pEntity) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/camel.png");
    }

    @Override
    public void render(Camel pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
