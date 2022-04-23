package samebutdifferent.ecologics.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import samebutdifferent.ecologics.util.SignTypeHelper;

import java.util.Set;

@Mixin(SignType.class)
public class SignTypeMixin implements SignTypeHelper {

    @Shadow
    @Final
    private static Set<SignType> VALUES;

    @Override
    public Set<SignType> eco_getTypes() {
        return VALUES;
    }
}