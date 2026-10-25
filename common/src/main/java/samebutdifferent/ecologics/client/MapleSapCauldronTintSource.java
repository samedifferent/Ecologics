package samebutdifferent.ecologics.client;

import org.jspecify.annotations.Nullable;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;

public class MapleSapCauldronTintSource implements BlockColor
{
	@Override
	public int getColor(BlockState state, @Nullable BlockAndTintGetter getter, @Nullable BlockPos pos, int tintIndex) {
        int sapLevel = state.getValue(MapleSapCauldronBlock.LEVEL);
        switch (sapLevel) {
            case 3:
                return 0xC0FFD6AD; 
            case 2:
                return 0xD0FF9B4D;
            case 1:
            default:
                return 0xE0FF7835;
        }
    }
}
