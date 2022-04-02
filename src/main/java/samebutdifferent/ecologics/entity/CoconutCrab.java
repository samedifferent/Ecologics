package samebutdifferent.ecologics.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
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

public class CoconutCrab extends AnimalEntity implements IAnimatable, Angerable {
    private static final TrackedData<Boolean> HAS_COCONUT = DataTracker.registerData(CoconutCrab.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final UniformIntProvider PERSISTENT_ANGER_TIME = TimeHelper.betweenSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable private UUID persistentAngerTarget;
    private final AnimationFactory factory = new AnimationFactory(this);

    public CoconutCrab(EntityType<? extends AnimalEntity> entityType, World level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld pLevel, PassiveEntity pMob) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack pStack) {
        return false;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new CoconutCrab.CrabAvoidGoal<>(this, PlayerEntity.class, 8.0F, 2.0D, 2.0D));
        this.goalSelector.add(2, new CoconutCrab.CrabMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(3, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new CoconutCrab.CrabNearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(3, new UniversalAngerGoal<>(this, false));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
                // TODO: .add(ForgeMod.REACH_DISTANCE.get(), 3);
    }

    @Override
    public boolean damage(DamageSource pSource, float pAmount) {
        return super.damage(pSource, this.hasCoconut() ? pAmount / 2 : pAmount);
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
        this.stopAnger();
        this.playCoconutSmashSound();
        ItemEntity itementity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.COCONUT_SLICE, 2));
        itementity.setToDefaultPickupDelay();
        this.world.spawnEntity(itementity);
    }


    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    protected float getBaseMovementSpeedMultiplier() {
        return 0.98F;
    }

    @Override
    public boolean handleFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity pPlayer) {
        return false;
    }

    public void setHasCoconut(boolean hasCoconut) {
        this.dataTracker.set(HAS_COCONUT, hasCoconut);
    }

    public boolean hasCoconut() {
        return this.dataTracker.get(HAS_COCONUT);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HAS_COCONUT, true);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.COCONUT_CRAB_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.COCONUT_CRAB_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.COCONUT_CRAB_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

    protected void playCoconutSmashSound() {
        this.playSound(ModSoundEvents.COCONUT_SMASH, 0.2F, 1.0F);
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
    public int getAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setAngryAt(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(PERSISTENT_ANGER_TIME.get(this.random));
    }


    static class CrabMeleeAttackGoal extends MeleeAttackGoal {
        private final CoconutCrab crab;

        public CrabMeleeAttackGoal(CoconutCrab pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.crab = pMob;
        }

        @Override
        public boolean canStart() {
            return crab.hasCoconut() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return crab.hasCoconut() && super.shouldContinue();
        }

        @Override
        public void start() {
            if (!crab.hasCoconut()) {
                crab.setAttacking(false);
                this.stop();
            } else {
                super.start();
            }
        }
    }

    static class CrabAvoidGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final CoconutCrab crab;

        public CrabAvoidGoal(CoconutCrab pMob, Class<T> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
            super(pMob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier, EntityPredicates.EXCEPT_SPECTATOR::test);
            this.crab = pMob;
        }

        @Override
        public boolean canStart() {
            return !this.crab.hasCoconut() && super.canStart();
        }
    }

    static class CrabNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        private final CoconutCrab crab;

        public CrabNearestAttackableTargetGoal(CoconutCrab pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
            super(pMob, pTargetType, pRandomInterval, pMustSee, pMustReach, pTargetPredicate);
            this.crab = pMob;
        }

        @Override
        public boolean canStart() {
            return this.crab.hasCoconut() && super.canStart();
        }
    }
}
