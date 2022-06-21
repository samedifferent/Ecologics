package samebutdifferent.ecologics.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.entity.Penguin;

@Environment(EnvType.CLIENT)
public class PenguinHeldItemLayer extends RenderLayer<Penguin, PenguinModel> {
    private final ItemInHandRenderer itemInHandRenderer;

    public PenguinHeldItemLayer(RenderLayerParent<Penguin, PenguinModel> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, Penguin livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStack.pushPose();
        this.getParentModel().head.translateAndRotate(matrixStack);
        matrixStack.translate(0.1f, -0.05f, -0.2f);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90f));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(135f));
        ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        this.itemInHandRenderer.renderItem(livingEntity, itemStack, ItemTransforms.TransformType.GROUND, false, matrixStack, buffer, packedLight);
        matrixStack.popPose();
    }
}
