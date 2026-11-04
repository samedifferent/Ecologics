package samebutdifferent.ecologics.neoforge.integration;

import com.simibubi.create.api.behaviour.spouting.CauldronSpoutingBehavior;
import com.simibubi.create.api.behaviour.spouting.CauldronSpoutingBehavior.CauldronInfo;

import samebutdifferent.ecologics.neoforge.registry.ModFluidTypes;
import samebutdifferent.ecologics.registry.ModBlocks;

public class CreateCompat
{
    public CreateCompat() {
        CauldronSpoutingBehavior.CAULDRON_INFO.register(ModFluidTypes.MAPLE_SAP.get(), new CauldronInfo(1000, ModBlocks.MAPLE_SAP_CAULDRON));
    }
}
