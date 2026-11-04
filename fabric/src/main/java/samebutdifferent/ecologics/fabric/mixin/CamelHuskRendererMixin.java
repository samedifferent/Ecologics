package samebutdifferent.ecologics.fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.blackgear.vanillabackport.client.level.renderer.entity.mob.CamelHuskRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.camel.Camel;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelHuskRenderer.class)
public class CamelHuskRendererMixin
{	
	private static final ResourceLocation CAMEL_CHEST_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/camel/camel_husk_chest.png");

	@Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/camel/Camel;)Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "RETURN"), cancellable = true)
	private void injectTextureLocation(Camel entity, CallbackInfoReturnable<ResourceLocation> cir) {
		if (((CamelChestDataHolder)entity).hasChest()) {
			cir.setReturnValue(CAMEL_CHEST_LOCATION);
		}
	}
}
