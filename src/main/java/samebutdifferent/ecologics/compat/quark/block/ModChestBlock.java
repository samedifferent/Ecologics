package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;

public class ModChestBlock extends ChestBlock implements IChestBlock {
    private final ChestVariant variant;

    public ModChestBlock(ChestVariant variant) {
        super(Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), QuarkCompat.CHEST::get);
        this.variant = variant;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return QuarkCompat.CHEST.get().create(pPos, pState);
    }

    @Override
    public ChestVariant getVariant() {
        return variant;
    }
}
