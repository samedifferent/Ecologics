package samebutdifferent.ecologics.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModTags;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Random;

public class Penguin extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(Penguin.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Penguin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.4F, 1.0F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 20);
        this.maxUpStep = 1.0F;
    }

    // ATTRIBUTES & GOALS

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public static boolean checkPenguinSpawnRules(EntityType<Penguin> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, Random random) {
        return level.getBlockState(pos.below()).is(ModTags.ModBlockTags.PENGUINS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
    }

    @Override
    public void baseTick() {
        int airSupply = this.getAirSupply();
        super.baseTick();
        if (!this.isNoAi()) {
            this.handleAirSupply(airSupply);
        }

    }

    @Override
    public int getMaxAirSupply() {
        return 6000;
    }

    protected void handleAirSupply(int airSupply) {
        if (this.isAlive() && !this.isInWaterRainOrBubble()) {
            this.setAirSupply(airSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DRY_OUT, 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
        }

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PenguinFloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new PenguinFollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new PenguinRandomSwimmingGoal(this, 1.0D, 120));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new PenguinAttackTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(1, new PenguinAttackTargetGoal<>(this, Salmon.class, false));
    }

    // MOVEMENT

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new Penguin.PenguinPathNavigation(this, pLevel);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(pTravelVector);
        }

    }
    
    // BREEDING

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return ModEntityTypes.PENGUIN.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

    public boolean isPregnant() {
        return this.entityData.get(PREGNANT);
    }

    public void setPregnant(boolean isPregnant) {
        this.entityData.set(PREGNANT, isPregnant);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PREGNANT, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsPregnant", this.isPregnant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setPregnant(pCompound.getBoolean("IsPregnant"));
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel level, Animal otherParent) {
        ServerPlayer serverplayer = this.getLoveCause();
        if (serverplayer == null && otherParent.getLoveCause() != null) {
            serverplayer = otherParent.getLoveCause();
        }

        if (serverplayer != null) {
            serverplayer.awardStat(Stats.ANIMALS_BRED);
            CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this, otherParent, null);
        }

        this.setAge(6000);
        otherParent.setAge(6000);
        this.resetLove();
        otherParent.resetLove();
        level.broadcastEntityEvent(this, (byte)18);
        this.setPregnant(true);
        if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            level.addFreshEntity(new ExperienceOrb(level, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(Items.GRAY_WOOL, 1);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isPregnant()) {
            if (this.random.nextInt(3000) == 0) {
                if (!this.level.isClientSide) {
                    ServerLevel level = (ServerLevel) this.level;
                    this.setPregnant(false);
                    Penguin penguin = ModEntityTypes.PENGUIN.get().create(level);
                    penguin.setBaby(true);
                    penguin.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                    level.addFreshEntityWithPassengers(penguin);
                    level.broadcastEntityEvent(this, (byte) 18);
                }
            }
        }
        if (this.level.getEntitiesOfClass(Penguin.class, this.getBoundingBox().inflate(20.0D)).size() > 4) {
            for(Player player : this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(10.0D))) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, true, true));
            }
        }
    }

    // SOUNDS


    // ANIMATION

    private boolean babyIsNearAdult() {
        if (this.isBaby()) {
            for(Penguin penguin : this.level.getEntitiesOfClass(Penguin.class, this.getBoundingBox().inflate(2.0D, 5.0D, 2.0D))) {
                return !penguin.isBaby();
            }
        }
        return false;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isBaby()) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.waddle", true));
            } else if (this.babyIsNearAdult()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.huddle", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.idle", true));
            }
        } else if (this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.swim", true));
        } else if (event.isMoving()) {
            if (this.level.getBlockState(this.blockPosition().below()).is(Blocks.ICE) && !this.isInLove() && !this.isPregnant()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.slide", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.waddle", true));
            }
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(5);
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    static class PenguinPathNavigation extends WaterBoundPathNavigation {

        public PenguinPathNavigation(Penguin penguin, Level level) {
            super(penguin, level);
        }

        protected boolean canUpdatePath() {
            return true;
        }

        protected PathFinder createPathFinder(int p_149222_) {
            this.nodeEvaluator = new AmphibiousNodeEvaluator(false);
            return new PathFinder(this.nodeEvaluator, p_149222_);
        }

        public boolean isStableDestination(BlockPos p_149224_) {
            return !this.level.getBlockState(p_149224_.below()).isAir();
        }
    }
    static class PenguinAttackTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        private final Penguin penguin;

        public PenguinAttackTargetGoal(Penguin pMob, Class<T> pTargetType, boolean pMustSee) {
            super(pMob, pTargetType, pMustSee);
            this.penguin = pMob;
        }

        @Override
        public boolean canUse() {
            if (penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canUse();
            }
        }
    }

    static class PenguinRandomSwimmingGoal extends RandomSwimmingGoal {
        private final Penguin penguin;

        public PenguinRandomSwimmingGoal(Penguin pMob, double pSpeedModifier, int pInterval) {
            super(pMob, pSpeedModifier, pInterval);
            this.penguin = pMob;
        }

        @Override
        public boolean canUse() {
            if (penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canUse();
            }
        }
    }

    static class PenguinFloatGoal extends FloatGoal {
        private final Penguin penguin;

        public PenguinFloatGoal(Penguin pMob) {
            super(pMob);
            this.penguin = pMob;
        }

        @Override
        public boolean canUse() {
            if (penguin.isBaby() || penguin.isPregnant()) {
                return super.canUse();
            } else {
                return false;
            }
        }
    }

    static class PenguinFollowParentGoal extends FollowParentGoal {
        private final Animal animal;
        private Animal parent;

        public PenguinFollowParentGoal(Animal pAnimal, double pSpeedModifier) {
            super(pAnimal, pSpeedModifier);
            this.animal = pAnimal;
        }

        @Override
        public boolean canUse() {
            if (this.animal.getAge() >= 0) {
                return false;
            } else {
                List<? extends Animal> list = this.animal.level.getEntitiesOfClass(this.animal.getClass(), this.animal.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));
                Animal animal = null;
                double d0 = Double.MAX_VALUE;

                for(Animal animal1 : list) {
                    if (animal1.getAge() >= 0) {
                        double d1 = this.animal.distanceToSqr(animal1);
                        if (!(d1 > d0)) {
                            d0 = d1;
                            animal = animal1;
                        }
                    }
                }

                if (animal == null) {
                    return false;
                } else if (d0 < 9.0D) {
                    return false;
                } else {
                    this.parent = animal;
                    return true;
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            if (this.animal.getAge() >= 0) {
                return false;
            } else if (!this.parent.isAlive()) {
                return false;
            } else if (this.parent.isInWater()) {
                return false;
            } else {
                double d0 = this.animal.distanceToSqr(this.parent);
                return !(d0 < 9.0D) && !(d0 > 256.0D);
            }
        }
    }
}
