package samebutdifferent.ecologics.block.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;

public class PotBlockEntity extends BlockEntity implements Clearable {
    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

    public PotBlockEntity() {
        super(ModBlockEntityTypes.POT.get());
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public boolean addItem(ItemStack pStack) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.items.set(i, pStack.split(1));
                this.markUpdated();
                PotBlock.signalItemAdded(this.getLevel(), this.getBlockPos(), this.getBlockState());
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public void load(BlockState state, CompoundTag pTag) {
        super.load(state, pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        ContainerHelper.saveAllItems(tag, this.items, true);
        return tag;
    }

    public int getRedstoneSignal() {
        int signal = 0;
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) {
                signal = signal + 1;
            }
        }
        return signal;
    }
}
