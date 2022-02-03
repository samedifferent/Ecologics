package samebutdifferent.ecologics.registry;

import ca.weblite.objc.Proxy;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.AgaveItem;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.ModBoatItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);

    public static final RegistryObject<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", () -> new CoconutSliceItem(new Item.Properties().tab(Ecologics.TAB).food(Foods.SWEET_BERRIES)));
    public static final RegistryObject<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new ShearsItem(new Item.Properties().tab(Ecologics.TAB).durability(50)));
    public static final RegistryObject<Item> CRAB_MEAT = ITEMS.register("crab_meat", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.COOKED_RABBIT)));
    public static final RegistryObject<Item> TROPICAL_STEW = ITEMS.register("tropical_stew", () -> new CoconutSliceItem(new Item.Properties().stacksTo(1).tab(Ecologics.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> COCONUT_CRAB_SPAWN_EGG = ITEMS.register("coconut_crab_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.COCONUT_CRAB.get(), 15686450, 5845811, new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> CAMEL_SPAWN_EGG = ITEMS.register("camel_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.CAMEL.get(), 15714446, 5321501, new Item.Properties().tab(Ecologics.TAB)));
//    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.PENGUIN.get(), 1315860, 16382457, new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> SANDCASTLE = ITEMS.register("sandcastle", SandcastleBlockItem::new);
    public static final RegistryObject<Item> MUSIC_DISC_COCONUT = ITEMS.register("music_disc_coconut", () -> new RecordItem(10, () -> ModSoundEvents.MUSIC_DISC_COCONUT.get(), new Item.Properties().stacksTo(1).tab(Ecologics.TAB).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> COCONUT_SIGN = ITEMS.register("coconut_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Ecologics.TAB), ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get()));
    public static final RegistryObject<Item> COCONUT_BOAT = ITEMS.register("cocon.ut_boat", () -> new ModBoatItem("coconut", new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final RegistryObject<Item> AGAVE = ITEMS.register("agave", AgaveItem::new);
}
