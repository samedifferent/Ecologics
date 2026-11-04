package samebutdifferent.ecologics.fabric.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class EcologicsMixinConfig implements IMixinConfigPlugin 
{
    @Override
    public void onLoad(String mixinPackage) {
        // ModConfigNeoForge.updateConfigs();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName == "CamelHuskRendererMixin") {
            return FabricLoader.getInstance().isModLoaded("vanillabackport");
        }
        if (mixinClassName == "PaleMossCarpetMixin") {
            return FabricLoader.getInstance().isModLoaded("vanillabackport");
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<String>();
        if (FabricLoader.getInstance().isModLoaded("vanillabackport")) {
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                mixins.add("CamelHuskRendererMixin");
            }
            mixins.add("PaleMossCarpetMixin");
        }
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

}
