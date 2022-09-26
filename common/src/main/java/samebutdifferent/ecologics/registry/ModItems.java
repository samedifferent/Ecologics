package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.ModBoatItem;
import samebutdifferent.ecologics.item.PricklyPearItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModItems {
    public static void init() {}

    public static final Supplier<Item> COCONUT_SLICE = CommonPlatformHelper.registerItem("coconut_slice", () -> new CoconutSliceItem(new Item.Properties().tab(Ecologics.TAB).food(Foods.SWEET_BERRIES)));
    public static final Supplier<Item> COCONUT_HUSK = CommonPlatformHelper.registerItem("coconut_husk", () -> new Item(new Item.Properties().tab(Ecologics.TAB)));
    public static final Supplier<Item> CRAB_CLAW = CommonPlatformHelper.registerItem("crab_claw", () -> new ShearsItem(new Item.Properties().tab(Ecologics.TAB).durability(50)));
    public static final Supplier<Item> CRAB_MEAT = CommonPlatformHelper.registerItem("crab_meat", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.COOKED_RABBIT)));
    public static final Supplier<Item> TROPICAL_STEW = CommonPlatformHelper.registerItem("tropical_stew", () -> new CoconutSliceItem(new Item.Properties().stacksTo(1).tab(Ecologics.TAB).food(Foods.RABBIT_STEW)));
    public static final Supplier<Item> COCONUT_CRAB_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("coconut_crab_spawn_egg", ModEntityTypes.COCONUT_CRAB, 15686450, 5845811);
    public static final Supplier<Item> CAMEL_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("camel_spawn_egg", ModEntityTypes.CAMEL, 15714446, 5321501);
    public static final Supplier<Item> PENGUIN_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("penguin_spawn_egg", ModEntityTypes.PENGUIN, 1315860, 16382457);
    public static final Supplier<Item> SQUIRREL_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("squirrel_spawn_egg", ModEntityTypes.SQUIRREL, 10051392, 15720061);
    public static final Supplier<Item> SANDCASTLE = CommonPlatformHelper.registerItem("sandcastle", SandcastleBlockItem::new);
    public static final Supplier<RecordItem> MUSIC_DISC_COCONUT = CommonPlatformHelper.registerRecordItem("music_disc_coconut", 10, ModSoundEvents.MUSIC_DISC_COCONUT, new Item.Properties().stacksTo(1).tab(Ecologics.TAB).rarity(Rarity.RARE));
    public static final Supplier<Item> COCONUT_SIGN = CommonPlatformHelper.registerItem("coconut_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Ecologics.TAB), ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get()));
    public static final Supplier<Item> PRICKLY_PEAR = CommonPlatformHelper.registerItem("prickly_pear", PricklyPearItem::new);
    public static final Supplier<Item> COOKED_PRICKLY_PEAR = CommonPlatformHelper.registerItem("cooked_prickly_pear", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.APPLE)));
    public static final Supplier<Item> PENGUIN_FEATHER = CommonPlatformHelper.registerItem("penguin_feather", () -> new Item(new Item.Properties().tab(Ecologics.TAB)));
    public static final Supplier<Item> WALNUT_SIGN = CommonPlatformHelper.registerItem("walnut_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Ecologics.TAB), ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()));
    public static final Supplier<Item> WALNUT = CommonPlatformHelper.registerItem("walnut", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.0F).fast().build())));
    public static final Supplier<Item> AZALEA_SIGN = CommonPlatformHelper.registerItem("azalea_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Ecologics.TAB), ModBlocks.AZALEA_SIGN.get(), ModBlocks.AZALEA_WALL_SIGN.get()));
    public static final Supplier<Item> FLOWERING_AZALEA_SIGN = CommonPlatformHelper.registerItem("flowering_azalea_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Ecologics.TAB), ModBlocks.FLOWERING_AZALEA_SIGN.get(), ModBlocks.FLOWERING_AZALEA_WALL_SIGN.get()));
    public static final Supplier<Item> COCONUT_BOAT = CommonPlatformHelper.registerItem("coconut_boat", () -> new ModBoatItem(false, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> WALNUT_BOAT = CommonPlatformHelper.registerItem("walnut_boat", () -> new ModBoatItem(false, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> AZALEA_BOAT = CommonPlatformHelper.registerItem("azalea_boat", () -> new ModBoatItem(false, ModBoat.Type.AZALEA, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> FLOWERING_AZALEA_BOAT = CommonPlatformHelper.registerItem("flowering_azalea_boat", () -> new ModBoatItem(false, ModBoat.Type.FLOWERING_AZALEA, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> COCONUT_CHEST_BOAT = CommonPlatformHelper.registerItem("coconut_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> WALNUT_CHEST_BOAT = CommonPlatformHelper.registerItem("walnut_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> AZALEA_CHEST_BOAT = CommonPlatformHelper.registerItem("azalea_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.AZALEA, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
    public static final Supplier<Item> FLOWERING_AZALEA_CHEST_BOAT = CommonPlatformHelper.registerItem("flowering_azalea_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.FLOWERING_AZALEA, new Item.Properties().stacksTo(1).tab(Ecologics.TAB)));
}
