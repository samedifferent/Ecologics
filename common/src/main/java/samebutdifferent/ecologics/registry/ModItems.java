package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.ModBoatItem;
import samebutdifferent.ecologics.item.PricklyPearItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModItems {
    public static void init() {}

    public static final Supplier<Item> COCONUT_SLICE = CommonPlatformHelper.registerItem("coconut_slice", () -> new CoconutSliceItem(new Item.Properties().food(Foods.SWEET_BERRIES)));
    public static final Supplier<Item> COCONUT_HUSK = CommonPlatformHelper.registerItem("coconut_husk", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CRAB_CLAW = CommonPlatformHelper.registerItem("crab_claw", () -> new ShearsItem(new Item.Properties().durability(50)));
    public static final Supplier<Item> CRAB_MEAT = CommonPlatformHelper.registerItem("crab_meat", () -> new Item(new Item.Properties().food(Foods.COOKED_RABBIT)));
    public static final Supplier<Item> TROPICAL_STEW = CommonPlatformHelper.registerItem("tropical_stew", () -> new CoconutSliceItem(new Item.Properties().stacksTo(1).food(Foods.RABBIT_STEW)));
    public static final Supplier<Item> COCONUT_CRAB_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("coconut_crab_spawn_egg", ModEntityTypes.COCONUT_CRAB, 15686450, 5845811);
    public static final Supplier<Item> PENGUIN_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("penguin_spawn_egg", ModEntityTypes.PENGUIN, 1315860, 16382457);
    public static final Supplier<Item> SQUIRREL_SPAWN_EGG = CommonPlatformHelper.registerSpawnEggItem("squirrel_spawn_egg", ModEntityTypes.SQUIRREL, 10051392, 15720061);
    public static final Supplier<Item> SANDCASTLE = CommonPlatformHelper.registerItem("sandcastle", SandcastleBlockItem::new);
    public static final Supplier<RecordItem> MUSIC_DISC_COCONUT = CommonPlatformHelper.registerRecordItem("music_disc_coconut", 10, ModSoundEvents.MUSIC_DISC_COCONUT, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    public static final Supplier<Item> PRICKLY_PEAR = CommonPlatformHelper.registerItem("prickly_pear", PricklyPearItem::new);
    public static final Supplier<Item> COOKED_PRICKLY_PEAR = CommonPlatformHelper.registerItem("cooked_prickly_pear", () -> new Item(new Item.Properties().food(Foods.APPLE)));
    public static final Supplier<Item> PENGUIN_FEATHER = CommonPlatformHelper.registerItem("penguin_feather", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WALNUT_SIGN = CommonPlatformHelper.registerItem("walnut_sign", () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()));
    public static final Supplier<Item> WALNUT_HANGING_SIGN = CommonPlatformHelper.registerItem("walnut_hanging_sign", () -> new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> WALNUT = CommonPlatformHelper.registerItem("walnut", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.0F).fast().build())));
    public static final Supplier<Item> COCONUT_BOAT = CommonPlatformHelper.registerItem("coconut_boat", () -> new ModBoatItem(false, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> WALNUT_BOAT = CommonPlatformHelper.registerItem("walnut_boat", () -> new ModBoatItem(false, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> COCONUT_CHEST_BOAT = CommonPlatformHelper.registerItem("coconut_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> WALNUT_CHEST_BOAT = CommonPlatformHelper.registerItem("walnut_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1)));
}
