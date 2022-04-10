package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import vazkii.quark.content.building.module.HedgesModule;

public class HedgeBlock extends FenceBlock {
    public static final BooleanProperty EXTEND = BooleanProperty.create("extend");
    private static final TagKey<Block> HEDGES = BlockTags.create(new ResourceLocation("quark", "hedges"));

    public HedgeBlock() {
        super(Properties.copy(Blocks.OAK_FENCE));
    }

    @Override
    public boolean connectsTo(BlockState state, boolean isSideSolid, Direction direction) {
        return state.is(HedgesModule.hedgesTag);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return facing == Direction.UP && !state.getValue(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(EXTEND, context.getLevel().getBlockState(context.getClickedPos().below()).is(HEDGES));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        if (facing == Direction.DOWN) {
            return stateIn.setValue(EXTEND, facingState.getBlock() instanceof vazkii.quark.content.building.block.HedgeBlock);
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTEND);
    }
}
