package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import samebutdifferent.ecologics.block.grower.CoconutTreeGrower;

public class CoconutSaplingBlock extends SaplingBlock {
    public CoconutSaplingBlock() {
        super(new CoconutTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WOOD));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.SAND) || pState.is(Blocks.RED_SAND);
    }
}
