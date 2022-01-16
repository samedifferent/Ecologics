package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutSliceItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);

    public static final RegistryObject<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", CoconutSliceItem::new);
}
