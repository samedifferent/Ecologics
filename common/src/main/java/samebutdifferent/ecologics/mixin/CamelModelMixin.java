package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelModel.class)
public class CamelModelMixin extends EntityModel<CamelRenderState> {
	
    protected CamelModelMixin(ModelPart $$0) {
        super($$0);
    }
    
    @Final @Unique
    private ModelPart body;
    @Final @Unique
	private ModelPart leftChest;
    @Final @Unique
    private ModelPart rightChest;
    
    @Inject(method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;)V", at = @At(value = "TAIL"))
    private void injectConstructor(ModelPart $$0, CallbackInfo ci) {
    	this.body = $$0.getChild("body");
    	this.leftChest = this.body.getChild("left_chest");
    	this.rightChest = this.body.getChild("right_chest");
    }
    
    @Inject(method = "createBodyMesh()Lnet/minecraft/client/model/geom/builders/MeshDefinition;", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void injectCreateBodyMesh(CallbackInfoReturnable<MeshDefinition> cir, MeshDefinition meshDef, PartDefinition root, PartDefinition body) {
    	CubeListBuilder clb = CubeListBuilder.create().texOffs(94, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F);
        body.addOrReplaceChild("left_chest", clb, PartPose.offsetAndRotation(9.0F, -9.0F, -7.0F, 0.0F, (float) (-Math.PI / 2), 0.0F));
        body.addOrReplaceChild("right_chest", clb, PartPose.offsetAndRotation(-9.0F, -9.0F, 1.0F, 0.0F, (float) (Math.PI / 2), 0.0F));
    }
    
	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/CamelRenderState;)V", at = @At(value = "TAIL"))
    private void setupAnim(CamelRenderState state, CallbackInfo ci) {
        this.leftChest.visible = ((CamelChestDataHolder)(Object)state).hasChest();
        this.rightChest.visible = ((CamelChestDataHolder)(Object)state).hasChest();
    }
}
