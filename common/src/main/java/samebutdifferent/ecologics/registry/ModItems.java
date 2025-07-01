package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.item.ModBoatItem;
import samebutdifferent.ecologics.item.PricklyPearItem;
import samebutdifferent.ecologics.item.SandcastleBlockItem;

public class ModItems
{
    public static void init() {
    	for (Triplet<ResourceLocation, Block, Boolean> registry : ModBlocks.BLOCKS) {
    		if (!registry.getC()) {
    			continue;
    		}
    		ModItems.registerItem(registry.getA().getPath(), new BlockItem(registry.getB(), new Item.Properties()));
    	}
       	for (Pair<ResourceLocation, Item> registry : ITEMS) {
    		Registry.register(BuiltInRegistries.ITEM, registry.getA(), registry.getB());
    	}
    }
    
    public static Item registerItem(String name, Item item) {
    	ITEMS.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), item));
    	return item;
    }
    
    public static final ArrayList<Pair<ResourceLocation, Item>> ITEMS = new ArrayList();

    // Food
    public static final Item COCONUT_SLICE = registerItem("coconut_slice", new CoconutSliceItem(new Item.Properties().food(Foods.SWEET_BERRIES)));
    public static final Item CRAB_MEAT = registerItem("crab_meat", new Item(new Item.Properties().food(Foods.COOKED_RABBIT)));
    public static final Item TROPICAL_STEW = registerItem("tropical_stew", new CoconutSliceItem(new Item.Properties().stacksTo(1).food(Foods.RABBIT_STEW)));
    public static final Item PRICKLY_PEAR = registerItem("prickly_pear", new Item(new Item.Properties().food(Foods.APPLE))); //PricklyPearItem
    public static final Item COOKED_PRICKLY_PEAR = registerItem("cooked_prickly_pear", new Item(new Item.Properties().food(Foods.APPLE)));
    public static final Item WALNUT = registerItem("walnut", new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.0F).fast().build())));
    
    // Miscellaneous
    public static final Item CRAB_CLAW = registerItem("crab_claw", new ShearsItem(new Item.Properties().durability(50)));
    public static final Item COCONUT_HUSK = registerItem("coconut_husk", new Item(new Item.Properties()));
    public static final Item PENGUIN_FEATHER = registerItem("penguin_feather", new Item(new Item.Properties()));
    public static final Item SANDCASTLE = registerItem("sandcastle", new SandcastleBlockItem());
    public static final Item MUSIC_DISC_COCONUT = registerItem("music_disc_coconut", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.COCONUT)));
    
    // Spawn Eggs
    //public static final Item CAMEL_SPAWN_EGG = registerItem("camel_spawn_egg", ModEntityTypes.CAMEL, 15714446, 5321501);
    public static final Item COCONUT_CRAB_SPAWN_EGG = registerItem("coconut_crab_spawn_egg", new SpawnEggItem(ModEntityTypes.COCONUT_CRAB, 15686450, 5845811, new Item.Properties()));
    public static final Item PENGUIN_SPAWN_EGG = registerItem("penguin_spawn_egg", new SpawnEggItem(ModEntityTypes.PENGUIN, 1315860, 16382457, new Item.Properties()));
    public static final Item SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg", new SpawnEggItem(ModEntityTypes.SQUIRREL, 10051392, 15720061, new Item.Properties()));
    
    // Signs
    public static final Item COCONUT_SIGN = registerItem("coconut_sign", new SignItem(new Item.Properties().stacksTo(16), ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN));
    public static final Item COCONUT_HANGING_SIGN = registerItem("coconut_hanging_sign", new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));
    public static final Item WALNUT_SIGN = registerItem("walnut_sign", new SignItem(new Item.Properties().stacksTo(16), ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN));
    public static final Item WALNUT_HANGING_SIGN = registerItem("walnut_hanging_sign", new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));
    public static final Item AZALEA_SIGN = registerItem("azalea_sign", new SignItem(new Item.Properties().stacksTo(16), ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN));
    public static final Item AZALEA_HANGING_SIGN = registerItem("azalea_hanging_sign", new HangingSignItem(ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));
    public static final Item FLOWERING_AZALEA_SIGN = registerItem("flowering_azalea_sign", new SignItem(new Item.Properties().stacksTo(16), ModBlocks.FLOWERING_AZALEA_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_SIGN));
    public static final Item FLOWERING_AZALEA_HANGING_SIGN = registerItem("flowering_azalea_hanging_sign", new HangingSignItem(ModBlocks.FLOWERING_AZALEA_HANGING_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));
    
    // Boats
    public static final Item COCONUT_BOAT = registerItem("coconut_boat", new ModBoatItem(false, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1)));
    public static final Item WALNUT_BOAT = registerItem("walnut_boat", new ModBoatItem(false, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1)));
    public static final Item AZALEA_BOAT = registerItem("azalea_boat", new ModBoatItem(false, ModBoat.Type.AZALEA, new Item.Properties().stacksTo(1)));
    public static final Item FLOWERING_AZALEA_BOAT = registerItem("flowering_azalea_boat", new ModBoatItem(false, ModBoat.Type.FLOWERING_AZALEA, new Item.Properties().stacksTo(1)));
    public static final Item COCONUT_CHEST_BOAT = registerItem("coconut_chest_boat", new ModBoatItem(true, ModBoat.Type.COCONUT, new Item.Properties().stacksTo(1)));
    public static final Item WALNUT_CHEST_BOAT = registerItem("walnut_chest_boat", new ModBoatItem(true, ModBoat.Type.WALNUT, new Item.Properties().stacksTo(1)));
    public static final Item AZALEA_CHEST_BOAT = registerItem("azalea_chest_boat", new ModBoatItem(true, ModBoat.Type.AZALEA, new Item.Properties().stacksTo(1)));
    public static final Item FLOWERING_AZALEA_CHEST_BOAT = registerItem("flowering_azalea_chest_boat", new ModBoatItem(true, ModBoat.Type.FLOWERING_AZALEA, new Item.Properties().stacksTo(1)));
    

}
