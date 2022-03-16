package samebutdifferent.ecologics.registry;

import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.ModBoatItem;
import samebutdifferent.ecologics.item.PricklyPearItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;

public class ModItems {
    public static final Item COCONUT_SLICE = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "coconut_slice"), new CoconutSliceItem(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.SWEET_BERRIES)));
    public static final Item CRAB_CLAW = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "crab_claw"), new ShearsItem(new Item.Settings().group(Ecologics.TAB).maxDamage(50)));
    public static final Item CRAB_MEAT = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "crab_meat"), new Item(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.COOKED_RABBIT)));
    public static final Item TROPICAL_STEW = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "tropical_stew"), new CoconutSliceItem(new Item.Settings().maxCount(1).group(Ecologics.TAB).food(FoodComponents.RABBIT_STEW)));
    public static final Item COCONUT_CRAB_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "coconut_crab_spawn_egg"), new SpawnEggItem(ModEntityTypes.COCONUT_CRAB, 15686450, 5845811, new Item.Settings().group(Ecologics.TAB)));
    public static final Item CAMEL_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "camel_spawn_egg"), new SpawnEggItem(ModEntityTypes.CAMEL, 15714446, 5321501, new Item.Settings().group(Ecologics.TAB)));
    public static final Item PENGUIN_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "penguin_spawn_egg"), new SpawnEggItem(ModEntityTypes.PENGUIN, 1315860, 16382457, new Item.Settings().group(Ecologics.TAB)));
//    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.PENGUIN.get(), 1315860, 16382457, new Item.Properties().tab(Ecologics.TAB)));
    public static final Item SANDCASTLE = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "sandcastle"), new SandcastleBlockItem());
    public static final Item MUSIC_DISC_COCONUT = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "music_disc_coconut"), new MusicDiscItem(10, ModSoundEvents.MUSIC_DISC_COCONUT, new Item.Settings().maxCount(1).group(Ecologics.TAB).rarity(Rarity.RARE)));
    public static final Item COCONUT_SIGN = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "coconut_sign"), new SignItem(new Item.Settings().maxCount(16).group(Ecologics.TAB), ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN));
    public static final Item COCONUT_BOAT = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "coconut_boat"), new ModBoatItem("coconut", new Item.Settings().maxCount(1).group(Ecologics.TAB)));
    public static final Item PRICKLY_PEAR = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "prickly_pear"), new PricklyPearItem());
    public static final Item COOKED_PRICKLY_PEAR = Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, "cooked_prickly_pear"), new Item(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.APPLE)));
}
