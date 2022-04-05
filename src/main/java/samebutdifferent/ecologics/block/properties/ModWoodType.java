package samebutdifferent.ecologics.block.properties;

import net.minecraft.util.SignType;
import samebutdifferent.ecologics.mixin.MixinSignType;

public class ModWoodType {
    public static final SignType COCONUT = MixinSignType.registerNew(MixinSignType.newSignType("coconut"));
    public static final SignType WALNUT = MixinSignType.registerNew(MixinSignType.newSignType("walnut"));
    public static final SignType AZALEA = MixinSignType.registerNew(MixinSignType.newSignType("azalea"));
    public static final SignType FLOWERING_AZALEA = MixinSignType.registerNew(MixinSignType.newSignType("flowering_azalea"));
}
