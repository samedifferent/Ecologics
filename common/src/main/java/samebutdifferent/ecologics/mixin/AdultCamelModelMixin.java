package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.animation.definitions.CamelAnimation;
import net.minecraft.client.model.animal.camel.AdultCamelModel;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

@Mixin(AdultCamelModel.class)
public class AdultCamelModelMixin extends CamelModel {
	
    protected AdultCamelModelMixin(ModelPart $$0) {
        super($$0, CamelAnimation.CAMEL_WALK, CamelAnimation.CAMEL_SIT, CamelAnimation.CAMEL_SIT_POSE, CamelAnimation.CAMEL_STANDUP, CamelAnimation.CAMEL_IDLE, CamelAnimation.CAMEL_DASH);
    }
    
    @Inject(method = "createBodyMesh()Lnet/minecraft/client/model/geom/builders/MeshDefinition;", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void injectCreateBodyMesh(CallbackInfoReturnable<MeshDefinition> cir, MeshDefinition meshDef, PartDefinition root, PartDefinition body) {
    	CubeListBuilder clb = CubeListBuilder.create().texOffs(94, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F);
        body.addOrReplaceChild("left_chest", clb, PartPose.offsetAndRotation(9.0F, -9.0F, -7.0F, 0.0F, (float) (-Math.PI / 2), 0.0F));
        body.addOrReplaceChild("right_chest", clb, PartPose.offsetAndRotation(-9.0F, -9.0F, 1.0F, 0.0F, (float) (Math.PI / 2), 0.0F));
    }
}
