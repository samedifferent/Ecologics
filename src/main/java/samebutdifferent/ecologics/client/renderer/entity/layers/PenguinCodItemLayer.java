package samebutdifferent.ecologics.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class PenguinCodItemLayer extends GeoLayerRenderer<Penguin> {
    public PenguinCodItemLayer(IGeoRenderer<Penguin> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Penguin pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        GeoBone head = this.getEntityModel().getModel(this.getEntityModel().getModelLocation(pLivingEntity)).getBone("head").get();
        pMatrixStack.pushPose();
        pMatrixStack.translate((head.getPositionX() / 16.0F), (head.getPositionY() / 16.0F), (head.getPositionZ() / 16.0F));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(pNetHeadYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(pHeadPitch));
        pMatrixStack.translate(0.0F, 0.6F, -0.1D);
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        ItemStack stack = pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(pLivingEntity, stack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }
}
