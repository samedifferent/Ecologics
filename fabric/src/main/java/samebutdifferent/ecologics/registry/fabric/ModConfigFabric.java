package samebutdifferent.ecologics.registry.fabric;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import samebutdifferent.ecologics.Ecologics;

@Config(name = Ecologics.MOD_ID)
public class ModConfigFabric implements ConfigData {
    // Settings
    @ConfigEntry.Gui.CollapsibleObject
    public Beach beach = new Beach();

    @ConfigEntry.Gui.CollapsibleObject
    public Desert desert = new Desert();

    @ConfigEntry.Gui.CollapsibleObject
    public Snowy snowy = new Snowy();

    @ConfigEntry.Gui.CollapsibleObject
    public Plains plains = new Plains();

    @ConfigEntry.Gui.CollapsibleObject
    public LushCaves lushCaves = new LushCaves();

    @ConfigEntry.Gui.CollapsibleObject
    public NetherWastes netherWastes = new NetherWastes();

    public static class Beach {
        public double coconutCrabSpawnChance = 0.2;
        public boolean generateCoconutTrees = true;
        public boolean generateSeashells = true;
    }

    public static class Desert {
        public boolean spawnCamels = true;
        public boolean generatePricklyPears = true;
        public double pricklyPearGrowthChance = 1.0;
        public boolean generateDesertRuins = true;
    }

    public static class Snowy {
        public boolean spawnPenguins = true;
        public boolean generateThinIcePatches = true;
    }

    public static class Plains {
        public boolean spawnSquirrels = true;
        public boolean generateWalnutTrees = true;
    }

    public static class LushCaves {
        public boolean replaceAzaleaTree = true;
        public boolean generateSurfaceMoss = true;
    }

    public static class NetherWastes {
        public boolean generateGoldOreVeins = true;
    }
}
