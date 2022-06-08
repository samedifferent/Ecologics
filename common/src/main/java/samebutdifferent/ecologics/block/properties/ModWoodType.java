package samebutdifferent.ecologics.block.properties;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;

public class ModWoodType {
    public static final WoodType COCONUT = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "coconut").toString());
    public static final WoodType WALNUT = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "walnut").toString());
    public static final WoodType AZALEA = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "azalea").toString());;
    public static final WoodType FLOWERING_AZALEA = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "flowering_azalea").toString());
}
