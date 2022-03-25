package samebutdifferent.ecologics.block.properties;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;

public class ModWoodType {
    public static final WoodType COCONUT = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "coconut").toString());
    public static final WoodType WALNUT = WoodType.create(new ResourceLocation(Ecologics.MOD_ID, "walnut").toString());
}
