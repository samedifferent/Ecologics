package samebutdifferent.ecologics.client.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.Penguin;

@Environment(EnvType.CLIENT)
public class PenguinModel extends AgeableListModel<Penguin> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "penguin"), "main");
    private final ModelPart body;
    public final ModelPart head;
    private final ModelPart leftFlipper;
    private final ModelPart rightFlipper;
    private final ModelPart egg;
    private final ModelPart leftFoot;
    private final ModelPart rightFoot;
    private float slidingAnimationProgress;
    private float swimmingAnimationProgress;

    public PenguinModel(ModelPart root) {
        super(true, 11.1F, 0.0F);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftFlipper = this.body.getChild("leftFlipper");
        this.rightFlipper = this.body.getChild("rightFlipper");
        this.egg = this.body.getChild("egg");
        this.leftFoot = this.body.getChild("leftFoot");
        this.rightFoot = this.body.getChild("rightFoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(23, 0).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -3.0F, 8.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition leftFlipper = body.addOrReplaceChild("leftFlipper", CubeListBuilder.create().texOffs(18, 21).addBox(0.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -8.0F, 0.0F));
        PartDefinition rightFlipper = body.addOrReplaceChild("rightFlipper", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -8.0F, 0.0F));
        PartDefinition egg = body.addOrReplaceChild("egg", CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftFoot = body.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -3.0F));
        PartDefinition rightFoot = body.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -3.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void prepareMobModel(Penguin entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        this.slidingAnimationProgress = entity.getSlidingAnimationProgress(partialTick);
        this.swimmingAnimationProgress = entity.getSwimmingAnimationProgress(partialTick);
    }

    @Override
    public void setupAnim(Penguin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.getAllParts().forEach(ModelPart::resetPose);
        this.egg.visible = entity.isPregnant();
        this.head.resetPose();

        float swingSlowdownFactor = 0.3F; // 10

        if (swimmingAnimationProgress > 0) {
//            this.body.setRotation((float) Math.toRadians(headPitch), (float) Math.toRadians(netHeadYaw), 0.0f);
            this.body.xRot += ModelUtils.rotlerpRad(this.body.xRot, (float) Math.toRadians(90), this.swimmingAnimationProgress)
                    - Mth.cos(0.7F * ageInTicks) * (swingSlowdownFactor * 0.25F);
            this.body.y += -Mth.cos(0.7F * ageInTicks) * (swingSlowdownFactor * 0.025F);

            this.head.y = Mth.lerp(this.swimmingAnimationProgress, this.head.getInitialPose().y, 24);
            this.head.z = Mth.lerp(this.swimmingAnimationProgress, this.head.getInitialPose().z, -9);
            this.head.xRot += Mth.cos(0.7F * ((float) Math.toRadians(-40) + ageInTicks)) * (swingSlowdownFactor * 0.3F);

            this.leftFoot.xRot += (Math.toRadians(17.5) - Mth.cos((float) Math.toRadians(-40) + ageInTicks)) * swingSlowdownFactor;
            this.rightFoot.xRot += (Math.toRadians(17.5) - Mth.sin((float) Math.toRadians(-40) + ageInTicks)) * swingSlowdownFactor;

            this.leftFlipper.xRot += Mth.cos((float) Math.toRadians(-80) + ageInTicks) * (swingSlowdownFactor * 0.2F);
            this.leftFlipper.zRot += (Math.toRadians(-5) - Mth.cos((float) Math.toRadians(-80) + ageInTicks)) *  (swingSlowdownFactor * 0.25F);
            this.rightFlipper.xRot += Mth.cos((float) Math.toRadians(-80) + ageInTicks) * (swingSlowdownFactor * 0.2F);
            this.rightFlipper.zRot += (Math.toRadians(5) - Mth.cos((float) Math.toRadians(-80) + ageInTicks)) *  (swingSlowdownFactor * 0.25F);
        } else if (slidingAnimationProgress > 0) {
            this.body.xRot += ModelUtils.rotlerpRad(this.body.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);
            this.body.z += (-Mth.cos(2F * limbSwing)) * swingSlowdownFactor * limbSwingAmount;

            this.head.x = 0;
            this.head.y = Mth.lerp(this.slidingAnimationProgress, this.head.getInitialPose().y, 24);
            this.head.z = Mth.lerp(this.slidingAnimationProgress, this.head.getInitialPose().z, -11);
            this.head.y += -Mth.cos(2F * ((float)Math.toRadians(-80) + limbSwing)) * swingSlowdownFactor * limbSwingAmount;
            this.head.z += -Mth.cos(2F * limbSwing) * swingSlowdownFactor * limbSwingAmount;

            this.leftFoot.xRot += ModelUtils.rotlerpRad(this.leftFoot.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);
            this.rightFoot.xRot += ModelUtils.rotlerpRad(this.rightFoot.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);

            this.leftFlipper.zRot += (Math.toRadians(-2.5) - Mth.cos(2F * limbSwing)) * (swingSlowdownFactor * 0.5F) * limbSwingAmount;
            this.rightFlipper.zRot += (Math.toRadians(2.5) - Mth.cos(2F * limbSwing)) * (swingSlowdownFactor * 0.5F) * limbSwingAmount;
        } else {
            this.body.yRot += Mth.cos((float)Math.toRadians(-20) + limbSwing) * swingSlowdownFactor * limbSwingAmount;
            this.body.zRot += Mth.cos(limbSwing) * (swingSlowdownFactor * 0.5F) * limbSwingAmount;

            this.head.xRot = headPitch * Mth.DEG_TO_RAD;
            this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
            this.head.yRot += -Mth.cos((float)Math.toRadians(-80) + limbSwing) * (swingSlowdownFactor * 0.5F) * limbSwingAmount;
            this.head.zRot += -Mth.cos((float)Math.toRadians(-40) + limbSwing) * (swingSlowdownFactor * 0.5F) * limbSwingAmount;
            this.head.x += Mth.cos(limbSwing) * (swingSlowdownFactor * 0.1F) * limbSwingAmount;

            this.leftFoot.xRot += (Math.toRadians(-10) + Mth.cos(limbSwing)) * (swingSlowdownFactor * 2F) * limbSwingAmount;
            this.rightFoot.xRot += (Math.toRadians(-10) - Mth.cos(limbSwing)) * (swingSlowdownFactor * 2F) * limbSwingAmount;

            this.leftFlipper.zRot += (Math.toRadians(-10) + Mth.cos((float)Math.toRadians(-40) + limbSwing)) * (swingSlowdownFactor * 0.8F) * limbSwingAmount;
            this.rightFlipper.zRot += (Math.toRadians(10) + Mth.cos((float)Math.toRadians(-40) + limbSwing)) * (swingSlowdownFactor * 0.8F) * limbSwingAmount;
        }
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
