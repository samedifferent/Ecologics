package samebutdifferent.ecologics.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;

public class MapleSapCauldronTintSource implements BlockTintSource
{
	@Override
	public int color(BlockState state) {
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
