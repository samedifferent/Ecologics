package samebutdifferent.ecologics.compat.quark.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;

public class ModChestBlockEntity extends ChestBlockEntity {
    protected ModChestBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    public ModChestBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(QuarkCompat.CHEST.get(), pWorldPosition, pBlockState);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition.getX() - 1, worldPosition.getY(), worldPosition.getZ() - 1, worldPosition.getX() + 2, worldPosition.getY() + 2, worldPosition.getZ() + 2);
    }
}
