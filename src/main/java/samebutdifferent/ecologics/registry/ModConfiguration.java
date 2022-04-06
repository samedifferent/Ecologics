package samebutdifferent.ecologics.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfiguration {
    public static ForgeConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_BEACH = "beach";
    public static final ForgeConfigSpec.DoubleValue COCONUT_CRAB_SPAWN_CHANCE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_COCONUT_TREES;
    public static final ForgeConfigSpec.BooleanValue GENERATE_SEASHELLS;

    public static final String CATEGORY_DESERT = "desert";
    public static final ForgeConfigSpec.BooleanValue SPAWN_CAMELS;
    public static final ForgeConfigSpec.BooleanValue GENERATE_PRICKLY_PEARS;
    public static final ForgeConfigSpec.DoubleValue PRICKLY_PEAR_GROWTH_CHANCE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_DESERT_RUINS;

    public static final String CATEGORY_SNOWY = "snowy";
    public static final ForgeConfigSpec.BooleanValue SPAWN_PENGUINS;
    public static final ForgeConfigSpec.BooleanValue GENERATE_THIN_ICE_PATCHES;

    public static final String CATEGORY_PLAINS = "plains";
    public static final ForgeConfigSpec.BooleanValue SPAWN_SQUIRRELS;
    public static final ForgeConfigSpec.BooleanValue GENERATE_WALNUT_TREES;

    public static final String CATEGORY_LUSH_CAVES = "lush_caves";
    public static final ForgeConfigSpec.BooleanValue REPLACE_AZALEA_TREE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_SURFACE_MOSS;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Beach Update").push(CATEGORY_BEACH);
        COCONUT_CRAB_SPAWN_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.").defineInRange("coconutCrabSpawnChance", 0.2, 0.0, 1.0);
        GENERATE_COCONUT_TREES = COMMON_BUILDER.comment("Generate coconut trees on beaches").define("generateCoconutTrees", true);
        GENERATE_SEASHELLS = COMMON_BUILDER.comment("Generate seashells on beaches").define("generateSeashells", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Desert Update").push(CATEGORY_DESERT);
        SPAWN_CAMELS = COMMON_BUILDER.comment("Spawn camels in deserts").define("spawnCamels", true);
        GENERATE_PRICKLY_PEARS = COMMON_BUILDER.comment("Generate prickly pears on top of cacti in the desert").define("generatePricklyPears", true);
        PRICKLY_PEAR_GROWTH_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.").defineInRange("pricklyPearGrowthChance", 1.0, 0.0, 1.0);
        GENERATE_DESERT_RUINS = COMMON_BUILDER.comment("Generate ruins in deserts").define("generateDesertRuins", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Snowy Update").push(CATEGORY_SNOWY);
        SPAWN_PENGUINS = COMMON_BUILDER.comment("Spawn penguins in snowy biomes").define("spawnPenguins", true);
        GENERATE_THIN_ICE_PATCHES = COMMON_BUILDER.comment("Generate thin ice patches in icy biomes").define("generateThinIcePatches", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Plains Update").push(CATEGORY_PLAINS);
        SPAWN_SQUIRRELS = COMMON_BUILDER.comment("Spawn squirrels in plains biomes").define("spawnSquirrels", true);
        GENERATE_WALNUT_TREES = COMMON_BUILDER.comment("Generate walnut trees in plains biomes instead of oak trees").define("generateWalnutTrees", true);
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
