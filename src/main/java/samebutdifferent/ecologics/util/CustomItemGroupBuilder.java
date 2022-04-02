package samebutdifferent.ecologics.util;

import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CustomItemGroupBuilder {
    private final String name;
    private Supplier<ItemStack> stackSupplier = () -> ItemStack.EMPTY;
    private BiConsumer<List<ItemStack>, ItemGroup> stacksForDisplay;

    private CustomItemGroupBuilder(String name) {
        this.name = name;
    }

    public static CustomItemGroupBuilder create(String name) {
        return new CustomItemGroupBuilder(name);
    }

    public static ItemGroup build(String name, Supplier<ItemStack> stackSupplier) {
        return new CustomItemGroupBuilder(name).icon(stackSupplier).build();
    }

    public CustomItemGroupBuilder icon(Supplier<ItemStack> stackSupplier) {
        this.stackSupplier = stackSupplier;
        return this;
    }

    public ItemGroup build() {
        ((ItemGroupExtensions) ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return new ItemGroup(ItemGroup.GROUPS.length - 1, this.name) {
            @Override
            public ItemStack createIcon() {
                return stackSupplier.get();
            }

            @Override
            public void appendStacks(DefaultedList<ItemStack> stacks) {
                if (stacksForDisplay != null) {
                    stacksForDisplay.accept(stacks, this);
                    return;
                }

                super.appendStacks(stacks);
            }
        };
    }

}
