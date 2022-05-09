package samebutdifferent.ecologics.compat.decorative_blocks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import samebutdifferent.ecologics.compat.CompatBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;

public class NoSeatBlock extends CompatBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    public static final BooleanProperty ATTACHED = BlockStateProperties.ATTACHED;
    public static final BooleanProperty POST = BooleanProperty.create("post");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public NoSeatBlock() {
        super(Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), DBCompat.DB_ID);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false).setValue(OCCUPIED, false).setValue(ATTACHED, false).setValue(POST, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED, OCCUPIED, ATTACHED, POST);
    }
}
