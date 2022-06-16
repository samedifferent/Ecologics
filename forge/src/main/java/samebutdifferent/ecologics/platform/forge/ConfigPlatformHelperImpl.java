package samebutdifferent.ecologics.platform.forge;

import samebutdifferent.ecologics.registry.forge.ModConfigForge;

public class ConfigPlatformHelperImpl {
    public static double coconutCrabSpawnChance() {
        return ModConfigForge.COCONUT_CRAB_SPAWN_CHANCE.get();
    }

    public static boolean replaceAzaleaTree() {
        return ModConfigForge.REPLACE_AZALEA_TREE.get();
    }
}
