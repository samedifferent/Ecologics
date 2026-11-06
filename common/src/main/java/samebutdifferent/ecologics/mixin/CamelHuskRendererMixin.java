package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.CamelHuskRenderer;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelHuskRenderer.class)
public class CamelHuskRendererMixin 
{
    private static final Identifier CAMEL_HUSK_CHEST_LOCATION = Identifier.withDefaultNamespace("textures/entity/camel/camel_husk_chest.png");
    
    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/CamelRenderState;)Lnet/minecraft/resources/Identifier;", at = @At(value = "RETURN"), cancellable = true)
    private void injectTextureLocation(CamelRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (((CamelChestDataHolder)state).hasChest()) {
            cir.setReturnValue(CAMEL_HUSK_CHEST_LOCATION);
        }
    }
}
