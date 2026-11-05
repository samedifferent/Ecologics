package samebutdifferent.ecologics.fabric.integration.farmersdelight;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.registry.ModItems;
import vectorwing.farmersdelight.common.block.PieBlock;

public class MaplePieBlock extends PieBlock
{
    public MaplePieBlock(Properties properties, Supplier<Item> pieSlice) {
        super(properties, pieSlice);
    }
    
    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean bool) {
        return ModItems.MAPLE_PIE.getDefaultInstance();
    }
}
