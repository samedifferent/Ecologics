package samebutdifferent.ecologics.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class ThinIceBlock extends Block {
    public static final IntProperty AGE = Properties.AGE_3;

    public ThinIceBlock() {
        super(AbstractBlock.Settings.of(Material.ICE).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning((state, blockGetter, pos, entityType) -> entityType == EntityType.POLAR_BEAR));
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    public void onLandedUpon(World pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (pEntity instanceof PlayerEntity) {
            pLevel.playSound(null, pPos, ModSoundEvents.THIN_ICE_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
            replaceIfThinIce(pPos, 3, pLevel);
            replaceIfThinIce(pPos.north(), 2, pLevel);
            replaceIfThinIce(pPos.east(), 2, pLevel);
            replaceIfThinIce(pPos.south(), 2, pLevel);
            replaceIfThinIce(pPos.west(), 2, pLevel);
            replaceIfThinIce(pPos.north().west(), 1, pLevel);
            replaceIfThinIce(pPos.north().east(), 1, pLevel);
            replaceIfThinIce(pPos.south().west(), 1, pLevel);
            replaceIfThinIce(pPos.south().east(), 1, pLevel);
        }
    }

    private void replaceIfThinIce(BlockPos pPos, int age, World pLevel) {
        if (pLevel.getBlockState(pPos).isOf(ModBlocks.THIN_ICE)) {
            pLevel.setBlockState(pPos, ModBlocks.THIN_ICE.getDefaultState().with(AGE, age), 2);
        }
    }

    @Override
    public void onSteppedOn(World pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof PlayerEntity && pState.get(AGE) > 0) {
            this.crack(pState, pLevel, pPos);
        }
    }

    private boolean crack(BlockState pState, World pLevel, BlockPos pPos) {
        int age = pState.get(AGE);
        if (age < 3) {
            pLevel.setBlockState(pPos, pState.with(AGE, age + 1), 2);
            pLevel.playSound(null, pPos, ModSoundEvents.THIN_ICE_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
            return false;
        } else {
            pLevel.removeBlock(pPos, false);
            pLevel.playSound(null, pPos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState pState) {
        return PistonBehavior.DESTROY;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
