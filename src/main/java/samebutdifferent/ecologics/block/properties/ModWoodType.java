package samebutdifferent.ecologics.block.properties;

import net.minecraft.util.SignType;
import samebutdifferent.ecologics.mixin.MixinSignType;

public class ModWoodType {
    public static final SignType COCONUT = MixinSignType.registerNew(MixinSignType.newSignType("coconut"));
    public static final SignType WALNUT = MixinSignType.registerNew(MixinSignType.newSignType("walnut"));
}
