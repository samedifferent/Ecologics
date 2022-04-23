package samebutdifferent.ecologics.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
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

public class Penguin extends AnimalEntity implements IAnimatable {
    private static final TrackedData<Boolean> PREGNANT = DataTracker.registerData(Penguin.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(Items.COD, Items.COOKED_COD);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Penguin(EntityType<? extends AnimalEntity> type, World level) {
        super(type, level);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.4F, 1.0F, true);
        this.lookControl = new YawAdjustingLookControl(this, 20);
        this.stepHeight = 1.0F;
        this.setCanPickUpLoot(true);
    }

    // ATTRIBUTES & GOALS

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0, this.getStandingEyeHeight() * 0.5F, this.getWidth() * 0.0);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new PenguinMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new PenguinSearchForCodItemGoal(this));
        this.goalSelector.add(7, new PenguinRandomStrollGoal(this, 1.0D));
        this.goalSelector.add(7, new PenguinRandomSwimmingGoal(this, 1.0D, 120));
        this.goalSelector.add(8, new PenguinFillSackGoal(this, 1.0F, 30, 20));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(11, new LookAroundGoal(this));
        this.targetSelector.add(1, new PenguinAttackTargetGoal<>(this, CodEntity.class, false));
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
            this.dropItem(ModItems.PENGUIN_FEATHER, 1);
        }
    }

    @Override
    public void tickMovement() {
        if (this.isPregnant()) {
            if (this.random.nextInt(3000) == 0) {
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
        if (this.world.getNonSpectatingEntities(Penguin.class, this.getBoundingBox().expand(20.0D)).size() > 4) {
            for(PlayerEntity player : this.world.getNonSpectatingEntities(PlayerEntity.class, this.getBoundingBox().expand(10.0D))) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0, true, true));
            }
        }
        super.tickMovement();
    }

    // FISH

    @Override
    public boolean canEquip(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = MobEntity.getPreferredEquipmentSlot(pItemstack);
        if (!this.getEquippedStack(equipmentslot).isEmpty() || this.isBaby()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canEquip(pItemstack);
        }
    }

    @Override
    public boolean canPickupItem(ItemStack pStack) {
        ItemStack itemstack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemstack.isEmpty() && pStack.isOf(Items.COD) && !this.isBaby();
    }

    @Override
    protected void loot(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getStack();
        if (this.canPickupItem(itemstack)) {
            int count = itemstack.getCount();
            if (count > 1) {
                this.dropItemStack(itemstack.split(count - 1));
            }

            this.triggerItemPickedUpByEntityCriteria(pItemEntity);
            this.equipStack(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 2.0F;
            this.sendPickup(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
        }
    }

    private void dropItemStack(ItemStack pStack) {
        ItemEntity itementity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), pStack);
        this.world.spawnEntity(itementity);
    }

    // SOUNDS

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.PENGUIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.PENGUIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.PENGUIN_DEATH;
    }

    // ANIMATION

    private boolean babyIsNearAdult() {
        if (this.isBaby()) {
            for(Penguin penguin : this.world.getNonSpectatingEntities(Penguin.class, this.getBoundingBox().expand(2.0D, 5.0D, 2.0D))) {
                return !penguin.isBaby();
            }
        }
        return false;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isTouchingWater()) {
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
            if (this.world.getBlockState(this.getBlockPos().down()).isOf(Blocks.ICE) && !this.isInLove() && !this.isPregnant()) {
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
    static class PenguinAttackTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        private final Penguin penguin;

        public PenguinAttackTargetGoal(Penguin pMob, Class<T> pTargetType, boolean pMustSee) {
            super(pMob, pTargetType, pMustSee);
            this.penguin = pMob;
        }

        @Override
        public boolean canStart() {
            if (penguin.isBaby() || penguin.isPregnant() || !penguin.getMainHandStack().isEmpty()) {
                return false;
            } else {
                return super.canStart();
            }
        }
    }

    static class PenguinRandomSwimmingGoal extends SwimAroundGoal {
        private final Penguin penguin;

        public PenguinRandomSwimmingGoal(Penguin pMob, double pSpeedModifier, int pInterval) {
            super(pMob, pSpeedModifier, pInterval);
            this.penguin = pMob;
        }

        @Override
        public boolean canStart() {
            if (penguin.isBaby() || penguin.isPregnant() || !penguin.getMainHandStack().isEmpty()) {
                return false;
            } else {
                return super.canStart();
            }
        }
    }

    static class PenguinRandomStrollGoal extends WanderAroundFarGoal {
        private final Penguin penguin;

        public PenguinRandomStrollGoal(Penguin pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.penguin = pMob;
        }

        @Override
        public boolean canStart() {
            if (!penguin.getMainHandStack().isEmpty()) {
                return false;
            } else {
                return super.canStart();
            }
        }
    }

    static class PenguinFillSackGoal extends MoveToTargetPosGoal {
        private final Penguin penguin;
        private boolean reachedTarget;

        public PenguinFillSackGoal(Penguin penguin, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(penguin, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.penguin = penguin;
        }

        @Override
        public boolean canStart() {
            if (penguin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canStart();
            }
        }

        @Override
        public double getDesiredDistanceToTarget() {
            return 2.0D;
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.getTargetPos();
            if (!blockpos.isWithinDistance(this.mob.getPos(), this.getDesiredDistanceToTarget())) {
                this.reachedTarget = false;
                ++this.tryingTime;
                if (this.shouldResetPath()) {
                    this.mob.getNavigation().startMovingTo((double)((float)blockpos.getX()) + 0.5D, blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5D, this.speed);
                }
            } else {
                this.reachedTarget = true;
                --this.tryingTime;
            }

            if (reachedTarget && !penguin.getMainHandStack().isEmpty()) {
                World level = penguin.world;
                if (level.getBlockEntity(targetPos) instanceof BarrelBlockEntity barrel) {
                    this.onReachedTarget(level, barrel);
                    reachedTarget = false;
                }
            }
        }

        @Override
        protected boolean isTargetPos(WorldView pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.isOf(Blocks.BARREL);
        }

        protected void onReachedTarget(World level, BarrelBlockEntity barrel) {
            for (int i = 0; i < barrel.size(); i++) {
                if (this.canPlaceItem(barrel, i, penguin.getMainHandStack())) {
                    penguin.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    level.playSound(null, targetPos, SoundEvents.ENTITY_COD_FLOP, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    break;
                }
            }
        }

        boolean canPlaceItem(BarrelBlockEntity barrel, int index, ItemStack stack) {
            if (barrel.getStack(index).isEmpty()) {
                barrel.setStack(index, new ItemStack(stack.getItem()));
                return true;
            } else if (barrel.getStack(index).isOf(stack.getItem())) {
                int amount = barrel.getStack(index).getCount();
                if (amount < 64) {
                    barrel.setStack(index, new ItemStack(stack.getItem(), amount + 1));
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean hasReached() {
            return this.reachedTarget;
        }
    }

    static class PenguinSearchForCodItemGoal extends Goal {
        private final Penguin penguin;

        public PenguinSearchForCodItemGoal(Penguin penguin) {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
            this.penguin = penguin;
        }

        @Override
        public boolean canStart() {
            if (!penguin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                List<ItemEntity> list = penguin.world.getEntitiesByClass(ItemEntity.class, penguin.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isOf(Items.COD));
                return !list.isEmpty() && penguin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
            }
        }
        
        @Override
        public void tick() {
            List<ItemEntity> list = penguin.world.getEntitiesByClass(ItemEntity.class, penguin.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isOf(Items.COD));
            ItemStack itemstack = penguin.getEquippedStack(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                penguin.getNavigation().startMovingTo(list.get(0), 1.0F);
            }

        }
        
        @Override
        public void start() {
            List<ItemEntity> list = penguin.world.getEntitiesByClass(ItemEntity.class, penguin.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isOf(Items.COD));
            if (!list.isEmpty()) {
                penguin.getNavigation().startMovingTo(list.get(0), 1.0F);
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
        public boolean canStart() {
            if (!penguin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || penguin.isBaby() || penguin.isPregnant()) {
                return false;
            } else {
                return super.canStart();
            }
        }
    }
}
