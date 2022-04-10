package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LeafCarpetBlock extends Block {
    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 1, 16);
    
    public LeafCarpetBlock() {
        super(Properties.of(Material.CLOTH_DECORATION).strength(0F).sound(SoundType.GRASS).noOcclusion());
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return useContext.getItemInHand().isEmpty() || useContext.getItemInHand().getItem() != this.asItem();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return !world.isEmptyBlock(pos.below());
    }
}
