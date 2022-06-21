package samebutdifferent.ecologics.client.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.Squirrel;

@Environment(EnvType.CLIENT)
public class SquirrelModel extends AgeableListModel<Squirrel> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "squirrel"), "main");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart leftThigh;
    private final ModelPart leftFoot;
    private final ModelPart rightLeg;
    private final ModelPart rightThigh;
    private final ModelPart rightFoot;
    private final ModelPart tail;

    public SquirrelModel(ModelPart root) {
        super(true, 9.0f, 2.0f);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftArm = this.body.getChild("leftArm");
        this.rightArm = this.body.getChild("rightArm");
        this.leftLeg = this.body.getChild("leftLeg");
        this.leftThigh = this.leftLeg.getChild("leftThigh");
        this.leftFoot = this.leftThigh.getChild("leftFoot");
        this.rightLeg = this.body.getChild("rightLeg");
        this.rightThigh = this.rightLeg.getChild("rightThigh");
        this.rightFoot = this.rightThigh.getChild("rightFoot");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 15).addBox(-2.0F, -3.0F, -4.1667F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(11, 15).addBox(1.0F, -5.0F, -1.1667F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 15).mirror().addBox(-3.0F, -5.0F, -1.1667F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 18.0F, -3.8333F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.5F, 2.0F));
        PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.5F, -3.5F));
        PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.5F, -3.5F));
        PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, -2.0F));
        PartDefinition leftThigh = leftLeg.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, 4.5F));
        PartDefinition leftFoot = leftThigh.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -0.5F, -7.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 3.0F));
        PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, -2.0F));
        PartDefinition rightThigh = rightLeg.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -4.0F, 4.5F));
        PartDefinition rightFoot = rightThigh.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-1.0F, -0.5F, -5.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.5F, 1.5F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 24).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-1.5F, -11.0F, 3.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 4.0F, -0.7854F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Squirrel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.getAllParts().forEach(ModelPart::resetPose);
        this.head.resetPose();

        float swingCorrectionFactor = 0.1F; // 10
        float correctedLimbSwing = entity.isBaby() ? limbSwing / 3 : limbSwing;

        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot += Mth.cos((float) Math.toRadians(-45) + correctedLimbSwing) * (swingCorrectionFactor * 0.8F) * limbSwingAmount;
        this.head.y += (-1 - Mth.cos(correctedLimbSwing)) * (entity.isBaby() ? 1 : 2) * limbSwingAmount;
        this.head.z += 1 * limbSwingAmount;

        this.body.xRot += (Math.toRadians(10) - Mth.cos((float) Math.toRadians(-30) + correctedLimbSwing)) * (swingCorrectionFactor * 3.5F) * limbSwingAmount;
        this.body.y += (-1 - Mth.cos(correctedLimbSwing)) * 2.0f * limbSwingAmount;

        this.leftArm.xRot += (Math.toRadians(-15) - Mth.cos((float) Math.toRadians(-35) + correctedLimbSwing)) * (swingCorrectionFactor * 7.5F) * limbSwingAmount;
        this.leftArm.yRot += Math.toRadians(-5);
        this.leftArm.zRot += (Math.toRadians(-15) - Mth.cos(correctedLimbSwing)) * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        leftArm.z += (0.5F + Mth.cos(correctedLimbSwing)) * (swingCorrectionFactor * 0.05F) * limbSwingAmount;

        this.rightArm.xRot += (Math.toRadians(-15) - Mth.cos((float) Math.toRadians(-45) + correctedLimbSwing)) * (swingCorrectionFactor * 7.5F) * limbSwingAmount;
        this.rightArm.yRot += Math.toRadians(-5);
        this.rightArm.zRot += (Math.toRadians(15) - Mth.cos(correctedLimbSwing)) * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        rightArm.z += (0.5F + Mth.cos((float) Math.toRadians(-25) + correctedLimbSwing)) * (swingCorrectionFactor * 0.05F) * limbSwingAmount;

        this.leftThigh.xRot += (Math.toRadians(45) - Mth.cos((float) Math.toRadians(-45) + correctedLimbSwing)) * (swingCorrectionFactor * 6.5F) * limbSwingAmount;
        this.leftThigh.y += -3 * (swingCorrectionFactor * 7) * limbSwingAmount;
        this.leftThigh.z += Mth.cos((float) Math.toRadians(-35) + correctedLimbSwing) * (swingCorrectionFactor * 0.1F) * limbSwingAmount;

        this.leftFoot.xRot += (Math.toRadians(25) + Mth.cos((float) Math.toRadians(-125) + correctedLimbSwing)) * (swingCorrectionFactor * 6.0F) * limbSwingAmount;
        this.leftFoot.y += -1 * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        this.leftFoot.z += (-0.5F + Mth.cos(correctedLimbSwing)) * (swingCorrectionFactor * 0.025F) * limbSwingAmount;

        this.rightLeg.y += -1 * swingCorrectionFactor * limbSwingAmount;

        this.rightThigh.xRot += (Math.toRadians(45) - Mth.cos((float) Math.toRadians(-25) + correctedLimbSwing)) * (swingCorrectionFactor * 6.5F) * limbSwingAmount;
        this.rightThigh.y += -2 * (swingCorrectionFactor * 7) * limbSwingAmount;
        this.rightThigh.z += Mth.cos((float) Math.toRadians(-35) + correctedLimbSwing) * (swingCorrectionFactor * 0.1F) * limbSwingAmount;

        this.rightFoot.xRot += (Math.toRadians(15) + Mth.cos((float) Math.toRadians(-85) + correctedLimbSwing)) * (swingCorrectionFactor * 6.0F) * limbSwingAmount;
        this.rightFoot.y += -1 * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        this.rightFoot.z += (-0.5F + Mth.cos(correctedLimbSwing)) * (swingCorrectionFactor * 0.025F) * limbSwingAmount;

        this.tail.xRot += (Math.toRadians(-40) - Mth.cos((float) Math.toRadians(-120) + correctedLimbSwing)) * (swingCorrectionFactor * 1.8F) * limbSwingAmount;
        this.tail.z += Mth.cos((float) Math.toRadians(-100) + correctedLimbSwing) * (swingCorrectionFactor * 0.03F) * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }
}