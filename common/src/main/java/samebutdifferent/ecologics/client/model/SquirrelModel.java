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

    public SquirrelModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 15).addBox(-2.0F, -3.0F, -4.1667F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(11, 15).addBox(1.0F, -5.0F, -1.1667F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 15).mirror().addBox(-3.0F, -5.0F, -1.1667F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 18.0F, -3.8333F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.5F, 2.0F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.5F, -3.5F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.5F, -3.5F));
        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, -2.0F));
        PartDefinition left_thigh = left_leg.addOrReplaceChild("left_thigh", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, 4.5F));
        PartDefinition left_foot = left_thigh.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -0.5F, -7.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 3.0F));
        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, -2.0F));
        PartDefinition right_thigh = right_leg.addOrReplaceChild("right_thigh", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -4.0F, 4.5F));
        PartDefinition right_foot = right_thigh.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-1.0F, -0.5F, -5.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.5F, 1.5F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 24).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-1.5F, -11.0F, 3.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 4.0F, -0.7854F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Squirrel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.getAllParts().forEach(ModelPart::resetPose);
        this.head.resetPose();
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
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