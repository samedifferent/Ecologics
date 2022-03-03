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
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class PenguinCodItemLayer extends GeoLayerRenderer<Penguin> {
    public PenguinCodItemLayer(IGeoRenderer<Penguin> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Penguin pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(pNetHeadYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(pHeadPitch));
        pMatrixStack.translate(0.0F, 0.6F, -0.35D);
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        ItemStack stack = pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(pLivingEntity, stack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }
}
