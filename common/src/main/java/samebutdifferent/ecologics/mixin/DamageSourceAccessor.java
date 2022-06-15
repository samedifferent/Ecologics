package samebutdifferent.ecologics.mixin;

import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public class DamageSourceAccessor {
    @Invoker("<init>")
    public static DamageSource invokeConstructor(String msgId) {
        throw new AssertionError();
    }
}
