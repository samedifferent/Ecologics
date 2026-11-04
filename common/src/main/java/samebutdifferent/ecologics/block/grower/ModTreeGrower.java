package samebutdifferent.ecologics.block.grower;

import java.util.Optional;

import net.minecraft.world.level.block.grower.TreeGrower;
import samebutdifferent.ecologics.registry.ModFeatures;

public class ModTreeGrower 
{
    public static final TreeGrower AZALEA = new TreeGrower("azalea", Optional.empty(), Optional.of(ModFeatures.AZALEA_REGULAR), Optional.empty());
    public static final TreeGrower COCONUT = new TreeGrower("coconut", Optional.empty(), Optional.of(ModFeatures.COCONUT_REGULAR), Optional.empty());
    public static final TreeGrower MAPLE = new TreeGrower("maple", 0.5F, Optional.empty(), Optional.empty(), Optional.of(ModFeatures.YELLOW_MAPLE), Optional.of(ModFeatures.ORANGE_MAPLE), Optional.of(ModFeatures.RED_MAPLE), Optional.of(ModFeatures.MAPLE_BEES_01));
    public static final TreeGrower WALNUT = new TreeGrower("walnut", Optional.empty(), Optional.of(ModFeatures.WALNUT_REGULAR), Optional.empty());
}
