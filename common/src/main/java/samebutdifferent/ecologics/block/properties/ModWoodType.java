package samebutdifferent.ecologics.block.properties;

import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;

public class ModWoodType {
	
	public static void init() {}
	
    public static final WoodType COCONUT = WoodType.register(new WoodType(Ecologics.MOD_ID + ":coconut", ModBlockSetType.COCONUT));
    public static final WoodType WALNUT = WoodType.register(new WoodType(Ecologics.MOD_ID + ":walnut", ModBlockSetType.WALNUT));
    public static final WoodType AZALEA = WoodType.register(new WoodType(Ecologics.MOD_ID + ":azalea", ModBlockSetType.AZALEA));
    public static final WoodType FLOWERING_AZALEA = WoodType.register(new WoodType(Ecologics.MOD_ID + ":flowering_azalea", ModBlockSetType.FLOWERING_AZALEA));
}
