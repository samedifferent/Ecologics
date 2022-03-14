package samebutdifferent.ecologics.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.AmphibiousPathNodeMaker;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.entity.*;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Penguin extends AnimalEntity implements IAnimatable {
    private static final TrackedData<Boolean> PREGNANT = DataTracker.registerData(Penguin.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(Items.COD, Items.SALMON);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Penguin(EntityType<? extends AnimalEntity> type, World level) {
        super(type, level);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.4F, 1.0F, true);
        this.lookControl = new YawAdjustingLookControl(this, 20);
        this.stepHeight = 1.0F;
    }

    // ATTRIBUTES & GOALS

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public void baseTick() {
        int airSupply = this.getAir();
        super.baseTick();
        if (!this.isAiDisabled()) {
            this.handleAirSupply(airSupply);
        }

    }

    @Override
    public int getMaxAir() {
        return 6000;
    }

    protected void handleAirSupply(int airSupply) {
        if (this.isAlive() && !this.isWet()) {
            this.setAir(airSupply - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.damage(DamageSource.DRYOUT, 2.0F);
            }
        } else {
            this.setAir(this.getMaxAir());
        }

    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(6, new SwimAroundGoal(this, 1.0D, 10));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, CodEntity.class, false));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SalmonEntity.class, false));
    }

    // MOVEMENT

    @Override
    protected EntityNavigation createNavigation(World pLevel) {
        return new Penguin.PenguinPathNavigation(this, pLevel);
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
    
    // BREEDING

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld level, PassiveEntity mob) {
        return ModEntityTypes.PENGUIN.create(level);
    }

    @Override
    public boolean isBreedingItem(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

    public boolean isPregnant() {
        return this.dataTracker.get(PREGNANT);
    }

    public void setPregnant(boolean isPregnant) {
        this.dataTracker.set(PREGNANT, isPregnant);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(PREGNANT, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound pCompound) {
        super.writeCustomDataToNbt(pCompound);
        pCompound.putBoolean("IsPregnant", this.isPregnant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound pCompound) {
        super.readCustomDataFromNbt(pCompound);
        this.setPregnant(pCompound.getBoolean("IsPregnant"));
    }

    @Override
    public void breed(ServerWorld level, AnimalEntity otherParent) {
        ServerPlayerEntity serverplayer = this.getLovingPlayer();
        if (serverplayer == null && otherParent.getLovingPlayer() != null) {
            serverplayer = otherParent.getLovingPlayer();
        }

        if (serverplayer != null) {
            serverplayer.incrementStat(Stats.ANIMALS_BRED);
            Criteria.BRED_ANIMALS.trigger(serverplayer, this, otherParent, null);
        }

        this.setBreedingAge(6000);
        otherParent.setBreedingAge(6000);
        this.resetLoveTicks();
        otherParent.resetLoveTicks();
        level.sendEntityStatus(this, (byte)18);
        this.setPregnant(true);
        if (level.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            level.spawnEntity(new ExperienceOrbEntity(level, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    @Override
    protected void onGrowUp() {
        super.onGrowUp();
        if (!this.isBaby() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropItem(Items.GRAY_WOOL, 1);
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.isPregnant()) {
            if (this.random.nextInt(1500) == 0) {
                if (!this.world.isClient) {
                    ServerWorld level = (ServerWorld) this.world;
                    this.setPregnant(false);
                    Penguin penguin = ModEntityTypes.PENGUIN.create(level);
                    penguin.setBaby(true);
                    penguin.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                    level.spawnEntityAndPassengers(penguin);
                    level.sendEntityStatus(this, (byte) 18);
                }
            }
        }
    }



    // SOUNDS


    // ANIMATION

    private boolean babyIsNearAdult() {
        if (this.isBaby()) {
            for(Penguin penguin : Penguin.this.world.getNonSpectatingEntities(Penguin.class, Penguin.this.getBoundingBox().expand(2.0D, 5.0D, 2.0D))) {
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
        } else if (event.isMoving()) {
            if (this.isTouchingWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.swim", true));
            } else if (this.world.getBlockState(this.getBlockPos().down()).isOf(Blocks.ICE) && !this.isInLove() && !this.isPregnant()) {
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

    static class PenguinPathNavigation extends SwimNavigation {

        public PenguinPathNavigation(Penguin penguin, World level) {
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
