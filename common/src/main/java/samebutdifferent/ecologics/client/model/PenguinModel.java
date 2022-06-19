package samebutdifferent.ecologics.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PenguinModel extends HierarchicalModel<Penguin> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "penguin"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftFlipper;
    private final ModelPart rightFlipper;
    private final ModelPart egg;
    private final ModelPart leftFoot;
    private final ModelPart rightFoot;

    public PenguinModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.leftFlipper = this.body.getChild("leftFlipper");
        this.rightFlipper = this.body.getChild("rightFlipper");
        this.egg = this.body.getChild("egg");
        this.leftFoot = this.body.getChild("leftFoot");
        this.rightFoot = this.body.getChild("rightFoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -3.0F, 8.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(23, 0).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));
        PartDefinition leftFlipper = body.addOrReplaceChild("leftFlipper", CubeListBuilder.create().texOffs(18, 21).addBox(0.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -8.0F, 0.0F));
        PartDefinition rightFlipper = body.addOrReplaceChild("rightFlipper", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -8.0F, 0.0F));
        PartDefinition egg = body.addOrReplaceChild("egg", CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftFoot = body.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -3.0F));
        PartDefinition rightFoot = body.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -3.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Penguin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.egg.visible = entity.isPregnant();

        float swingSlowdownFactor = 0.3F; // 10

        this.body.yRot += Mth.cos((float) (Math.toRadians(-20) + limbSwing)) * swingSlowdownFactor * limbSwingAmount;
        this.body.zRot += Mth.cos(limbSwing) * (swingSlowdownFactor / 2F) * limbSwingAmount;

        this.head.yRot += -Mth.cos((float) (Math.toRadians(-80) + limbSwing)) * (swingSlowdownFactor / 2F) * limbSwingAmount;
        this.head.zRot += -Mth.cos((float) (Math.toRadians(-40) + limbSwing)) * (swingSlowdownFactor / 2F) * limbSwingAmount;

        this.leftFlipper.zRot += (Math.toRadians(-10) + Mth.cos((float) (Math.toRadians(-40) + limbSwing))) * (swingSlowdownFactor * 0.8F) * limbSwingAmount;
        this.rightFlipper.zRot += (Math.toRadians(10) + Mth.cos((float) (Math.toRadians(-40) + limbSwing))) * (swingSlowdownFactor * 0.8F) * limbSwingAmount;

        this.leftFoot.xRot += (Math.toRadians(-10) + Mth.cos(limbSwing)) * (swingSlowdownFactor * 2F) * limbSwingAmount;
        this.rightFoot.xRot += (Math.toRadians(-10) - Mth.cos(limbSwing)) * (swingSlowdownFactor * 2F) * limbSwingAmount;
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
