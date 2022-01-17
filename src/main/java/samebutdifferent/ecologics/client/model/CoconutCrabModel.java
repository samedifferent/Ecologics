package samebutdifferent.ecologics.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;

@OnlyIn(Dist.CLIENT)
public class CoconutCrabModel<T extends CoconutCrab> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "coconut_crab"), "main");

	private final ModelPart shell;
	private final ModelPart leftClaw;
	private final ModelPart rightClaw;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightMiddleLeg;
	private final ModelPart leftMiddleLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;

	public CoconutCrabModel(ModelPart root) {
		this.shell = root.getChild("shell");
		this.leftClaw = root.getChild("leftClaw");
		this.rightClaw = root.getChild("rightClaw");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightMiddleLeg = root.getChild("rightMiddleLeg");
		this.leftMiddleLeg = root.getChild("leftMiddleLeg");
		this.rightFrontLeg = root.getChild("rightFrontLeg");
		this.leftFrontLeg = root.getChild("leftFrontLeg");
		this.rightHindLeg = root.getChild("rightHindLeg");
		this.leftHindLeg = root.getChild("leftHindLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.6F, 6.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition leftClaw = partdefinition.addOrReplaceChild("leftClaw", CubeListBuilder.create(), PartPose.offset(3.2277F, 19.8478F, -5.5809F));

		PartDefinition cube_r1 = leftClaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(31, 36).addBox(-3.0F, -4.0F, -3.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2723F, 2.1522F, 0.5809F, 0.0F, -0.3054F, -0.5236F));

		PartDefinition rightClaw = partdefinition.addOrReplaceChild("rightClaw", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r2 = rightClaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 28).addBox(-2.3986F, -2.0F, -3.9074F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9345F, -3.4055F, -4.2544F, 0.0F, 0.3054F, 0.5236F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 0).addBox(-2.5F, -2.25F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -5.25F, -4.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.75F, -4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -9.0F, -4.0F, 7.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rightMiddleLeg = partdefinition.addOrReplaceChild("rightMiddleLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition leftMiddleLeg = partdefinition.addOrReplaceChild("leftMiddleLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, -0.2618F, -0.3927F));

		PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, 0.2618F, 0.3927F));

		PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, 0.2618F, -0.3927F));

		PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, -0.2618F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
		this.head.xRot = headPitch * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.shell.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftClaw.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightClaw.render(poseStack, buffer, packedLight, packedOverlay);
		this.head.render(poseStack, buffer, packedLight, packedOverlay);
		this.body.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightMiddleLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftMiddleLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightFrontLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftFrontLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightHindLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftHindLeg.render(poseStack, buffer, packedLight, packedOverlay);
	}
}