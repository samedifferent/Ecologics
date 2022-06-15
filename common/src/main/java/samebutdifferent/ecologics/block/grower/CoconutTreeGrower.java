package samebutdifferent.ecologics.block.grower;

import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;

public class CoconutTreeGrower extends ModTreeGrower {
    @Override
    protected ResourceLocation getConfiguredFeatureLocation() {
        return new ResourceLocation(Ecologics.MOD_ID, "coconut");
    }
}
