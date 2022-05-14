package samebutdifferent.ecologics.compat;

import net.minecraftforge.common.crafting.CraftingHelper;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;
import samebutdifferent.ecologics.compat.farmersdelight.FDCompat;
import samebutdifferent.ecologics.compat.mcwbridges.MBCompat;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;
import samebutdifferent.ecologics.compat.quark.QuarkFlagRecipeCondition;

public class ModCompat {
    public static void init() {
        QuarkCompat.init();
        DBCompat.init();
        FDCompat.init();
        MBCompat.init();
        CraftingHelper.register(new QuarkFlagRecipeCondition.Serializer());
    }
}
