package samebutdifferent.ecologics.fabric.integration.vanillabackport;

import com.blackgear.vanillabackport.common.level.block_entities.ShelfBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.Ecologics;

public class ModShelfBlockEntity extends ShelfBlockEntity
{
	public ModShelfBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

    @Override
    public BlockEntityType<?> getType() {
        return ((VanillaBackportCompat)Ecologics.vanillaBackport).SHELF_BLOCK_ENTITY;
    }
    
	@Override
    public boolean isValidBlockState(BlockState $$0) {
        return this.getType().isValid($$0);
    }
}
