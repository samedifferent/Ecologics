package samebutdifferent.ecologics.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;
import java.util.function.Predicate;

public class CoconutCrab extends Animal implements IAnimatable, NeutralMob {
    private static final EntityDataAccessor<Boolean> HAS_COCONUT = SynchedEntityData.defineId(CoconutCrab.class, EntityDataSerializers.BOOLEAN);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable private UUID persistentAngerTarget;
    private final AnimationFactory factory = new AnimationFactory(this);

    public CoconutCrab(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pMob) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new CoconutCrab.CrabAvoidGoal<>(this, Player.class, 8.0F, 2.0D, 2.0D));
        this.goalSelector.addGoal(2, new CoconutCrab.CrabMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CoconutCrab.CrabNearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D).add(ForgeMod.REACH_DISTANCE.get(), 3);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return super.hurt(pSource, this.hasCoconut() ? pAmount / 2 : pAmount);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() <= this.getMaxHealth() / 2 && this.hasCoconut()) {
            this.breakCoconut();
        }
    }

    private void breakCoconut() {
        this.setHasCoconut(false);
        this.stopBeingAngry();
        this.playCoconutSmashSound();
        ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.COCONUT_SLICE.get(), 2));
        itementity.setDefaultPickUpDelay();
        this.level.addFreshEntity(itementity);
    }


    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.98F;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public void setHasCoconut(boolean hasCoconut) {
        this.entityData.set(HAS_COCONUT, hasCoconut);
    }

    public boolean hasCoconut() {
        return this.entityData.get(HAS_COCONUT);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_COCONUT, true);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.COCONUT_CRAB_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.COCONUT_CRAB_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.COCONUT_CRAB_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    protected void playCoconutSmashSound() {
        this.playSound(ModSoundEvents.COCONUT_SMASH.get(), 0.2F, 1.0F);
    }

    private static  <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.coconut_crab.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.coconut_crab.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, CoconutCrab::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }


    static class CrabMeleeAttackGoal extends MeleeAttackGoal {
        private final CoconutCrab crab;

        public CrabMeleeAttackGoal(CoconutCrab pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.crab = pMob;
        }

        @Override
        public boolean canUse() {
            return crab.hasCoconut() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return crab.hasCoconut() && super.canContinueToUse();
        }

        @Override
        public void start() {
            if (!crab.hasCoconut()) {
                crab.setAggressive(false);
                this.stop();
            } else {
                super.start();
            }
        }
    }

    static class CrabAvoidGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        private final CoconutCrab crab;

        public CrabAvoidGoal(CoconutCrab pMob, Class<T> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
            super(pMob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier, EntitySelector.NO_SPECTATORS::test);
            this.crab = pMob;
        }

        @Override
        public boolean canUse() {
            return !this.crab.hasCoconut() && super.canUse();
        }
    }

    static class CrabNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        private final CoconutCrab crab;

        public CrabNearestAttackableTargetGoal(CoconutCrab pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
            super(pMob, pTargetType, pRandomInterval, pMustSee, pMustReach, pTargetPredicate);
            this.crab = pMob;
        }

        @Override
        public boolean canUse() {
            return this.crab.hasCoconut() && super.canUse();
        }
    }
}
