package samebutdifferent.ecologics.entity;

import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class Camel extends AbstractDonkeyEntity {
    public Camel(EntityType<? extends AbstractDonkeyEntity> type, World level) {
        super(type, level);
    }

    // ATTRIBUTES & BREEDING

    public static boolean checkCamelSpawnRules(EntityType<Camel> type, WorldAccess level, SpawnReason spawnType, BlockPos pos, Random random) {
        return level.getBlockState(pos.down()).isIn(BlockTags.SAND) && isLightLevelValidForNaturalSpawn(level, pos);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return createAbstractDonkeyAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld level, PassiveEntity mob) {
        return ModEntityTypes.CAMEL.create(level);
    }

    @Override
    public boolean isBreedingItem(ItemStack pStack) {
        return pStack.isOf(Items.DEAD_BUSH) || pStack.isOf(Items.CACTUS) || pStack.isOf(ModItems.PRICKLY_PEAR);
    }

    @Override
    public boolean eatsGrass() {
        return false;
    }


    @Override
    public int getMaxTemper() {
        return 30;
    }

    @Override
    public boolean canBreedWith(AnimalEntity pOtherAnimal) {
        return pOtherAnimal instanceof Camel && this.canBreed() && ((Camel) pOtherAnimal).canBreed();
    }


    @Override
    protected boolean receiveFood(PlayerEntity pPlayer, ItemStack pStack) {
        int increaseAgeAmount = 0;
        int increaseTemperAmount = 0;
        float healAmount = 0.0F;
        boolean willEat = false;
        if (pStack.isOf(Items.DEAD_BUSH) || pStack.isOf(Items.CACTUS)) {
            increaseAgeAmount = 10;
            increaseTemperAmount = 3;
            healAmount = 2.0F;
        } else if (pStack.isOf(ModItems.PRICKLY_PEAR)) {
            increaseAgeAmount = 90;
            increaseTemperAmount = 6;
            healAmount = 10.0F;
            if (this.isTame() && this.getBreedingAge() == 0 && this.canEat()) {
                willEat = true;
                this.lovePlayer(pPlayer);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && healAmount > 0.0F) {
            this.heal(healAmount);
            willEat = true;
        }

        if (this.isBaby() && increaseAgeAmount > 0) {
            this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), 0.0D, 0.0D, 0.0D);
            if (!this.world.isClient) {
                this.growUp(increaseAgeAmount);
            }

            willEat = true;
        }

        if (increaseTemperAmount > 0 && (willEat || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
            willEat = true;
            if (!this.world.isClient) {
                this.addTemper(increaseTemperAmount);
            }
        }

        if (willEat) {
            this.emitGameEvent(GameEvent.MOB_INTERACT, this.getCameraBlockPos());
            if (!this.isSilent()) {
                SoundEvent soundevent = this.getEatSound();
                if (soundevent != null) {
                    this.world.playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatSound(), this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                }
            }
        }

        return willEat;
    }

    // MOVEMENT & CONTROL

    @Override
    public float getMovementSpeed() {
        return this.getPassengerList().size() < 2 ? 0.175F : 0.16F;
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    protected boolean canAddPassenger(Entity pPassenger) {
        return this.getPassengerList().size() < 2;
    }

    @Override
    public ActionResult interactMob(PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getStackInHand(pHand);
        if (!this.isBaby()) {
            if (this.isTame() && pPlayer.shouldCancelInteraction()) {
                this.openInventory(pPlayer);
                return ActionResult.success(this.world.isClient);
            }

            if (this.hasPassengers() && this.getPassengerList().size() >= 2) {
                return super.interactMob(pPlayer, pHand);
            }
        }

        if (!itemstack.isEmpty()) {
            if (this.isBreedingItem(itemstack)) {
                return this.interactHorse(pPlayer, itemstack);
            }

            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.world.isClient);
            }

            if (!this.hasChest() && itemstack.isOf(Blocks.CHEST.asItem())) {
                this.setHasChest(true);
                this.playAddChestSound();
                if (!pPlayer.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                this.onChestedStatusChanged();
                return ActionResult.success(this.world.isClient);
            }

            if (!this.isBaby() && !this.isSaddled() && itemstack.isOf(Items.SADDLE)) {
                this.openInventory(pPlayer);
                return ActionResult.success(this.world.isClient);
            }
        }

        if (this.isBaby()) {
            return super.interactMob(pPlayer, pHand);
        } else {
            this.putPlayerOnBack(pPlayer);
            return ActionResult.success(this.world.isClient);
        }
    }

    @Override
    public void updatePassengerPosition(Entity pPassenger) {
        if (this.hasPassenger(pPassenger)) {
            float lengthwiseOffset = 0.0f;
            final double heightwiseOffset = this.getMountedHeightOffset() + pPassenger.getHeightOffset();
            if (this.getPassengerList().size() > 0) {
                lengthwiseOffset = ((this.getPassengerList().indexOf(pPassenger) == 0) ? 0.2f : -0.6f);
            }
            Vec3d vec = new Vec3d(lengthwiseOffset, 0.0, 0.0).rotateY(-this.getYaw() * 0.017453292f - 1.5707964f);
            pPassenger.setPosition(this.getX() + vec.x, this.getY() + heightwiseOffset, this.getZ() + vec.z);
        }
    }

    @Override
    public double getMountedHeightOffset() {
        return this.getHeight() * 0.8D;
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
        return SoundEvents.ENTITY_LLAMA_ANGRY;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_LLAMA_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENTITY_LLAMA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_LLAMA_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getEatSound() {
        return SoundEvents.ENTITY_LLAMA_EAT;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void playAddChestSound() {
        this.playSound(SoundEvents.ENTITY_LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }
}
