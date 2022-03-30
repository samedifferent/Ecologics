package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.block.grower.ModAzaleaTreeGrower;

import java.util.Random;

public class AzaleaFlowerBlock extends BushBlock implements BonemealableBlock {
    private static final ModAzaleaTreeGrower TREE_GROWER = new ModAzaleaTreeGrower();
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public AzaleaFlowerBlock() {
        super(Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.GRASS));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(getter, pos);
        return SHAPE.move(offset.x, offset.y, offset.z);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean b) {
        return getter.getFluidState(pos.above()).isEmpty();
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45D;
    }

    @Override
    public void performBonemeal(ServerLevel serverWorld, Random random, BlockPos pos, BlockState state) {
        TREE_GROWER.growTree(serverWorld, serverWorld.getChunkSource().getGenerator(), pos, state, random);
    }
}
