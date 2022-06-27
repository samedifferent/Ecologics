package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(Sheep.class)
public class SheepMixin {
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean onMobInteract(ItemStack stack, Item item) {
        if (stack.is(ModItems.CRAB_CLAW.get())) {
            return true;
        }
        return stack.is(item);
    }
}
