package samebutdifferent.ecologics.registry.fabric;

import blue.endless.jankson.Comment;
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
    public LushCaves lushCaves = new LushCaves();

    public static class Beach {
        @Comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.")
        public double coconutCrabSpawnChance = 0.2;
    }

    public static class Desert {
        @Comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.")
        public double pricklyPearGrowthChance = 1.0;
    }

    public static class LushCaves {
        @Comment("Azalea trees grown from Azalea bushes will have azalea logs instead of oak logs")
        public boolean replaceAzaleaTree = true;
    }
}
