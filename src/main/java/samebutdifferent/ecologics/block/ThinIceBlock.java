package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class ThinIceBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public ThinIceBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).friction(0.98F).strength(0.5F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, blockGetter, pos, entityType) -> entityType == EntityType.POLAR_BEAR));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof Player) {
            this.crack(pState, pLevel, pPos);
        }
    }

    private boolean crack(BlockState pState, Level pLevel, BlockPos pPos) {
        int age = pState.getValue(AGE);
        if (age < 2) {
            pLevel.setBlock(pPos, pState.setValue(AGE, age + 1), 2);
            pLevel.playSound(null, pPos, ModSoundEvents.THIN_ICE_CRACK.get(), SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
            return false;
        } else {
            pLevel.removeBlock(pPos, false);
            pLevel.playSound(null, pPos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
