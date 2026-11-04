package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.CamelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.camel.Camel;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelRenderer.class)
public class CamelRendererMixin
{	
	private static final ResourceLocation CAMEL_CHEST_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/camel/camel_chest.png");

	@Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/camel/Camel;)Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "RETURN"), cancellable = true)
	private void injectTextureLocation(Camel entity, CallbackInfoReturnable<ResourceLocation> cir) {
		if (((CamelChestDataHolder)entity).hasChest()) {
			cir.setReturnValue(CAMEL_CHEST_LOCATION);
		}
	}
}
