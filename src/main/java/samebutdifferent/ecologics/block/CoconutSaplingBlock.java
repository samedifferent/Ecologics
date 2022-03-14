package samebutdifferent.ecologics.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import samebutdifferent.ecologics.block.grower.CoconutTreeGrower;

public class CoconutSaplingBlock extends SaplingBlock {
    public CoconutSaplingBlock() {
        super(new CoconutTreeGrower(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.WOOD));
    }

    @Override
    protected boolean canPlantOnTop(BlockState pState, BlockView pLevel, BlockPos pPos) {
        return pState.isOf(Blocks.SAND) || pState.isOf(Blocks.RED_SAND);
    }
}
