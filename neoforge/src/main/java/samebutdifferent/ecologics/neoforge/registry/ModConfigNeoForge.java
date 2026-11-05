package samebutdifferent.ecologics.neoforge.registry;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import samebutdifferent.ecologics.config.ConfigCommon;

public class ModConfigNeoForge 
{
    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_BEACH = "beach";
    public static final ModConfigSpec.DoubleValue COCONUT_CRAB_SPAWN_CHANCE;

    public static final String CATEGORY_DESERT = "desert";
    public static final ModConfigSpec.DoubleValue PRICKLY_PEAR_GROWTH_CHANCE;
    public static final ModConfigSpec.BooleanValue CAMEL_CHESTS_ENABLED;

    public static final String CATEGORY_LUSH_CAVES = "lush_caves";
    public static final ModConfigSpec.BooleanValue REPLACE_AZALEA_TREE;

    public static final String CATEGORY_PLAINS = "plains";
    public static final ModConfigSpec.BooleanValue FOXES_ATTACK_SQUIRRELS;

    public static final String CATEGORY_MAPLE = "maple";
    public static final ModConfigSpec.DoubleValue MAPLE_SAP_DEPLETION_CHANCE;

    public static final String CATEGORY_MISC = "misc";
    public static final ModConfigSpec.BooleanValue USE_VANILLA_CREATIVE_TABS;
    
    static {
    	ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Beach Update").push(CATEGORY_BEACH);
        COCONUT_CRAB_SPAWN_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should Coconut Crabs spawn when a coconut breaks? Set it to 0.0 to disable this.").defineInRange("coconutCrabSpawnChance", 0.2, 0.0, 1.0);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Desert Update").push(CATEGORY_DESERT);
        PRICKLY_PEAR_GROWTH_CHANCE = COMMON_BUILDER.comment("How often (in percentage) should prickly pears grow when a cactus reaches full height? Set it to 0.0 to disable this.").defineInRange("pricklyPearGrowthChance", 1.0, 0.0, 1.0);
        COMMON_BUILDER.pop();
        
        COMMON_BUILDER.comment("Desert Update").push(CATEGORY_DESERT);
        CAMEL_CHESTS_ENABLED = COMMON_BUILDER.comment("Should you be able to place Chests on vanilla Camels? Disable for compatibility with certain camel mods.").define("camelChestsEnabled", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Lush Caves Update").push(CATEGORY_LUSH_CAVES);
        REPLACE_AZALEA_TREE = COMMON_BUILDER.comment("Vanilla Azalea trees will have azalea logs instead of oak logs").define("replaceAzaleaTree", true);
        COMMON_BUILDER.pop();
        
        COMMON_BUILDER.comment("Plains").push(CATEGORY_PLAINS);
        FOXES_ATTACK_SQUIRRELS = COMMON_BUILDER.comment("Should Foxes attack Squirrels?").define("foxesAttackSquirrels", true);
        COMMON_BUILDER.pop();
        
        COMMON_BUILDER.comment("Maple & Syrup Update").push(CATEGORY_MAPLE);
        MAPLE_SAP_DEPLETION_CHANCE = COMMON_BUILDER.comment("The probability that Sappy Maple Logs will deplete when the bucket hanging from Spile completely fills up.").defineInRange("mapleSapDepletionChance", 0.2D, 0, 1);
        COMMON_BUILDER.pop();
        
        COMMON_BUILDER.comment("Miscellaneous").push(CATEGORY_PLAINS);
        USE_VANILLA_CREATIVE_TABS = COMMON_BUILDER.comment("Should Ecologics items be added to vanilla Creative Tabs?").define("useVanillaCreativeTabs", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { updateConfigs(); }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { updateConfigs(); }
    
    public static void updateConfigs() {
        ConfigCommon.setCoconutCrabSpawnChance(COCONUT_CRAB_SPAWN_CHANCE.get());
        ConfigCommon.setCamelChestsEnabled(CAMEL_CHESTS_ENABLED.get());
        ConfigCommon.setPricklyPearGrowthChance(PRICKLY_PEAR_GROWTH_CHANCE.get());
        ConfigCommon.setReplaceAzaleaTree(REPLACE_AZALEA_TREE.get());
        ConfigCommon.setFoxesAttackSquirrels(FOXES_ATTACK_SQUIRRELS.get());
        ConfigCommon.setMapleSapDepletionChance(MAPLE_SAP_DEPLETION_CHANCE.get());
        ConfigCommon.setUseVanillaTabs(USE_VANILLA_CREATIVE_TABS.get());
    }
}
