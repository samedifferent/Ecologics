package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(LootContext.Builder.class)
public class LootContextBuilderMixin {

    @Shadow @Final private LootParams params;

    //TODO: Update this code.
    /*@Inject(at = @At("HEAD"), method = "withParameter", cancellable = true)
    private void onGetDrops(LootContextParam<Object> parameter, Object value, CallbackInfoReturnable<LootContext.Builder> cir) {
        if (value instanceof ItemStack stack && stack.is(ModItems.CRAB_CLAW.get())) {
            this.params.addDynamicDrops(parameter.getName(), itemstack -> new ItemStack(Items.SHEARS));
            cir.setReturnValue((LootContext.Builder) (Object) this);
        }
    }*/
}
