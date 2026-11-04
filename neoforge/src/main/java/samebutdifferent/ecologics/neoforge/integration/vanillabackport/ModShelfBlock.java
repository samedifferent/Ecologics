package samebutdifferent.ecologics.neoforge.integration.vanillabackport;

import com.blackgear.vanillabackport.common.level.blocks.ShelfBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

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
