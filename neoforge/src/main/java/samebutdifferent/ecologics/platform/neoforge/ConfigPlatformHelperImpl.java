package samebutdifferent.ecologics.platform.neoforge;

import samebutdifferent.ecologics.registry.neoforge.ModConfigNeoForge;

public class ConfigPlatformHelperImpl {
    public static double coconutCrabSpawnChance() {
        return ModConfigNeoForge.COCONUT_CRAB_SPAWN_CHANCE.get();
    }

    public static boolean replaceAzaleaTree() {
        return ModConfigNeoForge.REPLACE_AZALEA_TREE.get();
    }
}
