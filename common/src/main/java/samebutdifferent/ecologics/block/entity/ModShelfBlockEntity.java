package samebutdifferent.ecologics.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.registry.ModBlockEntityTypes;

public class ModShelfBlockEntity extends ShelfBlockEntity
{
	public ModShelfBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.SHELF;
    }
    
	@Override
    public boolean isValidBlockState(BlockState $$0) {
        return this.getType().isValid($$0);
    }
}
