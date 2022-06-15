package samebutdifferent.ecologics;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

public class Ecologics {
    public static final String MOD_ID = "ecologics";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final CreativeModeTab TAB = CommonPlatformHelper.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(Items.APPLE));

    public static void init() {

    }
}