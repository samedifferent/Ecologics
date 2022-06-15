package samebutdifferent.ecologics.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoliagePlacerType.class)
public class FoliagePlacerTypeAccessor {
    @Invoker("<init>")
    public static <P extends FoliagePlacer> FoliagePlacerType<P> invokeConstructor(Codec<P> codec) {
        throw new AssertionError();
    }
}
