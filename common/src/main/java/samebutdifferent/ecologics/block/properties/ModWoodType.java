package samebutdifferent.ecologics.block.properties;

import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

public class ModWoodType {
    public static final WoodType COCONUT = CommonPlatformHelper.createWoodType(Ecologics.MOD_ID + ":coconut", ModBlockSetType.COCONUT);
    public static final WoodType WALNUT = CommonPlatformHelper.createWoodType(Ecologics.MOD_ID + ":walnut", ModBlockSetType.WALNUT);
}
