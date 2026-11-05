package samebutdifferent.ecologics.fabric.integration.farmersdelight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.Ecologics;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;

public class ModCabinetBlockEntity extends CabinetBlockEntity
{
    public ModCabinetBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    
    @Override
    public BlockEntityType<?> getType() {
        return ((FarmersDelightCompat)Ecologics.farmersDelight).CABINET_BLOCK_ENTITY;
    }
    
    @Override
    public boolean isValidBlockState(BlockState $$0) {
        return this.getType().isValid($$0);
    }
}
