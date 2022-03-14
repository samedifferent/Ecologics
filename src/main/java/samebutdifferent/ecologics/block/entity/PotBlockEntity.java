package samebutdifferent.ecologics.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Clearable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;

public class PotBlockEntity extends BlockEntity implements Clearable {
    private DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public PotBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntityTypes.POT, pWorldPosition, pBlockState);
    }

    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }

    public boolean addItem(ItemStack pStack) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.items.set(i, pStack.split(1));
                this.markUpdated();
                PotBlock.signalItemAdded(this.getWorld(), this.getPos(), this.getCachedState());
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        super.readNbt(pTag);
        this.items = DefaultedList.ofSize(this.getContainerSize(), ItemStack.EMPTY);
        Inventories.readNbt(pTag, this.items);
    }

    @Override
    protected void writeNbt(NbtCompound pTag) {
        super.writeNbt(pTag);
        Inventories.writeNbt(pTag, this.items);
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
