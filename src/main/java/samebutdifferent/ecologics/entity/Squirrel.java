package samebutdifferent.ecologics.entity;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Squirrel extends AnimalEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    private EntityType<? extends ItemEntity> level;

    public Squirrel(EntityType<? extends AnimalEntity> type, World level) {
        super(type, level);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.4F, 1.0F, true);
        this.lookControl = new YawAdjustingLookControl(this, 20);
        this.stepHeight = 1.0F;
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
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(6, new SwimAroundGoal(this, 1.0D, 10));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));


    }


        @Override
        protected EntityNavigation createNavigation(World pLevel) {
            return new Squirrel.SquirrelPathNavigation(this, pLevel);
        }

        @Override
        public boolean canBreatheInWater() {
            return true;
        }

        @Override
        public boolean isPushedByFluids() {
            return false;
        }

        @Override
        public void travel(Vec3d pTravelVector) {
            if (this.canMoveVoluntarily() && this.isTouchingWater()) {
                this.updateVelocity(this.getMovementSpeed(), pTravelVector);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.9D));
            } else {
                super.travel(pTravelVector);
            }


    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    // SOUNDS

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.SQUIRREL_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.SQUIRREL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.SQUIRREL_DEATH;
    }




    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.squirrel.run", true));
        } else if (event.isMoving() && this.isOnGround() || this.isTouchingWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.squirrel.run", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.squirrel.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(10);
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::predicate));
    }



    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    static class SquirrelPathNavigation extends SwimNavigation {

        public SquirrelPathNavigation(Squirrel penguin, World level) {
            super(penguin, level);
        }

        protected boolean isAtValidPosition() {
            return true;
        }

        protected PathNodeNavigator createPathNodeNavigator(int p_149222_) {
            this.nodeMaker = new AmphibiousPathNodeMaker(false);
            return new PathNodeNavigator(this.nodeMaker, p_149222_);
        }

        public boolean isValidPosition(BlockPos p_149224_) {
            return !this.world.getBlockState(p_149224_.down()).isAir();
        }
    }
}


