package samebutdifferent.ecologics.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.ecologics.registry.ModMobEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @ModifyVariable(method = "travel", at = @At(value = "STORE"), ordinal = 0)
    private float modifyFriction(float f) {
        if (((Object)this) instanceof LivingEntity living && living.hasEffect(ModMobEffects.SLIPPERY.get())) {
            return 0.98F;
        }
        return f;
    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (((Object)this) instanceof LivingEntity living && living.hasEffect(ModMobEffects.SLIPPERY.get())) {
            cir.setReturnValue(1.02F);
        }
    }
}