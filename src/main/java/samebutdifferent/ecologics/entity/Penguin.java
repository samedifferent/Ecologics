package samebutdifferent.ecologics.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModSoundEvents;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.List;

public class Penguin extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(Penguin.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.COOKED_COD);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Penguin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.4F, 1.0F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 20);
        this.maxUpStep = 1.0F;
        this.setCanPickUpLoot(true);
    }

    // ATTRIBUTES & GOALS

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new PenguinMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new PenguinSearchForCodItemGoal(this));
        this.goalSelector.addGoal(7, new PenguinRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new PenguinRandomSwimmingGoal(this, 1.0D, 120));
        this.goalSelector.addGoal(8, new PenguinFillBarrelGoal(this, 1.0F, 30, 20));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new PenguinAttackTargetGoal<>(this, Cod.class, false));
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
            this.spawnAtLocation(ModItems.PENGUIN_FEATHER.get(), 1);
        }
    }

    @Override
    public void aiStep() {
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
        super.aiStep();
    }

    // FISH

    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(pItemstack);
        if (!this.getItemBySlot(equipmentslot).isEmpty() || this.isBaby()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
        }
    }

    @Override
    public boolean canHoldItem(ItemStack pStack) {
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        return itemstack.isEmpty() && pStack.is(Items.COD) && !this.isBaby();
    }

    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (this.canHoldItem(itemstack)) {
            int count = itemstack.getCount();
            if (count > 1) {
                this.dropItemStack(itemstack.split(count - 1));
            }

            this.onItemPickup(pItemEntity);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
            this.take(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
        }
    }

    private void dropItemStack(ItemStack pStack) {
        ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), pStack);
        this.level.addFreshEntity(itementity);
    }

    // SOUNDS

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.PENGUIN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.PENGUIN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.PENGUIN_DEATH.get();
    }

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
        if (this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.swim", true));
        } else if (this.isBaby()) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.waddle", true));
            } else if (this.babyIsNearAdult()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.huddle", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.baby_penguin.idle", true));
            }
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
            if (penguin.isBaby() || penguin.isPregnant() || !penguin.getMainHandItem().isEmpty()) {
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
            if (penguin.isBaby() || penguin.isPregnant() || !penguin.getMainHandItem().isEmpty()) {
                return false;
            } else {
                return super.canUse();
            }
        }
    }

    static class PenguinRandomStrollGoal extends WaterAvoidingRandomStrollGoal {
        private final Penguin penguin;

        public PenguinRandomStrollGoal(Penguin pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.penguin = pMob;
        }

        @Override
        public boolean canUse() {
            if (!penguin.getMainHandItem().isEmpty()) {
                return false;
            } else {
                return super.canUse();
            }
        }
    }

    static class PenguinFillBarrelGoal extends MoveToBlockGoal {
        private final Penguin penguin;
        private boolean reachedTarget;

        public PenguinFillBarrelGoal(Penguin penguin, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(penguin, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.penguin = penguin;
        }

        @Override
        public boolean canUse() {
            if (penguin.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canUse();
            }
        }

        @Override
        public double acceptedDistance() {
            return 2.0D;
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.getMoveToTarget();
            if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
                this.reachedTarget = false;
                ++this.tryTicks;
                if (this.shouldRecalculatePath()) {
                    this.mob.getNavigation().moveTo((double)((float)blockpos.getX()) + 0.5D, blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5D, this.speedModifier);
                }
            } else {
                this.reachedTarget = true;
                --this.tryTicks;
            }

            if (reachedTarget && !penguin.getMainHandItem().isEmpty()) {
                Level level = penguin.level;
                if (level.getBlockEntity(blockPos) instanceof BarrelBlockEntity barrel) {
                    this.onReachedTarget(level, barrel);
                    reachedTarget = false;
                }
            }
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.is(Blocks.BARREL);
        }

        protected void onReachedTarget(Level level, BarrelBlockEntity barrel) {
            for (int i = 0; i < barrel.getContainerSize(); i++) {
                if (this.canPlaceItem(barrel, i, penguin.getMainHandItem())) {
                    penguin.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    level.playSound(null, blockPos, SoundEvents.COD_FLOP, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    break;
                }
            }
        }

        boolean canPlaceItem(BarrelBlockEntity barrel, int index, ItemStack stack) {
            if (barrel.getItem(index).isEmpty()) {
                barrel.setItem(index, new ItemStack(stack.getItem()));
                return true;
            } else if (barrel.getItem(index).is(stack.getItem())) {
                int amount = barrel.getItem(index).getCount();
                if (amount < 64) {
                    barrel.setItem(index, new ItemStack(stack.getItem(), amount + 1));
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean isReachedTarget() {
            return this.reachedTarget;
        }
    }

    static class PenguinSearchForCodItemGoal extends Goal {
        private final Penguin penguin;

        public PenguinSearchForCodItemGoal(Penguin penguin) {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
            this.penguin = penguin;
        }

        @Override
        public boolean canUse() {
            if (!penguin.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                List<ItemEntity> list = penguin.level.getEntitiesOfClass(ItemEntity.class, penguin.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(Items.COD));
                return !list.isEmpty() && penguin.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }
        
        @Override
        public void tick() {
            List<ItemEntity> list = penguin.level.getEntitiesOfClass(ItemEntity.class, penguin.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(Items.COD));
            ItemStack itemstack = penguin.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                penguin.getNavigation().moveTo(list.get(0), 1.0F);
            }

        }
        
        @Override
        public void start() {
            List<ItemEntity> list = penguin.level.getEntitiesOfClass(ItemEntity.class, penguin.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(Items.COD));
            if (!list.isEmpty()) {
                penguin.getNavigation().moveTo(list.get(0), 1.0F);
            }

        }
    }

    static class PenguinMeleeAttackGoal extends MeleeAttackGoal {
        private final Penguin penguin;

        public PenguinMeleeAttackGoal(Penguin pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.penguin = pMob;
        }

        @Override
        public boolean canUse() {
            if (!penguin.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canUse();
            }
        }
    }
}
