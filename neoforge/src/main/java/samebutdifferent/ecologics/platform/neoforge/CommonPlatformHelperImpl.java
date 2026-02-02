package samebutdifferent.ecologics.platform.neoforge;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

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
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import samebutdifferent.ecologics.mixin.neoforge.AxeItemAccessor;

public class CommonPlatformHelperImpl 
{
    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
    	((FireBlock)Blocks.FIRE).setFlammable(block, encouragement, flammability);
    }

    public static void registerCompostable(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }

    public static void registerStrippables(Map<Block, Block> blockMap) {
        Map<Block, Block> strippables = new ImmutableMap.Builder<Block, Block>().putAll(AxeItemAccessor.getStrippables()).putAll(blockMap).build();
        AxeItemAccessor.setStrippables(strippables);
    }

}
