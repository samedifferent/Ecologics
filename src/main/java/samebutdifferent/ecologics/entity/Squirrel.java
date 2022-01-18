package samebutdifferent.ecologics.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Squirrel extends Animal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public Squirrel(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pMob) {
        return ModEntityTypes.SQUIRREL.get().create(pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.HONEYCOMB), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new SquirrelGoToLeavesGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.4F);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.HONEYCOMB);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.SQUIRREL_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.SQUIRREL_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.SQUIRREL_DEATH.get();
    }


    private static  <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.squirrel.run", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, Squirrel::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    static class SquirrelGoToLeavesGoal extends MoveToBlockGoal {

        public SquirrelGoToLeavesGoal(Squirrel pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier, 8, 2);
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return true;
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(BlockTags.LEAVES) && pLevel.getBlockState(pPos.above()).isPathfindable(pLevel, pPos, PathComputationType.LAND);
        }
    }
}
