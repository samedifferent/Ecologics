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
import samebutdifferent.ecologics.client.animation.definitions.CoconutCrabAnimation;
import samebutdifferent.ecologics.client.animation.definitions.SquirrelAnimation;
import samebutdifferent.ecologics.entity.Squirrel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SquirrelModel extends HierarchicalModel<Squirrel> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, "squirrel"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;

    public SquirrelModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.5F, 0.3927F, 0.0F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 13).addBox(-2.0F, -3.6667F, -3.3333F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 0.3333F, -3.3333F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(12, 13).addBox(1.0F, -4.6667F, -0.3333F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 13).mirror().addBox(-3.0F, -4.6667F, -0.3333F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.2572F, -2.784F, -0.3927F, 0.0F, 0.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -6.8333F, -0.3333F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-2.0F, -9.8333F, 3.6667F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 22).addBox(-2.0F, -9.8333F, -0.3333F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.8333F, 1.8333F, -0.2618F, 0.0F, 0.0F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(10, 29).addBox(-1.0F, -0.2929F, -4.2071F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(10, 29).mirror().addBox(-1.0F, -0.2929F, -4.2071F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, -0.5F));
        PartDefinition thigh = left_leg.addOrReplaceChild("thigh", CubeListBuilder.create().texOffs(0, 25).addBox(-1.5F, -0.5349F, -0.3108F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -4.0F, 1.0F, -0.3927F, 0.0F, 0.0F));
        PartDefinition foot = thigh.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(22, 23).addBox(-1.0F, -0.5F, -5.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.9651F, 1.6892F));
        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, -0.5F));
        PartDefinition thigh2 = right_leg.addOrReplaceChild("thigh2", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-1.5F, -0.5349F, -0.3108F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -4.0F, 1.0F, -0.3927F, 0.0F, 0.0F));
        PartDefinition foot2 = thigh2.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(22, 23).mirror().addBox(-1.0F, -0.5F, -5.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, 3.9651F, 1.6892F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Squirrel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.animate(entity.moveAnimationState, SquirrelAnimation.MOVE, ageInTicks);
        this.animate(entity.idleAnimationState, SquirrelAnimation.IDLE, ageInTicks);
        this.animate(entity.climbAnimationState, SquirrelAnimation.CLIMB, ageInTicks);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}