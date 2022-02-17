package samebutdifferent.ecologics.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.Random;

public class Camel extends AbstractChestedHorse {
    public Camel(EntityType<? extends AbstractChestedHorse> type, Level level) {
        super(type, level);
    }

    // ATTRIBUTES & BREEDING

    public static boolean checkCamelSpawnRules(EntityType<Camel> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, Random random) {
        return level.getBlockState(pos.below()).is(BlockTags.SAND) && isBrightEnoughToSpawn(level, pos);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createBaseChestedHorseAttributes().add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return ModEntityTypes.CAMEL.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.DEAD_BUSH) || pStack.is(Items.CACTUS) || pStack.is(ModItems.PRICKLY_PEAR.get());
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }


    @Override
    public int getMaxTemper() {
        return 30;
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        return pOtherAnimal instanceof Camel && this.canParent() && ((Camel) pOtherAnimal).canParent();
    }


    @Override
    protected boolean handleEating(Player pPlayer, ItemStack pStack) {
        int increaseAgeAmount = 0;
        int increaseTemperAmount = 0;
        float healAmount = 0.0F;
        boolean willEat = false;
        if (pStack.is(Items.DEAD_BUSH) || pStack.is(Items.CACTUS)) {
            increaseAgeAmount = 10;
            increaseTemperAmount = 3;
            healAmount = 2.0F;
        } else if (pStack.is(ModItems.PRICKLY_PEAR.get())) {
            increaseAgeAmount = 90;
            increaseTemperAmount = 6;
            healAmount = 10.0F;
            if (this.isTamed() && this.getAge() == 0 && this.canFallInLove()) {
                willEat = true;
                this.setInLove(pPlayer);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && healAmount > 0.0F) {
            this.heal(healAmount);
            willEat = true;
        }

        if (this.isBaby() && increaseAgeAmount > 0) {
            this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
            if (!this.level.isClientSide) {
                this.ageUp(increaseAgeAmount);
            }

            willEat = true;
        }

        if (increaseTemperAmount > 0 && (willEat || !this.isTamed()) && this.getTemper() < this.getMaxTemper()) {
            willEat = true;
            if (!this.level.isClientSide) {
                this.modifyTemper(increaseTemperAmount);
            }
        }

        if (willEat) {
            this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
            if (!this.isSilent()) {
                SoundEvent soundevent = this.getEatingSound();
                if (soundevent != null) {
                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                }
            }
        }

        return willEat;
    }

    // MOVEMENT & CONTROL

    @Override
    public float getSpeed() {
        return this.getPassengers().size() < 2 ? 0.175F : 0.16F;
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    protected boolean canAddPassenger(Entity pPassenger) {
        return this.getPassengers().size() < 2;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!this.isBaby()) {
            if (this.isTamed() && pPlayer.isSecondaryUseActive()) {
                this.openInventory(pPlayer);
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }

            if (this.isVehicle() && this.getPassengers().size() >= 2) {
                return super.mobInteract(pPlayer, pHand);
            }
        }

        if (!itemstack.isEmpty()) {
            if (this.isFood(itemstack)) {
                return this.fedFood(pPlayer, itemstack);
            }

            if (!this.isTamed()) {
                this.makeMad();
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }

            if (!this.hasChest() && itemstack.is(Blocks.CHEST.asItem())) {
                this.setChest(true);
                this.playChestEquipsSound();
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.createInventory();
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }

            if (!this.isBaby() && !this.isSaddled() && itemstack.is(Items.SADDLE)) {
                this.openInventory(pPlayer);
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }

        if (this.isBaby()) {
            return super.mobInteract(pPlayer, pHand);
        } else {
            this.doPlayerRide(pPlayer);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
    }

    @Override
    public void positionRider(Entity pPassenger) {
        if (this.hasPassenger(pPassenger)) {
            float lengthwiseOffset = 0.0f;
            final double heightwiseOffset = this.getPassengersRidingOffset() + pPassenger.getMyRidingOffset();
            if (this.getPassengers().size() > 0) {
                lengthwiseOffset = ((this.getPassengers().indexOf(pPassenger) == 0) ? 0.2f : -0.6f);
            }
            Vec3 vec = new Vec3(lengthwiseOffset, 0.0, 0.0).yRot(-this.getYRot() * 0.017453292f - 1.5707964f);
            pPassenger.setPos(this.getX() + vec.x, this.getY() + heightwiseOffset, this.getZ() + vec.z);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return this.getBbHeight() * 0.8D;
    }

    // INVENTORY & CHESTS

    @Override
    public int getInventoryColumns() {
        return 3;
    }

    @Override
    protected int getInventorySize() {
        return this.hasChest() ? 2 + 3 * this.getInventoryColumns() : super.getInventorySize();
    }

    // SOUNDS

    @Override
    protected float getSoundVolume() {
        return 0.8F;
    }

    @Override
    protected SoundEvent getAngrySound() {
        return SoundEvents.LLAMA_ANGRY;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.LLAMA_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.LLAMA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.LLAMA_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getEatingSound() {
        return SoundEvents.LLAMA_EAT;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.LLAMA_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void playChestEquipsSound() {
        this.playSound(SoundEvents.LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }
}
