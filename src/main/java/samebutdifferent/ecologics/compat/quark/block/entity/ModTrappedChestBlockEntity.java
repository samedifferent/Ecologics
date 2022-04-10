package samebutdifferent.ecologics.compat.quark.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;

public class ModTrappedChestBlockEntity extends ModChestBlockEntity {
    public ModTrappedChestBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(QuarkCompat.TRAPPED_CHEST.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void signalOpenCount(Level pLevel, BlockPos pPos, BlockState pState, int pEventID, int pEventParam) {
        super.signalOpenCount(pLevel, pPos, pState, pEventID, pEventParam);
        if (pEventID != pEventParam) {
            Block block = pState.getBlock();
            pLevel.updateNeighborsAt(pPos, block);
            pLevel.updateNeighborsAt(pPos.below(), block);
        }
    }
}
