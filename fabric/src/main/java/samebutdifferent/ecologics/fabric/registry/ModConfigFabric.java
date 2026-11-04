package samebutdifferent.ecologics.fabric.registry;

//import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.event.ConfigSerializeEvent.Load;
import me.shedaniel.autoconfig.event.ConfigSerializeEvent.Save;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.world.InteractionResult;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.fabric.EcologicsFabric;

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
    public Maple maple = new Maple();
    
    @ConfigEntry.Gui.CollapsibleObject
    public Nether nether = new Nether();
    
    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();
    
    public static class Beach {
        @Comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.")
        public double coconutCrabSpawnChance = 0.2;
        @Comment("Generate coconut trees on beaches")
        public boolean generateCoconutTrees = true;
        @Comment("Generate seashells on beaches")
        public boolean generateSeashells = true;
    }

    public static class Desert {
        @Comment("Should vanilla Camels spawn in the desert biome? This is a backport of the change from Spring to Life.")
        public boolean spawnCamels = true;
        @Comment("Should you be able to place Chests on vanilla Camels? Disable for compatibility with certain camel mods.")
        public boolean camelChestsEnabled = true;
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
        @Comment("Should Foxes attack Squirrels?")
        public boolean foxesAttackSquirrels = true;
    }

    public static class LushCaves {
        @Comment("Vanilla Azalea trees will have azalea logs instead of oak logs. If you disable this, you can still obtain this mod's azalea wood by growing a tree from an Azalea Flower")
        public boolean replaceAzaleaTree = true;
        @Comment("Generate surface moss in lush cave biomes")
        public boolean generateSurfaceMoss = true;
    }
    
    public static class Maple {
        @Comment("Generate Maple trees in certain forest biomes")
        public boolean generateMapleTrees = true;

        @Comment("The probability that Sappy Maple Logs will deplete when the bucket hanging from Spile completely fills up.")
        public double mapleSapDepletionChance = 0.2D;
    }
    
    public static class Nether {
        @Comment("Generate Gold Ore Veins in the Nether")
        public boolean generateNetherGoldOreVeins = true;
    }
    
    public static class Misc {
        @Comment("Add Ecologics blocks and items to vanilla creative tabs")
        public boolean useVanillaCreativeTabs = true;
    }
    
    public static class SaveEvent implements Save<ConfigData> {
    	@Override
    	public InteractionResult onSave(ConfigHolder<ConfigData> arg0, ConfigData arg1) {
    		EcologicsFabric.updateConfig();
    		return InteractionResult.SUCCESS;
    	}
    }
    
    public static class LoadEvent implements Load<ConfigData> {
    	@Override
    	public InteractionResult onLoad(ConfigHolder<ConfigData> arg0, ConfigData arg1) {
    		EcologicsFabric.updateConfig();
    		return InteractionResult.SUCCESS;
    	}
    }
}
