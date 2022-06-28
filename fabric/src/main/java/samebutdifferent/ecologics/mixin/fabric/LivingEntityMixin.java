package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.ecologics.interfaces.BlockBehaviourInterface;
import samebutdifferent.ecologics.registry.ModMobEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    private final LivingEntity living = (LivingEntity) (Object) this;

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"), method = "travel")
    private void onTravel(Vec3 travelVector, CallbackInfo ci) {
        BlockPos blockPos = this.getBlockPosBelowThatAffectsMyMovement();
        Block block = this.level.getBlockState(blockPos).getBlock();
        if (living.hasEffect(ModMobEffects.SLIPPERY.get())) {
            ((BlockBehaviourInterface) block).setFriction(0.98F);
        }
    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (living.hasEffect(ModMobEffects.SLIPPERY.get())) {
            cir.setReturnValue(1.02F);
        }
    }
}