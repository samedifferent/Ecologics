package samebutdifferent.ecologics.compat.decorative_blocks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import samebutdifferent.ecologics.compat.CompatBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;

public class NoSupportBlock extends CompatBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final EnumProperty<NoSupportFaceShape> HORIZONTAL_SHAPE = EnumProperty.create("horizontal", NoSupportFaceShape.class);
    public static final EnumProperty<NoSupportFaceShape> VERTICAL_SHAPE = EnumProperty.create("vertical", NoSupportFaceShape.class);

    public NoSupportBlock() {
        super(Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), DBCompat.DB_ID);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE).setValue(UP, Boolean.TRUE).setValue(HORIZONTAL_SHAPE, NoSupportFaceShape.BIG).setValue(VERTICAL_SHAPE, NoSupportFaceShape.SMALL));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED, UP, HORIZONTAL_SHAPE, VERTICAL_SHAPE);
    }
}
