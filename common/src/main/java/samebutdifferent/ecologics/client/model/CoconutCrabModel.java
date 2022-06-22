package samebutdifferent.ecologics.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.util.AnimationUtil;

@Environment(EnvType.CLIENT)
public class CoconutCrabModel extends HierarchicalModel<CoconutCrab> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "coconut_crab"), "main");
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart shell;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftMiddleLeg;
    private final ModelPart rightMiddleLeg;
    private final ModelPart rightClaw;
    private final ModelPart leftClaw;

    public CoconutCrabModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.shell = this.root.getChild("shell");
        this.leftHindLeg = this.root.getChild("leftHindLeg");
        this.rightHindLeg = this.root.getChild("rightHindLeg");
        this.leftFrontLeg = this.root.getChild("leftFrontLeg");
        this.rightFrontLeg = this.root.getChild("rightFrontLeg");
        this.leftMiddleLeg = this.root.getChild("leftMiddleLeg");
        this.rightMiddleLeg = this.root.getChild("rightMiddleLeg");
        this.rightClaw = this.root.getChild("rightClaw");
        this.leftClaw = this.root.getChild("leftClaw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -9.0F, -4.0F, 7.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition shell = root.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.4F, 6.0F, -0.7854F, 0.0F, 0.0F));
        PartDefinition leftClaw = root.addOrReplaceChild("leftClaw", CubeListBuilder.create(), PartPose.offset(3.2277F, -4.1522F, -5.5809F));
        PartDefinition cube_r1 = leftClaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(31, 36).addBox(-3.0F, -4.0F, -3.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2723F, 2.1522F, 0.5809F, 0.0F, -0.3054F, -0.5236F));
        PartDefinition rightClaw = root.addOrReplaceChild("rightClaw", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition cube_r2 = rightClaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 28).addBox(-2.3986F, -2.0F, -3.9074F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9345F, -3.4055F, -4.2544F, 0.0F, 0.3054F, 0.5236F));
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 0).addBox(-2.5F, -2.25F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.25F, -4.0F));
        PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.75F, -4.0F));
        PartDefinition rightMiddleLeg = root.addOrReplaceChild("rightMiddleLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -6.0F, -1.0F, 0.0F, 0.0F, -0.3927F));
        PartDefinition leftMiddleLeg = root.addOrReplaceChild("leftMiddleLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -6.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
        PartDefinition rightFrontLeg = root.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -6.0F, -1.0F, 0.0F, -0.2618F, -0.3927F));
        PartDefinition leftFrontLeg = root.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -6.0F, -1.0F, 0.0F, 0.2618F, 0.3927F));
        PartDefinition rightHindLeg = root.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -6.0F, -1.0F, 0.0F, 0.2618F, -0.3927F));
        PartDefinition leftHindLeg = root.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -6.0F, -1.0F, 0.0F, -0.2618F, 0.3927F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(CoconutCrab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setupInitialAnimationValues();
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.shell.visible = entity.hasCoconut();
        float swingSlowdownFactor = 0.2F;
        this.shell.xRot += Mth.cos((float) (Math.toRadians(-360) + limbSwing)) * swingSlowdownFactor * limbSwingAmount;
        this.shell.zRot += Mth.cos((float) (Math.toRadians(-60) + limbSwing)) * swingSlowdownFactor * limbSwingAmount;

        float legLimbSwingAmount = Math.min(limbSwingAmount, 0.6F);
        this.leftHindLeg.yRot += -Mth.cos((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.leftHindLeg.zRot += Mth.cos((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightHindLeg.yRot += -Mth.cos((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightHindLeg.zRot += Mth.cos((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;

        this.leftFrontLeg.yRot += Mth.sin((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.leftFrontLeg.zRot += -Mth.sin((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightFrontLeg.yRot += Mth.sin((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightFrontLeg.zRot += -Mth.sin((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;

        this.leftMiddleLeg.yRot += Mth.cos((float) (Math.toRadians(-120) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.leftMiddleLeg.zRot += -Mth.cos((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightMiddleLeg.yRot += Mth.cos((float) (Math.toRadians(-120) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightMiddleLeg.zRot += -Mth.cos((float) (Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;

        this.leftClaw.xRot += -Mth.cos((float) (Math.toRadians(-20) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.leftClaw.zRot += -Mth.cos((float) (Math.toRadians(-20) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightClaw.xRot += Mth.cos((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
        this.rightClaw.zRot += Mth.cos((float) (Math.toRadians(-40) + limbSwing)) * swingSlowdownFactor * legLimbSwingAmount;
    }

    @Override
    public ModelPart root() {
        return root;
    }

    private void setupInitialAnimationValues() {
        AnimationUtil.setInitialValue(this.root, 0.0F, 24.0F, 0.0F);
        AnimationUtil.setInitialValue(this.head,0.0f, -6.25f, -4.0f);
        AnimationUtil.setInitialValue(this.shell, 0.0F, -8.4F, 6.0F, -0.7854F, 0.0F, 0.0F);
        AnimationUtil.setInitialValue(this.leftHindLeg, -3.0F, -6.0F, -1.0F, 0.0F, -0.2618F, 0.3927F);
        AnimationUtil.setInitialValue(this.rightHindLeg, 3.0F, -6.0F, -1.0F, 0.0F, 0.2618F, -0.3927F);
        AnimationUtil.setInitialValue(this.leftFrontLeg, -3.0F, -6.0F, -1.0F, 0.0F, 0.2618F, 0.3927F);
        AnimationUtil.setInitialValue(this.rightFrontLeg, 3.0F, -6.0F, -1.0F, 0.0F, -0.2618F, -0.3927F);
        AnimationUtil.setInitialValue(this.leftMiddleLeg, -3.0F, -6.0F, -1.0F, 0.0F, 0.0F, 0.3927F);
        AnimationUtil.setInitialValue(this.rightMiddleLeg, 3.0F, -6.0F, -1.0F, 0.0F, 0.0F, -0.3927F);
        AnimationUtil.setInitialValue(this.rightClaw);
        AnimationUtil.setInitialValue(this.leftClaw, 3.2277F, -4.1522F, -5.5809F);
    }
}