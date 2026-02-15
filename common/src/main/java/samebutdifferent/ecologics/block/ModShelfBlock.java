package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.entity.ModShelfBlockEntity;

public class ModShelfBlock extends ShelfBlock
{
	public ModShelfBlock(Properties properties) {
		super(properties);
	}

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModShelfBlockEntity(pos, state);
    }
}
