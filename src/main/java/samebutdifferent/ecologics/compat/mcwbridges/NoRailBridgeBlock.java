package samebutdifferent.ecologics.compat.mcwbridges;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;

public class NoRailBridgeBlock extends NoBridgeBlock {
    public static final BooleanProperty NORTH_WEST = BooleanProperty.create("north_west");
    public static final BooleanProperty NORTH_EAST = BooleanProperty.create("north_east");
    public static final BooleanProperty SOUTH_EAST = BooleanProperty.create("south_east");
    public static final BooleanProperty SOUTH_WEST = BooleanProperty.create("south_west");

    public NoRailBridgeBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, TOGGLE, TORCH, WATERLOGGED, FACING, NORTH_WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST);
    }
}