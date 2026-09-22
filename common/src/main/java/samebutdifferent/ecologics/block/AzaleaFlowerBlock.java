package samebutdifferent.ecologics.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import samebutdifferent.ecologics.block.grower.ModTreeGrower;

public class AzaleaFlowerBlock extends FlowerBlock implements BonemealableBlock 
{
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    protected static final SuspiciousStewEffects AZALEA_FLOWER_SUSPICIOUS_STEW_EFFECTS = new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(MobEffects.NAUSEA, Mth.floor(15 * 20.0F))));

    public AzaleaFlowerBlock(Properties properties) {
        super(AZALEA_FLOWER_SUSPICIOUS_STEW_EFFECTS, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return level.getFluidState(pos.above()).isEmpty();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return (double)world.getRandom().nextFloat() < 0.45D;
    }

    @Override
    public void performBonemeal(ServerLevel serverWorld, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
    	ModTreeGrower.AZALEA.growTree(serverWorld, serverWorld.getChunkSource().getGenerator(), pos, state, random);
    }
}
