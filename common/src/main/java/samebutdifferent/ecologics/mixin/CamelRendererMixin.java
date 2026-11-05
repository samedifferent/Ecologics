package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.CamelRenderer;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.camel.Camel;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelRenderer.class)
public class CamelRendererMixin implements CamelChestDataHolder 
{    
    private static final Identifier CAMEL_CHEST_LOCATION = Identifier.withDefaultNamespace("textures/entity/camel/camel_chest.png");
        
    private boolean hasChest;
    
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/camel/Camel;Lnet/minecraft/client/renderer/entity/state/CamelRenderState;F)V", at = @At(value = "TAIL"))
    private void injectExtractRenderState(Camel entity, CamelRenderState state, float $$2, CallbackInfo ci) {
        boolean hasChest = ((CamelChestDataHolder) entity).hasChest();
        ((CamelChestDataHolder) state).setChest(hasChest);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/CamelRenderState;)Lnet/minecraft/resources/Identifier;", at = @At(value = "RETURN"), cancellable = true)
    private void injectTextureLocation(CamelRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (((CamelChestDataHolder)state).hasChest()) {
            cir.setReturnValue(CAMEL_CHEST_LOCATION);
        }
    }
    
    @Override
    public boolean hasChest() {
        return this.hasChest;
    }

    @Override
    public void setChest(boolean hasChest) {
        this.hasChest = hasChest;
    }
}
