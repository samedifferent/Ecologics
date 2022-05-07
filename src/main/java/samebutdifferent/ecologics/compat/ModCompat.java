package samebutdifferent.ecologics.compat;

import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.ModList;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;
import samebutdifferent.ecologics.compat.farmersdelight.FDCompat;
import samebutdifferent.ecologics.compat.mcwbridges.MBCompat;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;
import samebutdifferent.ecologics.compat.quark.QuarkFlagRecipeCondition;

public class ModCompat {
    public static final boolean quark;
    public static final boolean autoreglib;
    public static final boolean decorative_blocks;
    public static final boolean mcwbridges;

    static {
        quark = isModPresent("quark");
        autoreglib = isModPresent("autoreglib");
        decorative_blocks = isModPresent("decorative_blocks");
        mcwbridges = isModPresent("mcwbridges");
    }

    // This function should no longer be necessary once everything is ported
    public static boolean isModPresent(String modid) {
        return ModList.get().isLoaded(modid); // However, I believe this is the more accepted way to check presence
        //return FMLLoader.getLoadingModList().getModFileById(modid) != null;
    }

    public static void init() {
        if (quark) QuarkCompat.init();
        if (decorative_blocks) DBCompat.init();
        FDCompat.init();
        if (mcwbridges) MBCompat.init();
        CraftingHelper.register(new QuarkFlagRecipeCondition.Serializer());
    }
}
