package samebutdifferent.ecologics.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;

public class CoconutCrabModel<T extends CoconutCrab> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "coconut_crab"), "main");

	private final ModelPart bone;
	private final ModelPart body;
	private final ModelPart leg_middle_right;
	private final ModelPart leg_front_right;
	private final ModelPart leg_back_right;
	private final ModelPart leg_middle_left;
	private final ModelPart leg_back_left;
	private final ModelPart leg_front_left;

	public CoconutCrabModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.body = root.getChild("body");
		this.leg_middle_right = root.getChild("leg_middle_right");
		this.leg_front_right = root.getChild("leg_front_right");
		this.leg_back_right = root.getChild("leg_back_right");
		this.leg_middle_left = root.getChild("leg_middle_left");
		this.leg_back_left = root.getChild("leg_back_left");
		this.leg_front_left = root.getChild("leg_front_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.6F, 6.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -9.0F, -4.0F, 7.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 0).addBox(-2.5F, -2.25F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.5F, -5.25F, -4.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.25F, -4.0F));

		PartDefinition claw_left = body.addOrReplaceChild("claw_left", CubeListBuilder.create(), PartPose.offset(3.2277F, -4.1522F, -5.5809F));

		PartDefinition cube_r1 = claw_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(31, 36).addBox(-3.0F, -4.0F, -3.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2723F, 2.1522F, 0.5809F, 0.0F, -0.3054F, -0.5236F));

		PartDefinition claw_right = body.addOrReplaceChild("claw_right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = claw_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 28).addBox(-2.3986F, -2.0F, -3.9074F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9345F, -3.4055F, -4.2544F, 0.0F, 0.3054F, 0.5236F));

		PartDefinition leg_middle_right = partdefinition.addOrReplaceChild("leg_middle_right", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition leg_front_right = partdefinition.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, -0.2618F, -0.3927F));

		PartDefinition leg_back_right = partdefinition.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(24, 24).addBox(-15.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, 0.0F, 0.2618F, -0.3927F));

		PartDefinition leg_middle_left = partdefinition.addOrReplaceChild("leg_middle_left", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition leg_back_left = partdefinition.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, 0.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, -0.2618F, 0.3927F));

		PartDefinition leg_front_left = partdefinition.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(24, 24).mirror().addBox(5.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, 0.0F, 0.2618F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		leg_middle_right.render(poseStack, buffer, packedLight, packedOverlay);
		leg_front_right.render(poseStack, buffer, packedLight, packedOverlay);
		leg_back_right.render(poseStack, buffer, packedLight, packedOverlay);
		leg_middle_left.render(poseStack, buffer, packedLight, packedOverlay);
		leg_back_left.render(poseStack, buffer, packedLight, packedOverlay);
		leg_front_left.render(poseStack, buffer, packedLight, packedOverlay);
	}
}