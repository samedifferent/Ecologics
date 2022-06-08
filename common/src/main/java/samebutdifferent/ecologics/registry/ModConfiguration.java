package samebutdifferent.ecologics.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfiguration {
    public static ForgeConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_BEACH = "beach";
    public static final ForgeConfigSpec.DoubleValue COCONUT_CRAB_SPAWN_CHANCE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_COCONUT_TREES;
    public static final ForgeConfigSpec.IntValue COCONUT_TREE_COUNT;
    public static final ForgeConfigSpec.DoubleValue COCONUT_TREE_EXTRA_COUNT_CHANCE;
    public static final ForgeConfigSpec.IntValue COCONUT_TREE_EXTRA_COUNT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_SEASHELLS;

    public static final String CATEGORY_DESERT = "desert";
    public static final ForgeConfigSpec.IntValue CAMEL_SPAWN_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_PRICKLY_PEARS;
    public static final ForgeConfigSpec.DoubleValue PRICKLY_PEAR_GROWTH_CHANCE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_DESERT_RUINS;

    public static final String CATEGORY_SNOWY = "snowy";
    public static final ForgeConfigSpec.IntValue PENGUIN_SPAWN_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_THIN_ICE_PATCHES;
    public static final ForgeConfigSpec.IntValue THIN_ICE_PATCH_COUNT;

    public static final String CATEGORY_PLAINS = "plains";
    public static final ForgeConfigSpec.IntValue SQUIRREL_SPAWN_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_WALNUT_TREES;
    public static final ForgeConfigSpec.BooleanValue REMOVE_PLAINS_OAK_TREES;

    public static final String CATEGORY_LUSH_CAVES = "lush_caves";
    public static final ForgeConfigSpec.BooleanValue REPLACE_AZALEA_TREE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_SURFACE_MOSS;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Beach Update").push(CATEGORY_BEACH);
        COCONUT_CRAB_SPAWN_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.").defineInRange("coconutCrabSpawnChance", 0.2, 0.0, 1.0);
        GENERATE_COCONUT_TREES = COMMON_BUILDER.comment("Generate coconut trees on beaches").define("generateCoconutTrees", true);
        COCONUT_TREE_COUNT = COMMON_BUILDER.comment("Count of coconut trees that should generate.").defineInRange("coconutTreeCount", 0, 0, 100);
        COCONUT_TREE_EXTRA_COUNT = COMMON_BUILDER.comment("Extra count of coconut trees that can generate.").defineInRange("coconutTreeExtraCount", 1, 0, 100);
        COCONUT_TREE_EXTRA_COUNT_CHANCE = COMMON_BUILDER.comment("Chance of the extra coconut trees generating. If you want to make the trees less frequent than the default amount, make this value smaller.").defineInRange("coconutTreeExtraCountChance", 0.5F, 0.0F, 0.5F);
        GENERATE_SEASHELLS = COMMON_BUILDER.comment("Generate seashells on beaches").define("generateSeashells", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Desert Update").push(CATEGORY_DESERT);
        CAMEL_SPAWN_WEIGHT = COMMON_BUILDER.comment("Camel spawn weight. Set to 0 to disable.").defineInRange("camelSpawnWeight", 1, 0, 1000);
        GENERATE_PRICKLY_PEARS = COMMON_BUILDER.comment("Generate prickly pears on top of cacti in the desert").define("generatePricklyPears", true);
        PRICKLY_PEAR_GROWTH_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.").defineInRange("pricklyPearGrowthChance", 1.0, 0.0, 1.0);
        GENERATE_DESERT_RUINS = COMMON_BUILDER.comment("Generate ruins in deserts").define("generateDesertRuins", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Snowy Update").push(CATEGORY_SNOWY);
        PENGUIN_SPAWN_WEIGHT = COMMON_BUILDER.comment("Penguin spawn weight. Set to 0 to disable.").defineInRange("penguinSpawnWeight", 2, 0, 1000);
        GENERATE_THIN_ICE_PATCHES = COMMON_BUILDER.comment("Generate thin ice patches in icy biomes").define("generateThinIcePatches", true);
        THIN_ICE_PATCH_COUNT = COMMON_BUILDER.comment("Thin ice patch count. Greater number = more frequent.").defineInRange("thinIcePatchCount", 15, 0, 1000);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Plains Update").push(CATEGORY_PLAINS);
        SQUIRREL_SPAWN_WEIGHT = COMMON_BUILDER.comment("Squirrel spawn weight. Set to 0 to disable.").defineInRange("squirrelSpawnWeight", 10, 0, 1000);
        GENERATE_WALNUT_TREES = COMMON_BUILDER.comment("Generate walnut trees in plains biomes").define("generateWalnutTrees", true);
        REMOVE_PLAINS_OAK_TREES = COMMON_BUILDER.comment("Remove the oak trees in plains biomes so only walnut trees generate").define("removePlainsOakTrees", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Lush Caves Update").push(CATEGORY_LUSH_CAVES);
        REPLACE_AZALEA_TREE = COMMON_BUILDER.comment("Vanilla Azalea trees will have azalea logs instead of oak logs").define("replaceAzaleaTree", true);
        GENERATE_SURFACE_MOSS = COMMON_BUILDER.comment("Generate surface moss in lush cave biomes").define("generateSurfaceMoss", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
