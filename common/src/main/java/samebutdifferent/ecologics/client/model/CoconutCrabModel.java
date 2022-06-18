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
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.animation.definitions.CoconutCrabAnimation;
import samebutdifferent.ecologics.entity.CoconutCrab;

@Environment(EnvType.CLIENT)
public class CoconutCrabModel extends HierarchicalModel<CoconutCrab> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "coconut_crab"), "main");
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart shell;

    public CoconutCrabModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.shell = this.root.getChild("shell");
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
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.shell.visible = entity.hasCoconut();
        this.animate(entity.walkAnimationState, CoconutCrabAnimation.WALK, ageInTicks);
        this.animate(entity.idleAnimationState, CoconutCrabAnimation.IDLE, ageInTicks);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}