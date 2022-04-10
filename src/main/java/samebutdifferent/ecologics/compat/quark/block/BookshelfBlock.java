package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BookshelfBlock extends Block {
    public BookshelfBlock() {
        super(Properties.copy(Blocks.BOOKSHELF));
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return 1;
    }
}
