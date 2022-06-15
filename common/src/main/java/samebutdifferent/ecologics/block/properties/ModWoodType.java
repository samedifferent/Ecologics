package samebutdifferent.ecologics.block.properties;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

public class ModWoodType {
    public static final WoodType COCONUT = CommonPlatformHelper.createWoodType(new ResourceLocation(Ecologics.MOD_ID, "coconut").toString());
    public static final WoodType WALNUT = CommonPlatformHelper.createWoodType(new ResourceLocation(Ecologics.MOD_ID, "walnut").toString());
    public static final WoodType AZALEA = CommonPlatformHelper.createWoodType(new ResourceLocation(Ecologics.MOD_ID, "azalea").toString());;
    public static final WoodType FLOWERING_AZALEA = CommonPlatformHelper.createWoodType(new ResourceLocation(Ecologics.MOD_ID, "flowering_azalea").toString());
}
