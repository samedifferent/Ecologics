package samebutdifferent.ecologics.compat;

import net.minecraftforge.fml.loading.FMLLoader;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;

public class ModCompat {
    public static final boolean quark;
    public static final boolean autoreglib;
    public static final boolean decorative_blocks;

    static {
        quark = isModPresent("quark");
        autoreglib = isModPresent("autoreglib");
        decorative_blocks = isModPresent("decorative_blocks");
    }

    public static boolean isModPresent(String modid) {
        return FMLLoader.getLoadingModList().getModFileById(modid) != null;
    }

    public static void init() {
        if (decorative_blocks) DBCompat.init();
        if (quark) QuarkCompat.init();
    }
}
