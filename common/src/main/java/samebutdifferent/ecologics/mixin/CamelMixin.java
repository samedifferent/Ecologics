package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.config.ConfigCommon;
import samebutdifferent.ecologics.entity.data.CamelChestDataHelper;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;
import samebutdifferent.ecologics.registry.ModSoundEvents;

@Mixin(Camel.class)
public class CamelMixin extends AbstractHorse implements CamelChestDataHolder 
{
    protected CamelMixin(EntityType<? extends AbstractHorse> etype, Level level) {
        super(etype, level);
    }
    
    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
    protected void injectAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
    	compound.putBoolean("ChestedCamel", this.hasChest());
        if (this.hasChest()) {
            ListTag listTag = new ListTag();
            for (int i = 1; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemStack = this.inventory.getItem(i);
                if (itemStack.isEmpty()) continue;
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putByte("Slot", (byte)(i - 1));
                listTag.add(itemStack.save(this.registryAccess(), compoundTag));
            }
            compound.put("Items", listTag);
        }
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
    protected void injectReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.setChest(compound.getBoolean("ChestedCamel"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag listTag = compound.getList("Items", 10);
            for (int i = 0; i < listTag.size(); ++i) {
                CompoundTag compoundTag = listTag.getCompound(i);
                int j = compoundTag.getByte("Slot") & 0xFF;
                if (j >= this.inventory.getContainerSize() - 1) continue;
                this.inventory.setItem(j + 1, ItemStack.parse(this.registryAccess(), compoundTag).orElse(ItemStack.EMPTY));
            }
        }
    }
    
    @Inject(method = "canAddPassenger(Lnet/minecraft/world/entity/Entity;)Z", at = @At(value = "RETURN"), cancellable = true)
    private void canAddPassenger(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (hasChest()) {
            cir.setReturnValue(this.getPassengers().size() <= 1);
        }
    }
    
    //@Inject(method = "getSlot(I)Lnet/minecraft/world/entity/SlotAccess;", at = @At(value = "RETURN"), cancellable = true)
    @Override
    public SlotAccess getSlot(int slot) {
        if (slot == AbstractHorse.CHEST_SLOT_OFFSET) {
            return new SlotAccess() {
                @Override
                public ItemStack get() {
                    return hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
                }

                @Override
                public boolean set(ItemStack $$0) {
                    if ($$0.isEmpty()) {
                        if (hasChest()) {
                            setChest(false);
                            createInventory();
                        }
                        return true;
                    } else if ($$0.is(Items.CHEST)) {
                        if (!hasChest()) {
                            setChest(true);
                            createInventory();
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            };
        }
        return super.getSlot(slot);
    }
    
    @Override
    public void dropEquipment() {
        super.dropEquipment();
        if (this.hasChest()) {
            this.spawnAtLocation(Blocks.CHEST);
            this.setChest(false);
        }
    }
    
    @Override
    public int getInventoryColumns() {
        return this.hasChest() ? 5 : 0;
    }
    
    @Inject(method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/camel/Camel;isFood(Lnet/minecraft/world/item/ItemStack;)Z", shift = Shift.BEFORE), cancellable = true)
    private void injectMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
    	if (!ConfigCommon.getCamelChestsEnabled()) {
    		return; // If disabled, don't proceed.
    	}
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.hasChest() && itemstack.is(Items.CHEST) && !this.isBaby()) {
            if (this.level().isClientSide()) {
                player.swing(hand);
            }
            else {
                this.equipChest(player, itemstack);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    private void equipChest(Player player, ItemStack stack) {
        this.setChest(true);
        this.playChestEquipsSound();
        stack.consume(1, player);
        this.createInventory();
    }
    
    protected void playChestEquipsSound() {
        this.playSound(ModSoundEvents.CAMEL_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }
    
    @Override
    public boolean hasChest() {
    	if (!ConfigCommon.getCamelChestsEnabled()) {
    		return false;
    	}
        return CamelChestDataHelper.hasChest((Camel)(Object)this);
    }
    
    @Override
    public void setChest(boolean hasChest) {
    	CamelChestDataHelper.setChest((Camel)(Object)this, hasChest);
    }
}
