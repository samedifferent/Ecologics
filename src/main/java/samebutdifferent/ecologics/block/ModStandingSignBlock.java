package samebutdifferent.ecologics.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.SignType;
import net.minecraft.util.math.BlockPos;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;

public class ModStandingSignBlock extends SignBlock {
    public ModStandingSignBlock(Settings properties, SignType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
}
