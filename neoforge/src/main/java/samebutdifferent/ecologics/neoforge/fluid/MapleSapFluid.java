package samebutdifferent.ecologics.neoforge.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModParticleTypes;

public class MapleSapFluid extends Fluid
{	
    public static class Source extends BaseFlowingFluid.Source {
        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public Item getBucket() {
            return ModItems.MAPLE_SAP_BUCKET;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
        
        @Override
        protected ParticleOptions getDripParticle() {
            return ModParticleTypes.DRIPPING_MAPLE_SAP;
        }
    }

	@Override
	public Item getBucket() {
		return ModItems.MAPLE_SAP_BUCKET;
	}

	@Override
	protected boolean canBeReplacedWith(FluidState fluidState, BlockGetter p_76128_, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	protected Vec3 getFlow(BlockGetter p_76110_, BlockPos pos, FluidState fluidState) {
		return Vec3.ZERO;
	}

	@Override
	public int getTickDelay(LevelReader reader) {
		return 0;
	}

	@Override
	protected float getExplosionResistance() {
		return 0;
	}

	@Override
	public float getHeight(FluidState fluidState, BlockGetter getter, BlockPos pos) {
		return 0;
	}

	@Override
	public float getOwnHeight(FluidState fluidState) {
		return 0;
	}

	@Override
	protected BlockState createLegacyBlock(FluidState fluidState) {
		return Blocks.WATER.defaultBlockState();
	}

	@Override
	public boolean isSource(FluidState fluidState) {
		return true;
	}

	@Override
	public int getAmount(FluidState fluidState) {
		return 0;
	}

	@Override
	public VoxelShape getShape(FluidState fluidState, BlockGetter p_76138_, BlockPos pos) {
		return Shapes.block();
	}
	
    @Override
    protected ParticleOptions getDripParticle() {
        return ModParticleTypes.DRIPPING_MAPLE_SAP;
    }
}
