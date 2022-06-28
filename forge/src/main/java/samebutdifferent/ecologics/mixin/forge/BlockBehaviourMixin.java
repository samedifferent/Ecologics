package samebutdifferent.ecologics.mixin.forge;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import samebutdifferent.ecologics.interfaces.BlockBehaviourInterface;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin implements BlockBehaviourInterface {
    @Shadow
    @Final
    @Mutable
    protected float friction;

    @Override
    public void setFriction(float friction) {
        this.friction = friction;
    }
}
