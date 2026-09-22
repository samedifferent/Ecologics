package samebutdifferent.ecologics.fabric.integration.farmersdelight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class ModCabinetBlock extends CabinetBlock 
{
    public ModCabinetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModCabinetBlockEntity(pos, state);
    }
}
