package samebutdifferent.ecologics.client.model;

import java.util.Set;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.renderer.entity.state.PenguinRenderState;

public class PenguinModel extends EntityModel<PenguinRenderState> {
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 4.75F, 0.0F, 1.5F, 2.0F, 24.0F, Set.of("head"));
    public static final ModelLayerLocation PENGUIN = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "penguin"), "main");
    public static final ModelLayerLocation PENGUIN_BABY = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "penguin_baby"), "main");
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
    	super(root);
        //super(true, 4.75F, 0.0F, 1.5f, 2.0f, 24.0f);
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
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));
        PartDefinition leftFlipper = body.addOrReplaceChild("leftFlipper", CubeListBuilder.create().texOffs(18, 21).addBox(0.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, 0.0F));
        PartDefinition rightFlipper = body.addOrReplaceChild("rightFlipper", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.0F, -1.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.0F, 0.0F));
        PartDefinition egg = body.addOrReplaceChild("egg", CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));
        PartDefinition leftFoot = body.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 7.0F, -3.0F));
        PartDefinition rightFoot = body.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 7.0F, -3.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createBaby() {
    	return createBodyLayer().apply(BABY_TRANSFORMER);
    }
    
    /*public void prepareMobModel(Penguin entity, float walkAnimationPos, float walkAnimationSpeed, float partialTick) {
        // super.prepareMobModel(entity, walkAnimationPos, walkAnimationSpeed, partialTick);
        this.slidingAnimationProgress = entity.getSlidingAnimationProgress(partialTick);
        this.swimmingAnimationProgress = entity.getSwimmingAnimationProgress(partialTick);
    }*/

    @Override
    public void setupAnim(PenguinRenderState entity) { //, float walkAnimationPos, float walkAnimationSpeed, float ageInTicks, float netHeadYaw, float headPitch
        this.body.getAllParts().forEach(ModelPart::resetPose);
        this.egg.visible = entity.isPregnant;
        this.head.resetPose();

        float swingSlowdownFactor = 0.3F; // 10

        if (swimmingAnimationProgress > 0) {
//            this.body.setRotation((float) Math.toRadians(headPitch), (float) Math.toRadians(netHeadYaw), 0.0f);
            this.body.xRot += Mth.rotLerpRad(this.body.xRot, (float) Math.toRadians(90), this.swimmingAnimationProgress) - Mth.cos(0.7F * entity.ageInTicks) * (swingSlowdownFactor * 0.25F);
            this.body.y = Mth.lerp(this.swimmingAnimationProgress, this.body.getInitialPose().y(), this.body.getInitialPose().y() + 7);
            this.body.y += -Mth.cos(0.7F * entity.ageInTicks) * (swingSlowdownFactor * 0.025F);

            this.head.xRot = Mth.lerp(this.swimmingAnimationProgress, entity.xRot * Mth.DEG_TO_RAD, 0);
            this.head.yRot = Mth.lerp(this.swimmingAnimationProgress, entity.yRot * Mth.DEG_TO_RAD, 0);
            this.head.y = Mth.lerp(this.swimmingAnimationProgress, this.head.getInitialPose().y(), entity.isBaby ? 21 : 24);
            this.head.z = Mth.lerp(this.swimmingAnimationProgress, this.head.getInitialPose().z(), -2);
            this.head.xRot += Mth.cos(0.7F * ((float) Math.toRadians(-40) + entity.ageInTicks)) * (swingSlowdownFactor * 0.3F);

            this.leftFoot.xRot += (Math.toRadians(17.5) - Mth.cos((float) Math.toRadians(-40) + entity.ageInTicks)) * swingSlowdownFactor;
            this.rightFoot.xRot += (Math.toRadians(17.5) - Mth.sin((float) Math.toRadians(-40) + entity.ageInTicks)) * swingSlowdownFactor;

            this.leftFlipper.xRot += Mth.cos((float) Math.toRadians(-80) + entity.ageInTicks) * (swingSlowdownFactor * 0.2F);
            this.leftFlipper.zRot += (Math.toRadians(-5) - Mth.cos((float) Math.toRadians(-80) + entity.ageInTicks)) *  (swingSlowdownFactor * 0.25F);
            this.rightFlipper.xRot += Mth.cos((float) Math.toRadians(-80) + entity.ageInTicks) * (swingSlowdownFactor * 0.2F);
            this.rightFlipper.zRot += (Math.toRadians(5) - Mth.cos((float) Math.toRadians(-80) + entity.ageInTicks)) *  (swingSlowdownFactor * 0.25F);
        } else if (slidingAnimationProgress > 0) {
            this.body.xRot += Mth.rotLerpRad(this.body.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);
            this.body.y = Mth.lerp(this.slidingAnimationProgress, this.body.getInitialPose().y(), this.body.getInitialPose().y() + 7);
            this.body.z += (-Mth.cos(2F * entity.walkAnimationPos)) * swingSlowdownFactor * entity.walkAnimationSpeed;

            this.head.xRot = Mth.lerp(this.slidingAnimationProgress, entity.xRot * Mth.DEG_TO_RAD, 0);
            this.head.yRot = Mth.lerp(this.slidingAnimationProgress, entity.yRot * Mth.DEG_TO_RAD, 0);
            this.head.y = Mth.lerp(this.slidingAnimationProgress, this.head.getInitialPose().y(), entity.isBaby ? 20 : 24);
            this.head.z = Mth.lerp(this.slidingAnimationProgress, this.head.getInitialPose().z(), -4);
            this.head.y += -Mth.cos(2F * ((float)Math.toRadians(-80) + entity.walkAnimationPos)) * swingSlowdownFactor * entity.walkAnimationSpeed;
            this.head.z += -Mth.cos(2F * entity.walkAnimationPos) * swingSlowdownFactor * entity.walkAnimationSpeed;

            this.leftFoot.xRot += Mth.rotLerpRad(this.leftFoot.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);
            this.rightFoot.xRot += Mth.rotLerpRad(this.rightFoot.xRot, (float) Math.toRadians(90), this.slidingAnimationProgress);

            this.leftFlipper.zRot += (Math.toRadians(-2.5) - Mth.cos(2F * entity.walkAnimationPos)) * (swingSlowdownFactor * 0.5F) * entity.walkAnimationSpeed;
            this.rightFlipper.zRot += (Math.toRadians(2.5) - Mth.cos(2F * entity.walkAnimationPos)) * (swingSlowdownFactor * 0.5F) * entity.walkAnimationSpeed;
        } else {
            this.body.yRot += Mth.cos((float)Math.toRadians(-20) + entity.walkAnimationPos) * swingSlowdownFactor * entity.walkAnimationSpeed;
            this.body.zRot += Mth.cos(entity.walkAnimationPos) * (swingSlowdownFactor * 0.5F) * entity.walkAnimationSpeed;

            this.head.xRot = entity.xRot * Mth.DEG_TO_RAD;
            this.head.yRot = entity.yRot * Mth.DEG_TO_RAD;
            this.head.yRot += -Mth.cos((float)Math.toRadians(-80) + entity.walkAnimationPos) * (swingSlowdownFactor * 0.5F) * entity.walkAnimationSpeed;
            this.head.zRot += -Mth.cos((float)Math.toRadians(-40) + entity.walkAnimationPos) * (swingSlowdownFactor * 0.5F) * entity.walkAnimationSpeed;
            this.head.x += Mth.cos(entity.walkAnimationPos) * (swingSlowdownFactor * 0.1F) * entity.walkAnimationSpeed;

            this.leftFoot.xRot += (Math.toRadians(-10) + Mth.cos(entity.walkAnimationPos)) * (swingSlowdownFactor * 2F) * entity.walkAnimationSpeed;
            this.rightFoot.xRot += (Math.toRadians(-10) - Mth.cos(entity.walkAnimationPos)) * (swingSlowdownFactor * 2F) * entity.walkAnimationSpeed;

            this.leftFlipper.zRot += (Math.toRadians(-10) + Mth.cos((float)Math.toRadians(-40) + entity.walkAnimationPos)) * (swingSlowdownFactor * 0.8F) * entity.walkAnimationSpeed;
            this.rightFlipper.zRot += (Math.toRadians(10) + Mth.cos((float)Math.toRadians(-40) + entity.walkAnimationPos)) * (swingSlowdownFactor * 0.8F) * entity.walkAnimationSpeed;
        }
    }

    //TODO: Determine if this code is safe to remove.
    /*@Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }*/
}
