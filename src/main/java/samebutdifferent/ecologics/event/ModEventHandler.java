package samebutdifferent.ecologics.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.Camel;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.loot.AddItemModifier;
import samebutdifferent.ecologics.registry.*;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {
    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(ModWoodType.COCONUT);
            ModTrunkPlacerTypes.register();
            ModConfiguredFeatures.register();
            ModPlacedFeatures.register();
            BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(ModItems.PENGUIN_FEATHER.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SLIDING.get()));
            BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SLIDING.get())), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_SLIDING.get()));
        });
        AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                .put(ModBlocks.COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_LOG.get())
                .put(ModBlocks.COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get()).build();
        ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT_SLICE.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_HUSK.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_LEAVES.get().asItem(), 0.3F);
    }


    @SubscribeEvent
    public static void onComplete(FMLLoadCompleteEvent event) {
        SpawnPlacements.register(ModEntityTypes.CAMEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Camel::checkCamelSpawnRules);
    }

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.COCONUT_CRAB.get(), CoconutCrab.createAttributes().build());
        event.put(ModEntityTypes.CAMEL.get(), Camel.createAttributes().build());
        event.put(ModEntityTypes.PENGUIN.get(), Penguin.createAttributes().build());
        event.put(ModEntityTypes.SQUIRREL.get(), Squirrel.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new AddItemModifier.Serializer().setRegistryName(Ecologics.MOD_ID, "add_item"));
    }
}
