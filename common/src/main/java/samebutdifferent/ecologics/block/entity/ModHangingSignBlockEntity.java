package samebutdifferent.ecologics.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;

public class ModHangingSignBlockEntity extends HangingSignBlockEntity
{
	public ModHangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(blockPos, blockState);
	}

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.HANGING_SIGN.get();
    }
}
