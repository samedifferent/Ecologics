package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelModel.class)
public class CamelModelMixin 
{
    @Final @Unique
    private ModelPart leftChest;
    @Final @Unique
    private ModelPart rightChest;
    
    @Inject(method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/animation/AnimationDefinition;Lnet/minecraft/client/animation/AnimationDefinition;Lnet/minecraft/client/animation/AnimationDefinition;Lnet/minecraft/client/animation/AnimationDefinition;Lnet/minecraft/client/animation/AnimationDefinition;Lnet/minecraft/client/animation/AnimationDefinition;)V", at = @At(value = "TAIL"))
    private void injectConstructor(final ModelPart root, final AnimationDefinition walk, final AnimationDefinition sit, final AnimationDefinition sitPose, final AnimationDefinition standup, final AnimationDefinition idle, final AnimationDefinition dash, CallbackInfo ci) {
        if (root.getChild("body").hasChild("left_chest")) {
            this.leftChest = root.getChild("body").getChild("left_chest");
        }
        if (root.getChild("body").hasChild("right_chest")) {
            this.rightChest = root.getChild("body").getChild("right_chest");
        }
    }
    
    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/CamelRenderState;)V", at = @At(value = "TAIL"))
    private void setupAnim(CamelRenderState state, CallbackInfo ci) {
        if (!state.isBaby && this.leftChest != null && this.rightChest != null) {
            this.leftChest.visible = ((CamelChestDataHolder)(Object)state).hasChest();
            this.rightChest.visible = ((CamelChestDataHolder)(Object)state).hasChest();
        }
    }
}
