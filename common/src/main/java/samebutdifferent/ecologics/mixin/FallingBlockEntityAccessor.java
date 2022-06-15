package samebutdifferent.ecologics.mixin;

import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityAccessor {
    @Invoker("<init>")
    public static FallingBlockEntity invokeConstructor(Level level, double x, double y, double z, BlockState blockState) {
        throw new AssertionError();
    }
}
