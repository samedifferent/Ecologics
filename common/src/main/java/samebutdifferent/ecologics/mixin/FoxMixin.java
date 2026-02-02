package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.level.Level;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.platform.ConfigPlatformHelper;

@Mixin(Fox.class)
public abstract class FoxMixin extends Animal {

    protected FoxMixin(EntityType<? extends TamableAnimal> entitytype, Level level) {
        super(entitytype, level);
    }

    @Inject(method = "registerGoals()V", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        if (ConfigPlatformHelper.foxesAttackSquirrels()) {
            this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Squirrel.class, 30, true, true, (target, level) -> target instanceof Squirrel && level.getDifficulty() != Difficulty.PEACEFUL));
        }
    }
}
