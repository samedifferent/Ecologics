package samebutdifferent.ecologics.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import samebutdifferent.ecologics.Ecologics;

@Environment(EnvType.CLIENT)
public class CamelModel<T extends AbstractChestedHorse> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "camel"), "main");
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

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition saddle = partdefinition.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(80, 105).addBox(-5.0F, -13.0F, -1.5F, 10.0F, 9.0F, 14.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 5.0F, -4.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 37).addBox(-5.0F, -7.0F, -3.0F, 10.0F, 5.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -2.0F, -10.0F, 10.0F, 11.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 16).addBox(-2.0F, -14.975F, -15.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -15.0F, -10.0F, 6.0F, 19.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(14, 55).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -12.0F));
        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(33, 37).addBox(-1.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -15.0F, -6.0F));
        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(0, 37).addBox(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -15.0F, -6.0F));
        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, 0.0F, -3.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 8.0F, 12.0F));
        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(0, 55).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 8.0F, 12.0F));
        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, 0.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 8.0F, -9.0F));
        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(0, 55).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 8.0F, -9.0F));
        PartDefinition leftChest = partdefinition.addOrReplaceChild("leftChest", CubeListBuilder.create().texOffs(46, 0).addBox(19.0F, 0.0F, 2.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.5F, -1.0F, -5.0F));
        PartDefinition rightChest = partdefinition.addOrReplaceChild("rightChest", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(19.0F, 0.0F, 2.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-26.5F, -1.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.3331F) * pLimbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.3331F + (float)Math.PI) * pLimbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.3331F + (float)Math.PI) * pLimbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.3331F) * pLimbSwingAmount;
        boolean showChest = !pEntity.isBaby() && pEntity.hasChest();
        this.rightChest.visible = showChest;
        this.leftChest.visible = showChest;
        boolean showSaddle = pEntity.isSaddled();
        this.saddle.visible = showSaddle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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
