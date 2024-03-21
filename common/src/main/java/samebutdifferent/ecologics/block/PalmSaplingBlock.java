package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.grower.PalmTreeGrower;

public class PalmSaplingBlock extends SaplingBlock {
    public PalmSaplingBlock() {
        super(new PalmTreeGrower(), Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.SAND) || pState.is(Blocks.RED_SAND);
    }
}
