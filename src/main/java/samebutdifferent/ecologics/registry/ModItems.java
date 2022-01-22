package samebutdifferent.ecologics.registry;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);

    public static final RegistryObject<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", () -> new CoconutSliceItem(new Item.Properties().tab(Ecologics.TAB).food(Foods.SWEET_BERRIES)));
    public static final RegistryObject<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new Item(new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> CRAB_MEAT = ITEMS.register("crab_meat", () -> new Item(new Item.Properties().tab(Ecologics.TAB).food(Foods.COOKED_RABBIT)));
    public static final RegistryObject<Item> TROPICAL_STEW = ITEMS.register("tropical_stew", () -> new CoconutSliceItem(new Item.Properties().stacksTo(1).tab(Ecologics.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> COCONUT_CRAB_SPAWN_EGG = ITEMS.register("coconut_crab_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.COCONUT_CRAB.get(), 15686450, 5845811, new Item.Properties().tab(Ecologics.TAB)));
    public static final RegistryObject<Item> SANDCASTLE = ITEMS.register("sandcastle", SandcastleBlockItem::new);
    public static final RegistryObject<Item> MUSIC_DISC_COCONUT = ITEMS.register("music_disc_coconut", () -> new RecordItem(10, () -> ModSoundEvents.MUSIC_DISC_COCONUT.get(), new Item.Properties().stacksTo(1).tab(Ecologics.TAB).rarity(Rarity.RARE)));
}
