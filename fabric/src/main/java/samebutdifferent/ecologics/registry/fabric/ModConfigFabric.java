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
    public Snowy snowy = new Snowy();

    @ConfigEntry.Gui.CollapsibleObject
    public Plains plains = new Plains();

    @ConfigEntry.Gui.CollapsibleObject
    public LushCaves lushCaves = new LushCaves();

    public static class Beach {
        @Comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.")
        public double coconutCrabSpawnChance = 0.2;
        @Comment("Generate coconut trees on beaches")
        public boolean generateCoconutTrees = true;
        @Comment("Generate seashells on beaches")
        public boolean generateSeashells = true;
    }

    public static class Desert {
        @Comment("Spawn camels in the desert biome")
        public boolean spawnCamels = true;
        @Comment("Should prickly pears generate naturally on top of cacti during worldgen?")
        public boolean generatePricklyPears = true;
        @Comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.")
        public double pricklyPearGrowthChance = 1.0;
        @Comment("Generate ruins in deserts")
        public boolean generateDesertRuins = true;
    }

    public static class Snowy {
        @Comment("Spawn penguins in snowy and icy biomes")
        public boolean spawnPenguins = true;
        @Comment("Generate thin ice patches in icy biomes")
        public boolean generateThinIcePatches = true;
    }

    public static class Plains {
        @Comment("Spawn squirrels in the plains biome")
        public boolean spawnSquirrels = true;
        @Comment("Generate walnut trees in plains biomes")
        public boolean generateWalnutTrees = true;
    }

    public static class LushCaves {
        @Comment("Vanilla Azalea trees will have azalea logs instead of oak logs. If you disable this, you can still obtain this mod's azalea wood by growing a tree from an Azalea Flower")
        public boolean replaceAzaleaTree = true;
        @Comment("Generate surface moss in lush cave biomes")
        public boolean generateSurfaceMoss = true;
    }
}
