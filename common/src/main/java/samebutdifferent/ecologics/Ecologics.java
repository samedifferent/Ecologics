package samebutdifferent.ecologics;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.registry.*;
import software.bernie.geckolib3.GeckoLib;

public class Ecologics {
    public static final String MOD_ID = "ecologics";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final CreativeModeTab TAB = CommonPlatformHelper.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(Items.APPLE));

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
        ModFeatures.init();
        ModTrunkPlacerTypes.init();
        ModFoliagePlacerTypes.init();
        ModMobEffects.init();
        ModPotions.init();
        GeckoLib.initialize();
    }
}