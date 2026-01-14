package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import samebutdifferent.ecologics.block.grower.CoconutTreeGrower;
import samebutdifferent.ecologics.registry.ModTags;

public class CoconutSaplingBlock extends SaplingBlock {
    public CoconutSaplingBlock() {
        super(new CoconutTreeGrower(), Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModTags.BlockTags.COCONUT_PLANTABLE_ON);
    }
}
