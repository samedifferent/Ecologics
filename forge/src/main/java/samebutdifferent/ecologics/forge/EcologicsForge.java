package samebutdifferent.ecologics.forge;

import samebutdifferent.ecologics.Ecologics;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ecologics.MOD_ID)
public class EcologicsForge {
    public EcologicsForge() {
        Ecologics.init();
    }
}