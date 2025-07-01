package samebutdifferent.ecologics.block.grower;

import java.util.Optional;

import net.minecraft.world.level.block.grower.TreeGrower;
import samebutdifferent.ecologics.registry.ModFeatures;

public class ModTreeGrower 
{
	public static final TreeGrower AZALEA = new TreeGrower("azalea", Optional.empty(), Optional.of(ModFeatures.ROOTED_AZALEA_TREE), Optional.empty());
	public static final TreeGrower COCONUT = new TreeGrower("coconut", Optional.empty(), Optional.of(ModFeatures.COCONUT_REGULAR), Optional.empty());
	public static final TreeGrower WALNUT = new TreeGrower("walnut", Optional.empty(), Optional.of(ModFeatures.WALNUT_REGULAR), Optional.empty());
}
