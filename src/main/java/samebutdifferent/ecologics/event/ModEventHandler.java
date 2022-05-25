package samebutdifferent.ecologics.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.Camel;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.entity.Penguin;
import samebutdifferent.ecologics.entity.Squirrel;
import samebutdifferent.ecologics.loot.AddItemModifier;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModPotions;
import samebutdifferent.ecologics.util.ModBrewingRecipe;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {
    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(ModWoodType.COCONUT);
            WoodType.register(ModWoodType.WALNUT);
            WoodType.register(ModWoodType.AZALEA);
            WoodType.register(ModWoodType.FLOWERING_AZALEA);
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, ModItems.PENGUIN_FEATHER.get(), ModPotions.SLIDING.get()));
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(ModPotions.SLIDING.get(), Items.REDSTONE, ModPotions.LONG_SLIDING.get()));
            registerCompostables();
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_LOG.get())
                    .put(ModBlocks.COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get())
                    .put(ModBlocks.WALNUT_LOG.get(), ModBlocks.STRIPPED_WALNUT_LOG.get())
                    .put(ModBlocks.WALNUT_WOOD.get(), ModBlocks.STRIPPED_WALNUT_WOOD.get())
                    .put(ModBlocks.AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_LOG.get())
                    .put(ModBlocks.FLOWERING_AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_LOG.get())
                    .put(ModBlocks.FLOWERING_AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get())
                    .put(ModBlocks.AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get()).build();
            registerFlammables();
            SpawnPlacements.register(ModEntityTypes.CAMEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Camel::checkCamelSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SQUIRREL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        });
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

    private static void registerCompostables() {
        compostibleBlocks(0.3F, ModItems.COCONUT_SLICE.get());
        compostibleBlocks(0.3F, ModBlocks.COCONUT_HUSK.get().asItem());
        compostibleBlocks(0.3F, ModBlocks.COCONUT_LEAVES.get().asItem());
        compostibleBlocks(0.3F, ModBlocks.WALNUT_LEAVES.get().asItem());
        compostibleBlocks(0.3F, ModBlocks.WALNUT_SAPLING.get().asItem());
        compostibleBlocks(0.65F, ModBlocks.AZALEA_FLOWER.get().asItem());
    }

    private static void compostibleBlocks(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }

    public static void registerFlammables() {
        // COCONUT
        flammableBlock(ModBlocks.COCONUT_PLANKS.get(), 5, 20);
        flammableBlock(ModBlocks.COCONUT_SLAB.get(), 5, 20);
        flammableBlock(ModBlocks.COCONUT_FENCE_GATE.get(), 5, 20);
        flammableBlock(ModBlocks.COCONUT_FENCE.get(), 5, 20);
        flammableBlock(ModBlocks.COCONUT_STAIRS.get(), 5, 20);
        flammableBlock(ModBlocks.COCONUT_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_COCONUT_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_COCONUT_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.COCONUT_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.COCONUT_LEAVES.get(), 30, 60);
        // WALNUT
        flammableBlock(ModBlocks.WALNUT_PLANKS.get(), 5, 20);
        flammableBlock(ModBlocks.WALNUT_SLAB.get(), 5, 20);
        flammableBlock(ModBlocks.WALNUT_FENCE_GATE.get(), 5, 20);
        flammableBlock(ModBlocks.WALNUT_FENCE.get(), 5, 20);
        flammableBlock(ModBlocks.WALNUT_STAIRS.get(), 5, 20);
        flammableBlock(ModBlocks.WALNUT_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_WALNUT_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_WALNUT_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.WALNUT_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.WALNUT_LEAVES.get(), 30, 60);
        // AZALEA
        flammableBlock(ModBlocks.AZALEA_PLANKS.get(), 5, 20);
        flammableBlock(ModBlocks.AZALEA_SLAB.get(), 5, 20);
        flammableBlock(ModBlocks.AZALEA_FENCE_GATE.get(), 5, 20);
        flammableBlock(ModBlocks.AZALEA_FENCE.get(), 5, 20);
        flammableBlock(ModBlocks.AZALEA_STAIRS.get(), 5, 20);
        flammableBlock(ModBlocks.AZALEA_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_AZALEA_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_AZALEA_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.AZALEA_WOOD.get(), 5, 5);
        // FLOWERING_AZALEA
        flammableBlock(ModBlocks.FLOWERING_AZALEA_PLANKS.get(), 5, 20);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_SLAB.get(), 5, 20);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_FENCE_GATE.get(), 5, 20);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_FENCE.get(), 5, 20);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_STAIRS.get(), 5, 20);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.FLOWERING_AZALEA_WOOD.get(), 5, 5);
    }

    public static void flammableBlock(Block block, int flameOdds, int burnOdds) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(block, flameOdds, burnOdds);
    }
}
