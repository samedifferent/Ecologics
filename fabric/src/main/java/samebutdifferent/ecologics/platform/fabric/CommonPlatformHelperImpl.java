package samebutdifferent.ecologics.platform.fabric;

import java.util.Map;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import samebutdifferent.ecologics.mixin.fabric.SpawnPlacementsAccessor;

public class CommonPlatformHelperImpl {

    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
        FlammableBlockRegistry.getInstance(fireBlock).add(block, encouragement, flammability);
    }
    
    public static void registerBrewingRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
    	FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(input, Ingredient.of(ingredient), output));
    }
    
    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacementType decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacementsAccessor.invokeRegister(entityType, decoratorType, heightMapType, decoratorPredicate);
    }

    public static void registerCompostable(float chance, ItemLike item) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }

    public static void registerStrippables(Map<Block, Block> blockMap) {
        blockMap.forEach(StrippableBlockRegistry::register);
    }

}
