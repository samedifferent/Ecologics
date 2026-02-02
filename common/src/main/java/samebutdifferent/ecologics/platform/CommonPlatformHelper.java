package samebutdifferent.ecologics.platform;

import java.util.Map;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

//TODO: Determine if this should be removed and replaced.
public class CommonPlatformHelper 
{
    public static <T extends Block> void setFlammable(Block block, int encouragement, int flammability) {
        setFlammable(Blocks.FIRE, block, encouragement, flammability);
    }

    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
        // throw new AssertionError();
    }

    public static void registerBrewingRecipe(Holder<Potion> awkward, Item ingredient, Holder<Potion> output) {
        // throw new AssertionError();
    }
    
    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacementType decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        // throw new AssertionError();
    }

    public static void registerCompostable(float chance, ItemLike item) {
        // throw new AssertionError();
    }

    public static void registerStrippables(Map<Block, Block> blockMap) {
        // throw new AssertionError();
    }

}
