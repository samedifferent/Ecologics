package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Function;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutBlockItem;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.MaplePieItem;
import samebutdifferent.ecologics.item.PricklyPearItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;

public class ModItems
{	
    public static void init() {
    	for (Triplet<Identifier, Block, Item.Properties> registry : ModBlocks.BLOCKS) {
    		if (registry.getC() == null) {
    			continue;
    		}
    		ModItems.registerItem(registry.getA().getPath(), new BlockItem(registry.getB(), registry.getC().setId(ResourceKey.create(Registries.ITEM, registry.getA()))));
    	}
       	for (Pair<Identifier, Item> registry : ITEMS) {
    		Registry.register(BuiltInRegistries.ITEM, registry.getA(), registry.getB());
    	}
    }
    
    public static Item registerItem(String name, Item item) {
    	ITEMS.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), item));
    	return item;
    }
    
    public static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
    	ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name));
    	Item item = itemFactory.apply(properties.setId(itemKey));
    	ITEMS.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), item));
    	return item;
    }
    
    public static final ArrayList<Pair<Identifier, Item>> ITEMS = new ArrayList<>();
    
    // Miscellaneous
    public static final Item CRAB_CLAW = registerItem("crab_claw", ShearsItem::new, new Item.Properties().durability(50).component(DataComponents.TOOL, ShearsItem.createToolProperties()));
    public static final Item COCONUT_HUSK = registerItem("coconut_husk", Item::new, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
    public static final Item PENGUIN_FEATHER = registerItem("penguin_feather", Item::new, new Item.Properties());
    public static final Item SANDCASTLE = registerItem("sandcastle", SandcastleBlockItem::new, new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
    public static final Item COCONUT = registerItem("coconut", properties -> new CoconutBlockItem(ModBlocks.COCONUT, properties), new Item.Properties().useBlockDescriptionPrefix());
    public static final Item MUSIC_DISC_COCONUT = registerItem("music_disc_coconut", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.COCONUT));
    public static final Item MAPLE_SAP_BUCKET = registerItem("maple_sap_bucket", Item::new, new Item.Properties().stacksTo(1));

    // Food
    public static final Item COCONUT_SLICE = registerItem("coconut_slice", CoconutSliceItem::new, new Item.Properties().food(ModFoods.COCONUT_SLICE).compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final Item CRAB_MEAT = registerItem("crab_meat", Item::new, new Item.Properties().food(ModFoods.CRAB_MEAT));
    public static final Item TROPICAL_STEW = registerItem("tropical_stew", CoconutSliceItem::new, new Item.Properties().stacksTo(1).food(ModFoods.TROPICAL_STEW));
    public static final Item PRICKLY_PEAR = registerItem("prickly_pear", PricklyPearItem::new, new Item.Properties().food(ModFoods.PRICKLY_PEAR).compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
    public static final Item COOKED_PRICKLY_PEAR = registerItem("cooked_prickly_pear", Item::new, new Item.Properties().food(ModFoods.COOKED_PRICKLY_PEAR).compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
    public static final Item WALNUT = registerItem("walnut", Item::new, new Item.Properties().food(ModFoods.WALNUT, Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build()));
    public static final Item MAPLE_SYRUP_BOTTLE = registerItem("maple_syrup_bottle", Item::new, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(ModFoods.MAPLE_SYRUP_BOTTLE, ModConsumables.MAPLE_SYRUP_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE));
    public static final Item MAPLE_COOKIE = registerItem("maple_cookie", Item::new, new Item.Properties().food(ModFoods.MAPLE_COOKIE));
    public static final Item MAPLE_PIE = registerItem("maple_pie", properties -> new MaplePieItem(ModBlocks.MAPLE_PIE, properties), new Item.Properties().food(ModFoods.MAPLE_PIE, ModConsumables.MAPLE_PIE));
    
    // Spawn Eggs
    public static final Item COCONUT_CRAB_SPAWN_EGG = registerItem("coconut_crab_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.COCONUT_CRAB));
    public static final Item PENGUIN_SPAWN_EGG = registerItem("penguin_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.PENGUIN));
    public static final Item SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.SQUIRREL));
    
    // Signs
    public static final Item COCONUT_SIGN = registerItem("coconut_sign", properties -> new StandingAndWallBlockItem(ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
    public static final Item COCONUT_HANGING_SIGN = registerItem("coconut_hanging_sign", properties -> new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).useBlockDescriptionPrefix());
    public static final Item WALNUT_SIGN = registerItem("walnut_sign", properties -> new StandingAndWallBlockItem(ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
    public static final Item WALNUT_HANGING_SIGN = registerItem("walnut_hanging_sign", properties -> new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).useBlockDescriptionPrefix());
    public static final Item AZALEA_SIGN = registerItem("azalea_sign", properties -> new StandingAndWallBlockItem(ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
    public static final Item AZALEA_HANGING_SIGN = registerItem("azalea_hanging_sign", properties -> new HangingSignItem(ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).useBlockDescriptionPrefix());
    public static final Item FLOWERING_AZALEA_SIGN = registerItem("flowering_azalea_sign", properties -> new StandingAndWallBlockItem(ModBlocks.FLOWERING_AZALEA_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
    public static final Item FLOWERING_AZALEA_HANGING_SIGN = registerItem("flowering_azalea_hanging_sign", properties -> new HangingSignItem(ModBlocks.FLOWERING_AZALEA_HANGING_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).useBlockDescriptionPrefix());
    public static final Item MAPLE_SIGN = registerItem("maple_sign", properties -> new StandingAndWallBlockItem(ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
    public static final Item MAPLE_HANGING_SIGN = registerItem("maple_hanging_sign", properties -> new HangingSignItem(ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16).cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).useBlockDescriptionPrefix());
    
    // Boats
    public static final Item COCONUT_BOAT = registerItem("coconut_boat", properties -> new BoatItem(ModEntityTypes.COCONUT_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item WALNUT_BOAT = registerItem("walnut_boat", properties -> new BoatItem(ModEntityTypes.WALNUT_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item AZALEA_BOAT = registerItem("azalea_boat", properties -> new BoatItem(ModEntityTypes.AZALEA_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item FLOWERING_AZALEA_BOAT = registerItem("flowering_azalea_boat", properties -> new BoatItem(ModEntityTypes.FLOWERING_AZALEA_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item MAPLE_BOAT = registerItem("maple_boat", properties -> new BoatItem(ModEntityTypes.MAPLE_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item COCONUT_CHEST_BOAT = registerItem("coconut_chest_boat", properties -> new BoatItem(ModEntityTypes.COCONUT_CHEST_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item WALNUT_CHEST_BOAT = registerItem("walnut_chest_boat", properties -> new BoatItem(ModEntityTypes.WALNUT_CHEST_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item AZALEA_CHEST_BOAT = registerItem("azalea_chest_boat", properties -> new BoatItem(ModEntityTypes.AZALEA_CHEST_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item FLOWERING_AZALEA_CHEST_BOAT = registerItem("flowering_azalea_chest_boat", properties -> new BoatItem(ModEntityTypes.FLOWERING_AZALEA_CHEST_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));
    public static final Item MAPLE_CHEST_BOAT = registerItem("maple_chest_boat", properties -> new BoatItem(ModEntityTypes.MAPLE_CHEST_BOAT, properties), new Item.Properties().stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS));

}
