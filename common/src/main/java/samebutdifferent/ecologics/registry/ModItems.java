package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.PricklyPearItem;

public class ModItems
{
    public static void init() {
    	for (Triplet<Identifier, Block, Boolean> registry : ModBlocks.BLOCKS) {
    		if (!registry.getC()) {
    			continue;
    		}
    		ModItems.registerItem(registry.getA().getPath(), new BlockItem(registry.getB(), new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, registry.getA()))));
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

    // Food
    public static final Item COCONUT_SLICE = registerItem("coconut_slice", CoconutSliceItem::new, new Item.Properties().food(Foods.SWEET_BERRIES));
    public static final Item CRAB_MEAT = registerItem("crab_meat", Item::new, new Item.Properties().food(Foods.COOKED_RABBIT));
    public static final Item TROPICAL_STEW = registerItem("tropical_stew", CoconutSliceItem::new, new Item.Properties().stacksTo(1).food(Foods.RABBIT_STEW));
    public static final Item PRICKLY_PEAR = registerItem("prickly_pear", PricklyPearItem::new, new Item.Properties().food(Foods.APPLE));
    public static final Item COOKED_PRICKLY_PEAR = registerItem("cooked_prickly_pear", Item::new, new Item.Properties().food(Foods.APPLE));
    public static final Item WALNUT = registerItem("walnut", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.0F).build(), Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build()));
    
    // Miscellaneous
    public static final Item CRAB_CLAW = registerItem("crab_claw", ShearsItem::new, new Item.Properties().durability(50));
    public static final Item COCONUT_HUSK = registerItem("coconut_husk", Item::new, new Item.Properties());
    public static final Item PENGUIN_FEATHER = registerItem("penguin_feather", Item::new, new Item.Properties());
    // public static final Item SANDCASTLE = registerItem("sandcastle", SandcastleBlockItem::new, new Properties().stacksTo(16));
    public static final Item MUSIC_DISC_COCONUT = registerItem("music_disc_coconut", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.COCONUT));
    
    // Spawn Eggs
    public static final Item COCONUT_CRAB_SPAWN_EGG = registerItem("coconut_crab_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.COCONUT_CRAB));
    public static final Item PENGUIN_SPAWN_EGG = registerItem("penguin_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.PENGUIN));
    public static final Item SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntityTypes.SQUIRREL));
    
    // Signs
    public static final Item COCONUT_SIGN = registerItem("coconut_sign", properties -> new SignItem(ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item COCONUT_HANGING_SIGN = registerItem("coconut_hanging_sign", properties -> new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item WALNUT_SIGN = registerItem("walnut_sign", properties -> new SignItem(ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item WALNUT_HANGING_SIGN = registerItem("walnut_hanging_sign", properties -> new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item AZALEA_SIGN = registerItem("azalea_sign", properties -> new SignItem(ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item AZALEA_HANGING_SIGN = registerItem("azalea_hanging_sign", properties -> new HangingSignItem(ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item FLOWERING_AZALEA_SIGN = registerItem("flowering_azalea_sign", properties -> new SignItem(ModBlocks.FLOWERING_AZALEA_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
    public static final Item FLOWERING_AZALEA_HANGING_SIGN = registerItem("flowering_azalea_hanging_sign", properties -> new HangingSignItem(ModBlocks.FLOWERING_AZALEA_HANGING_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
    
    // Boats
    public static final Item COCONUT_BOAT = registerItem("coconut_boat", properties -> new BoatItem(ModEntityTypes.COCONUT_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item WALNUT_BOAT = registerItem("walnut_boat", properties -> new BoatItem(ModEntityTypes.WALNUT_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item AZALEA_BOAT = registerItem("azalea_boat", properties -> new BoatItem(ModEntityTypes.AZALEA_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item FLOWERING_AZALEA_BOAT = registerItem("flowering_azalea_boat", properties -> new BoatItem(ModEntityTypes.FLOWERING_AZALEA_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item COCONUT_CHEST_BOAT = registerItem("coconut_chest_boat", properties -> new BoatItem(ModEntityTypes.COCONUT_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item WALNUT_CHEST_BOAT = registerItem("walnut_chest_boat", properties -> new BoatItem(ModEntityTypes.WALNUT_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item AZALEA_CHEST_BOAT = registerItem("azalea_chest_boat", properties -> new BoatItem(ModEntityTypes.AZALEA_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item FLOWERING_AZALEA_CHEST_BOAT = registerItem("flowering_azalea_chest_boat", properties -> new BoatItem(ModEntityTypes.FLOWERING_AZALEA_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    

}
