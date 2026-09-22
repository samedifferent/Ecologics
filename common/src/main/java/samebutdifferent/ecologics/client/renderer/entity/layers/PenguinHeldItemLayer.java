package samebutdifferent.ecologics.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.renderer.entity.state.PenguinRenderState;

public class PenguinHeldItemLayer extends RenderLayer<PenguinRenderState, PenguinModel> 
{
    public PenguinHeldItemLayer(RenderLayerParent<PenguinRenderState, PenguinModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, PenguinRenderState penguin, float yRot, float xRot) {
    	poseStack.pushPose();
        this.getParentModel().head.translateAndRotate(poseStack);
        poseStack.translate(0.1f, -0.05f, -0.2f);
        poseStack.rotateDegrees(Axis.XP, 90F);
        poseStack.rotateDegrees(Axis.ZP, 135F);
        ItemStackRenderState itemStackRenderState = penguin.heldItem;
        itemStackRenderState.submit(poseStack, nodeCollector, packedLight, OverlayTexture.NO_OVERLAY, penguin.outlineColor);
        poseStack.popPose();
    }
}
