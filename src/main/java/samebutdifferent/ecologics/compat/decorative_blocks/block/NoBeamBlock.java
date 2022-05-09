package samebutdifferent.ecologics.compat.decorative_blocks.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import samebutdifferent.ecologics.compat.CompatBlock;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;

public class NoBeamBlock extends CompatBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public NoBeamBlock() {
        super(Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), DBCompat.DB_ID);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }
}
