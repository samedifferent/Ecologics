package samebutdifferent.ecologics.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

public class ConfigPlatformHelperImpl {

    static ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();

    public static double coconutCrabSpawnChance() {
        return config.beach.coconutCrabSpawnChance;
    }

}
