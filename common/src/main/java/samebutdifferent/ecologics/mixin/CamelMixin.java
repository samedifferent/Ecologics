package samebutdifferent.ecologics.mixin;

import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(Camel.class)
public class CamelMixin {
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModItems.PRICKLY_PEAR.get())) {
            cir.setReturnValue(true);
        }
    }
}
