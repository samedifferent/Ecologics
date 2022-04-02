package samebutdifferent.ecologics.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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

public class Squirrel extends AnimalEntity implements IAnimatable {
    private static final TrackedData<Byte> DATA_FLAGS_ID = DataTracker.registerData(Squirrel.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> DATA_TRUSTING = DataTracker.registerData(Squirrel.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient TEMPT_INGREDIENT = Ingredient.ofItems(ModItems.WALNUT);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Squirrel(EntityType<? extends AnimalEntity> type, World level) {
        super(type, level);
        this.setCanPickUpLoot(true);
    }

    // ATTRIBUTES, GOALS, DATA

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4F);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.0D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, TEMPT_INGREDIENT, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new SquirrelSearchForSaplingsGoal(this));
        this.goalSelector.add(6, new SquirrelPlantSaplingGoal(this, 1.0F, 8, 4));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_FLAGS_ID, (byte)0);
        this.dataTracker.startTracking(DATA_TRUSTING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound pCompound) {
        super.writeCustomDataToNbt(pCompound);
        pCompound.putBoolean("Trusting", this.isTrusting());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound pCompound) {
        super.readCustomDataFromNbt(pCompound);
        this.setTrusting(pCompound.getBoolean("Trusting"));
    }

    // BREEDING & TRUSTING

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld pLevel, PassiveEntity pMob) {
        return ModEntityTypes.SQUIRREL.create(pLevel);
    }

    @Override
    public boolean isBreedingItem(ItemStack pStack) {
        return pStack.isOf(Items.HONEYCOMB);
    }

    boolean isTrusting() {
        return this.dataTracker.get(DATA_TRUSTING);
    }

    private void setTrusting(boolean pTrusting) {
        this.dataTracker.set(DATA_TRUSTING, pTrusting);
    }

    @Override
    public ActionResult interactMob(PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getStackInHand(pHand);
        if (!this.isTrusting() && TEMPT_INGREDIENT.test(itemstack)) {
            this.eat(pPlayer, pHand, itemstack);
            if (!this.world.isClient) {
                if (this.random.nextInt(3) == 0) {
                    this.setTrusting(true);
                    this.spawnTrustingParticles(true);
                    this.world.sendEntityStatus(this, (byte)41);
                } else {
                    this.spawnTrustingParticles(false);
                    this.world.sendEntityStatus(this, (byte)40);
                }
            }
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(pPlayer, pHand);
        }
    }

    @Override
    public void handleStatus(byte pId) {
        if (pId == 41) {
            this.spawnTrustingParticles(true);
        } else if (pId == 40) {
            this.spawnTrustingParticles(false);
        } else {
            super.handleStatus(pId);
        }
    }

    private void spawnTrustingParticles(boolean isTrusting) {
        ParticleEffect particleoptions = ParticleTypes.HEART;
        if (!isTrusting) {
            particleoptions = ParticleTypes.SMOKE;
        }

        for(int i = 0; i < 7; ++i) {
            double x = this.random.nextGaussian() * 0.02D;
            double y = this.random.nextGaussian() * 0.02D;
            double z = this.random.nextGaussian() * 0.02D;
            this.world.addParticle(particleoptions, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), x, y, z);
        }
    }

    // FISH

    @Override
    public boolean canEquip(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = MobEntity.getPreferredEquipmentSlot(pItemstack);
        if (!this.getEquippedStack(equipmentslot).isEmpty() || this.isBaby() || !this.isTrusting()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canEquip(pItemstack);
        }
    }

    @Override
    public boolean canPickupItem(ItemStack pStack) {
        ItemStack itemstack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemstack.isEmpty() && pStack.isIn(ItemTags.SAPLINGS) && !this.isBaby() && this.isTrusting();
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

    // MOVEMENT

    @Override
    protected EntityNavigation createNavigation(World pLevel) {
        return new SpiderNavigation(this, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean isClimbing() {
        return (this.dataTracker.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean pClimbing) {
        byte flag = this.dataTracker.get(DATA_FLAGS_ID);
        if (pClimbing) {
            flag = (byte)(flag | 1);
        } else {
            flag = (byte)(flag & -2);
        }

        this.dataTracker.set(DATA_FLAGS_ID, flag);
    }


    @Override
    public boolean handleFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity pPlayer) {
        return this.isTrusting();
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


    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (this.isClimbing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.squirrel.climb", true));
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

    static class SquirrelSearchForSaplingsGoal extends Goal {
        private final Squirrel squirrel;

        public SquirrelSearchForSaplingsGoal(Squirrel squirrel) {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
            this.squirrel = squirrel;
        }

        @Override
        public boolean canStart() {
            if (!squirrel.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || squirrel.isBaby() || !squirrel.isTrusting()) {
                return false;
            } else {
                List<ItemEntity> list = squirrel.world.getEntitiesByClass(ItemEntity.class, squirrel.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isIn(ItemTags.SAPLINGS));
                return !list.isEmpty() && squirrel.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        @Override
        public void tick() {
            List<ItemEntity> list = squirrel.world.getEntitiesByClass(ItemEntity.class, squirrel.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isIn(ItemTags.SAPLINGS));
            ItemStack itemstack = squirrel.getEquippedStack(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                squirrel.getNavigation().startMovingTo(list.get(0), 1.0F);
            }

        }

        @Override
        public void start() {
            List<ItemEntity> list = squirrel.world.getEntitiesByClass(ItemEntity.class, squirrel.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().isIn(ItemTags.SAPLINGS));
            if (!list.isEmpty()) {
                squirrel.getNavigation().startMovingTo(list.get(0), 1.0F);
            }

        }
    }

    static class SquirrelPlantSaplingGoal extends MoveToTargetPosGoal {
        private final Squirrel squirrel;
        private boolean reachedTarget;

        public SquirrelPlantSaplingGoal(Squirrel squirrel, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(squirrel, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.squirrel = squirrel;
        }

        @Override
        public boolean canStart() {
            if (squirrel.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || squirrel.isBaby() || !squirrel.isTrusting()) {
                return false;
            } else {
                return super.canStart();
            }
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

            if (reachedTarget && !squirrel.getMainHandStack().isEmpty()) {
                World level = squirrel.world;
                if (level.getBlockState(targetPos).isIn(BlockTags.DIRT)) {
                    this.onReachedTarget(level);
                    reachedTarget = false;
                }
            }
        }

        @Override
        public double getDesiredDistanceToTarget() {
            return 1.5D;
        }

        @Override
        protected boolean isTargetPos(WorldView pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.isIn(BlockTags.DIRT) && pLevel.getBlockState(pPos.up()).isAir();
        }

        protected void onReachedTarget(World level) {
            if (squirrel.getMainHandStack().getItem() instanceof BlockItem item) {
                level.setBlockState(targetPos.up(), item.getBlock().getDefaultState());
                level.playSound(null, targetPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                squirrel.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        }

        @Override
        protected boolean hasReached() {
            return this.reachedTarget;
        }
    }
}