package samebutdifferent.ecologics.registry.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigNeoForge 
{
    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_BEACH = "beach";
    public static final ModConfigSpec.DoubleValue COCONUT_CRAB_SPAWN_CHANCE;

    public static final String CATEGORY_DESERT = "desert";
    public static final ModConfigSpec.DoubleValue PRICKLY_PEAR_GROWTH_CHANCE;

    public static final String CATEGORY_LUSH_CAVES = "lush_caves";
    public static final ModConfigSpec.BooleanValue REPLACE_AZALEA_TREE;

    static {
    	ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Beach Update").push(CATEGORY_BEACH);
        COCONUT_CRAB_SPAWN_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.").defineInRange("coconutCrabSpawnChance", 0.2, 0.0, 1.0);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Desert Update").push(CATEGORY_DESERT);
        PRICKLY_PEAR_GROWTH_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.").defineInRange("pricklyPearGrowthChance", 1.0, 0.0, 1.0);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Lush Caves Update").push(CATEGORY_LUSH_CAVES);
        REPLACE_AZALEA_TREE = COMMON_BUILDER.comment("Vanilla Azalea trees will have azalea logs instead of oak logs").define("replaceAzaleaTree", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
