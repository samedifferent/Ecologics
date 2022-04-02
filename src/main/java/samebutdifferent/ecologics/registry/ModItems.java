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

import java.util.function.Supplier;

public class ModItems {
    public static final Item COCONUT_SLICE = registerItem("coconut_slice", () -> new CoconutSliceItem(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.SWEET_BERRIES)));
    public static final Item CRAB_CLAW = registerItem("crab_claw", () -> new ShearsItem(new Item.Settings().group(Ecologics.TAB).maxDamage(50)));
    public static final Item CRAB_MEAT = registerItem("crab_meat", () -> new Item(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.COOKED_RABBIT)));
    public static final Item TROPICAL_STEW = registerItem("tropical_stew", () -> new CoconutSliceItem(new Item.Settings().maxCount(1).group(Ecologics.TAB).food(FoodComponents.RABBIT_STEW)));
    public static final Item COCONUT_CRAB_SPAWN_EGG = registerItem("coconut_crab_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.COCONUT_CRAB, 15686450, 5845811, new Item.Settings().group(Ecologics.TAB)));
    public static final Item CAMEL_SPAWN_EGG = registerItem("camel_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.CAMEL, 15714446, 5321501, new Item.Settings().group(Ecologics.TAB)));
    public static final Item PENGUIN_SPAWN_EGG = registerItem("penguin_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.PENGUIN, 1315860, 16382457, new Item.Settings().group(Ecologics.TAB)));
    public static final Item SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.SQUIRREL, 10051392, 15720061, new Item.Settings().group(Ecologics.TAB)));
    public static final Item SANDCASTLE = registerItem("sandcastle", SandcastleBlockItem::new);
    public static final Item MUSIC_DISC_COCONUT = registerItem("music_disc_coconut", () -> new MusicDiscItem(10, ModSoundEvents.MUSIC_DISC_COCONUT, new Item.Settings().maxCount(1).group(Ecologics.TAB).rarity(Rarity.RARE)));
    public static final Item COCONUT_SIGN = registerItem("coconut_sign", () -> new SignItem(new Item.Settings().maxCount(16).group(Ecologics.TAB), ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN));
    public static final Item COCONUT_BOAT = registerItem("coconut_boat", () -> new ModBoatItem("coconut", new Item.Settings().maxCount(1).group(Ecologics.TAB)));
    public static final Item PRICKLY_PEAR = registerItem("prickly_pear", PricklyPearItem::new);
    public static final Item COOKED_PRICKLY_PEAR = registerItem("cooked_prickly_pear", () -> new Item(new Item.Settings().group(Ecologics.TAB).food(FoodComponents.APPLE)));
    public static final Item PENGUIN_FEATHER = registerItem("penguin_feather", () -> new Item(new Item.Settings().group(Ecologics.TAB)));
    public static final Item WALNUT_SIGN = registerItem("walnut_sign", () -> new SignItem(new Item.Settings().maxCount(16).group(Ecologics.TAB), ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN));
    public static final Item WALNUT_BOAT = registerItem("walnut_boat", () -> new ModBoatItem("walnut", new Item.Settings().maxCount(1).group(Ecologics.TAB)));
    public static final Item WALNUT = registerItem("walnut", () -> new Item(new Item.Settings().group(Ecologics.TAB).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.6F).snack().build())));
    
    public static <T extends Item> T registerItem(String name, Supplier<T> item) {
        return Registry.register(Registry.ITEM, new Identifier(Ecologics.MOD_ID, name), item.get());
    }
}
