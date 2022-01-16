package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);

    public static final RegistryObject<Item> COCONUT_HUSK = ITEMS.register("coconut_husk", () -> new Item(new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.APPLE)));
}
