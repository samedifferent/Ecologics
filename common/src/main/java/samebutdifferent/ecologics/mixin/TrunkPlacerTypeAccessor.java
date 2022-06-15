package samebutdifferent.ecologics.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrunkPlacerType.class)
public class TrunkPlacerTypeAccessor {
    @Invoker("<init>")
    public static <P extends TrunkPlacer> TrunkPlacerType<P> invokeConstructor(Codec<P> codec) {
        throw new AssertionError();
    }
}
