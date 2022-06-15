package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeAccessor {
    @Invoker("<init>")
    static WoodType invokeConstructor(String name) {
        throw new AssertionError();
    }

    @Invoker("register")
    static WoodType invokeRegister(WoodType type) {
        throw new AssertionError();
    }
}