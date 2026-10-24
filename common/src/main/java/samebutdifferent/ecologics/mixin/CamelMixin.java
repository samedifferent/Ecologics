package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import samebutdifferent.ecologics.entity.data.CamelChestDataHelper;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(Camel.class)
public class CamelMixin extends AbstractHorse implements CamelChestDataHolder 
{
    protected CamelMixin(EntityType<? extends AbstractHorse> etype, Level level) {
        super(etype, level);
    }
    
    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueOutput;)V", at = @At(value = "TAIL"))
    protected void injectAddAdditionalSaveData(ValueOutput valueIO, CallbackInfo ci) {
        valueIO.putBoolean("ChestedCamel", this.hasChest());
        if (this.hasChest()) {
            ValueOutput.TypedOutputList<ItemStackWithSlot> $$1 = valueIO.list("Items", ItemStackWithSlot.CODEC);
            for (int $$2 = 0; $$2 < this.inventory.getContainerSize(); $$2++) {
                ItemStack $$3 = this.inventory.getItem($$2);
                if (!$$3.isEmpty()) {
                    $$1.add(new ItemStackWithSlot($$2, $$3));
                }
            }
        }
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V", at = @At(value = "TAIL"))
    protected void injectReadAdditionalSaveData(ValueInput valueIO, CallbackInfo ci) {
        this.setChest(valueIO.getBooleanOr("ChestedCamel", false));
        this.createInventory();
        if (this.hasChest()) {
            for (ItemStackWithSlot $$1 : valueIO.listOrEmpty("Items", ItemStackWithSlot.CODEC)) {
                if ($$1.isValidInContainer(this.inventory.getContainerSize())) {
                    this.inventory.setItem($$1.slot(), $$1.stack());
                }
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
    public void dropEquipment(ServerLevel level) {
        super.dropEquipment(level);
        if (this.hasChest()) {
            this.spawnAtLocation(level, Blocks.CHEST);
            this.setChest(false);
        }
    }
    
    @Override
    public int getInventoryColumns() {
        return this.hasChest() ? 5 : 0;
    }
    
    @Inject(method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/camel/Camel;isFood(Lnet/minecraft/world/item/ItemStack;)Z", shift = Shift.BEFORE), cancellable = true)
    private void injectMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.hasChest() && itemstack.is(Items.CHEST) && !this.isBaby()) {
            if (this.level().isClientSide()) {
                player.swing(hand, SwingAnimation.DEFAULT, true);
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
        this.playSound(SoundEvents.LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }
    
    @Override
    public boolean hasChest() {
        return CamelChestDataHelper.hasChest((Camel)(Object)this);
    }
    
    @Override
    public void setChest(boolean hasChest) {
    	CamelChestDataHelper.setChest((Camel)(Object)this, hasChest);
    }
}
