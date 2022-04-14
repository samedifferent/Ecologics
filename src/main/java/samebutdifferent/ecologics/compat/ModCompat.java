package samebutdifferent.ecologics.compat;

import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompat;
import samebutdifferent.ecologics.compat.farmersdelight.FDCompat;
import samebutdifferent.ecologics.compat.mcwbridges.MBCompat;
import samebutdifferent.ecologics.compat.mcwbridges.MBCompatClient;
import samebutdifferent.ecologics.compat.quark.QuarkCompat;
import samebutdifferent.ecologics.compat.quark.QuarkCompatClient;
import samebutdifferent.ecologics.compat.quark.QuarkFlagRecipeCondition;

public class ModCompat {
    public static final boolean quark;
    public static final boolean autoreglib;
    public static final boolean decorative_blocks;
    public static final boolean farmersdelight;
    public static final boolean mcwbridges;

    static {
        quark = isModPresent("quark");
        autoreglib = isModPresent("autoreglib");
        decorative_blocks = isModPresent("decorative_blocks");
        farmersdelight = isModPresent("farmersdelight");
        mcwbridges = isModPresent("mcwbridges");
    }

    public static boolean isModPresent(String modid) {
        return FMLLoader.getLoadingModList().getModFileById(modid) != null;
    }

    public static void init() {
        if (quark) {
            QuarkCompat.init();
            FMLJavaModLoadingContext.get().getModEventBus().addListener(QuarkCompatClient::registerRenderLayers);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(QuarkCompatClient::registerBlockColors);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(QuarkCompatClient::registerItemColors);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(QuarkCompatClient::registerRenderers);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(QuarkCompatClient::stitchTextures);
        }
        if (decorative_blocks) DBCompat.init();
        if (farmersdelight) FDCompat.init();
        if (mcwbridges) {
            MBCompat.init();
            FMLJavaModLoadingContext.get().getModEventBus().addListener(MBCompatClient::registerRenderLayers);
        }
        CraftingHelper.register(new QuarkFlagRecipeCondition.Serializer());
    }
}
