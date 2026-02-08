package samebutdifferent.ecologics.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import samebutdifferent.ecologics.config.ConfigCommon;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

public class ConfigPlatformHelperImpl {
    static ModConfigFabric config = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();

    private static void updateConfig() {
    	ConfigCommon.setCoconutCrabSpawnChance(config.beach.coconutCrabSpawnChance);
    	ConfigCommon.setReplaceAzaleaTree(config.lushCaves.replaceAzaleaTree);
    	ConfigCommon.setFoxesAttackSquirrels(config.plains.foxesAttackSquirrels);
    }
    
    /*public static double coconutCrabSpawnChance() {
        return config.beach.coconutCrabSpawnChance;
    }

    public static boolean replaceAzaleaTree() {
        return config.lushCaves.replaceAzaleaTree;
    }
    
    public static boolean foxesAttackSquirrels() {
        return config.plains.foxesAttackSquirrels;
    }*/
}
