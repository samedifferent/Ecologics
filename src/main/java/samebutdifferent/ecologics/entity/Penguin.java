package samebutdifferent.ecologics.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Penguin extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(Penguin.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Penguin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return ModEntityTypes.PENGUIN.get().create(level);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
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
    }

    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(Items.BROWN_WOOL, 1);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isPregnant()) {
            if (this.random.nextInt(500) == 0) {
                if (!this.level.isClientSide) {
                    ServerLevel level = (ServerLevel) this.level;
                    this.setPregnant(false);
                    Penguin penguin = ModEntityTypes.PENGUIN.get().create(level);
                    penguin.setBaby(true);
                    penguin.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                    level.addFreshEntityWithPassengers(penguin);
                    level.broadcastEntityEvent(this, (byte) 18);
                    if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                        level.addFreshEntity(new ExperienceOrb(level, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
                    }
                }
            }
        }
    }

    private <E extends IAnimatable> PlayState swimming(AnimationEvent<E> event) {
        if (isSwimming()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.penguin.swim", true));
            return PlayState.CONTINUE;
        }
        event.getController().markNeedsReload();
        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState movement(AnimationEvent<E> event) {
        if (event.isMoving()) {
            if (this.level.getBlockState(this.blockPosition().below()).is(Blocks.ICE)) {
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
        data.addAnimationController(new AnimationController<>(this, "swimming_controller", 0, this::swimming));
        data.setResetSpeedInTicks(5);
        data.addAnimationController(new AnimationController<>(this, "movement_controller", 5, this::movement));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
