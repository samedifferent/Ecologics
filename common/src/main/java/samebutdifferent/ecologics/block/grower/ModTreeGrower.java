package samebutdifferent.ecologics.block.grower;

import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import samebutdifferent.ecologics.registry.ModFeatures;

public class ModTreeGrower 
{
	public static void init() {}
	
	public static final TreeGrower AZALEA = new TreeGrower("azalea", WeightedList.of(ModFeatures.AZALEA), WeightedList.of(), WeightedList.of(), ModFeatures.AZALEA);
	public static final TreeGrower COCONUT = new TreeGrower("coconut", WeightedList.of(ModFeatures.COCONUT), WeightedList.of(), WeightedList.of(), ModFeatures.COCONUT);
	public static final TreeGrower MAPLE = new TreeGrower("maple", WeightedList.of(new Weighted<>(ModFeatures.RED_MAPLE, 1), new Weighted<>(ModFeatures.ORANGE_MAPLE, 1), new Weighted<>(ModFeatures.YELLOW_MAPLE, 1), new Weighted<>(ModFeatures.GREEN_MAPLE, 1)), WeightedList.of(), WeightedList.of(), ModFeatures.GREEN_MAPLE);
	public static final TreeGrower WALNUT = new TreeGrower("walnut", WeightedList.of(ModFeatures.WALNUT), WeightedList.of(), WeightedList.of(), ModFeatures.WALNUT);
}
