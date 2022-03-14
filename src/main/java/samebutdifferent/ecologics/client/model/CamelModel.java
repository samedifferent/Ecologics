package samebutdifferent.ecologics.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import samebutdifferent.ecologics.Ecologics;

@Environment(EnvType.CLIENT)
public class CamelModel<T extends AbstractDonkeyEntity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "camel"), "main");
    private final ModelPart saddle;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftChest;
    private final ModelPart rightChest;

    public CamelModel(ModelPart root) {
        this.saddle = root.getChild("saddle");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.rightHindLeg = root.getChild("rightHindLeg");
        this.leftHindLeg = root.getChild("leftHindLeg");
        this.rightFrontLeg = root.getChild("rightFrontLeg");
        this.leftFrontLeg = root.getChild("leftFrontLeg");
        this.leftChest = root.getChild("leftChest");
        this.rightChest = root.getChild("rightChest");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData saddle = partdefinition.addChild("saddle", ModelPartBuilder.create().uv(80, 105).cuboid(-5.0F, -13.0F, -1.5F, 10.0F, 9.0F, 14.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 5.0F, -4.0F));
        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 37).cuboid(-5.0F, -7.0F, -3.0F, 10.0F, 5.0F, 13.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-5.0F, -2.0F, -10.0F, 10.0F, 11.0F, 26.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -2.0F));
        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(46, 16).cuboid(-2.0F, -14.975F, -15.0F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -15.0F, -10.0F, 6.0F, 19.0F, 7.0F, new Dilation(0.0F))
                .uv(14, 55).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -12.0F));
        ModelPartData leftEar = head.addChild("leftEar", ModelPartBuilder.create().uv(33, 37).cuboid(-1.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, -15.0F, -6.0F));
        ModelPartData rightEar = head.addChild("rightEar", ModelPartBuilder.create().uv(0, 37).cuboid(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -15.0F, -6.0F));
        ModelPartData rightHindLeg = partdefinition.addChild("rightHindLeg", ModelPartBuilder.create().uv(0, 55).mirrored().cuboid(0.0F, 0.0F, -3.0F, 3.0F, 16.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.5F, 8.0F, 12.0F));
        ModelPartData leftHindLeg = partdefinition.addChild("leftHindLeg", ModelPartBuilder.create().uv(0, 55).cuboid(-3.0F, 0.0F, -3.0F, 3.0F, 16.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 8.0F, 12.0F));
        ModelPartData rightFrontLeg = partdefinition.addChild("rightFrontLeg", ModelPartBuilder.create().uv(0, 55).mirrored().cuboid(0.0F, 0.0F, -2.0F, 3.0F, 16.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.5F, 8.0F, -9.0F));
        ModelPartData leftFrontLeg = partdefinition.addChild("leftFrontLeg", ModelPartBuilder.create().uv(0, 55).cuboid(-3.0F, 0.0F, -2.0F, 3.0F, 16.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 8.0F, -9.0F));
        ModelPartData leftChest = partdefinition.addChild("leftChest", ModelPartBuilder.create().uv(46, 0).cuboid(19.0F, 0.0F, 2.0F, 3.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-14.5F, -1.0F, -5.0F));
        ModelPartData rightChest = partdefinition.addChild("rightChest", ModelPartBuilder.create().uv(46, 0).mirrored().cuboid(19.0F, 0.0F, 2.0F, 3.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-26.5F, -1.0F, -5.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.head.pitch = pHeadPitch * ((float)Math.PI / 180F);
        this.head.yaw = pNetHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.pitch = MathHelper.cos(pLimbSwing * 0.3331F) * pLimbSwingAmount;
        this.leftHindLeg.pitch = MathHelper.cos(pLimbSwing * 0.3331F + (float)Math.PI) * pLimbSwingAmount;
        this.rightFrontLeg.pitch = MathHelper.cos(pLimbSwing * 0.3331F + (float)Math.PI) * pLimbSwingAmount;
        this.leftFrontLeg.pitch = MathHelper.cos(pLimbSwing * 0.3331F) * pLimbSwingAmount;
        boolean showChest = !pEntity.isBaby() && pEntity.hasChest();
        this.rightChest.visible = showChest;
        this.leftChest.visible = showChest;
        this.saddle.visible = pEntity.isSaddled();
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        saddle.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        rightHindLeg.render(poseStack, buffer, packedLight, packedOverlay);
        leftHindLeg.render(poseStack, buffer, packedLight, packedOverlay);
        rightFrontLeg.render(poseStack, buffer, packedLight, packedOverlay);
        leftFrontLeg.render(poseStack, buffer, packedLight, packedOverlay);
        leftChest.render(poseStack, buffer, packedLight, packedOverlay);
        rightChest.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
