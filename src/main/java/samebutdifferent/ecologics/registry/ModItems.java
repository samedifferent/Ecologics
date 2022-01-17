package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutSliceItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);

    public static final RegistryObject<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", CoconutSliceItem::new);
    public static final RegistryObject<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new ShearsItem(new Item.Properties().tab(Ecologics.TAB).durability(100)));
    public static final RegistryObject<Item> SMOKED_CRAB_CLAW = ITEMS.register("smoked_crab_claw", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.COOKED_RABBIT)));
    public static final RegistryObject<Item> TROPICAL_STEW = ITEMS.register("tropical_stew", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(Ecologics.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> SQUIRREL_SPAWN_EGG = ITEMS.register("squirrel_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.SQUIRREL.get(), 10051392, 15720061, new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> COCONUT_CRAB_SPAWN_EGG = ITEMS.register("coconut_crab_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.COCONUT_CRAB.get(), 15686450, 5845811, new Item.Properties().tab(Ecologics.TAB)));

}
