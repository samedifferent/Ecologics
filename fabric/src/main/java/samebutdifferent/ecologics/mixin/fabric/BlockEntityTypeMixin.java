package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin<T extends BlockEntity> {
    @SuppressWarnings("unchecked")
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        BlockEntityType<T> that = BlockEntityType.class.cast(this);
        if (BlockEntityType.SIGN.equals(that) && (state.getBlock() instanceof SignBlock || state.getBlock() instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}
