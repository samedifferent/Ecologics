package samebutdifferent.ecologics.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.ecologics.registry.ModMobEffects;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    public MixinLivingEntity(EntityType<?> pEntityType, World pLevel) {
        super(pEntityType, pLevel);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"), method = "travel")
    private float onTravel(Block instance) {
        if (((LivingEntity) (Object) this).hasStatusEffect(ModMobEffects.SLIPPERY)) {
            return 0.98F;
        }
        return instance.getSlipperiness();
    }

    @Inject(method = "getVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (((LivingEntity) (Object) this).hasStatusEffect(ModMobEffects.SLIPPERY)) {
            cir.setReturnValue(1.05F);
        }
    }
}