package samebutdifferent.ecologics.neoforge.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.LoadingModList;

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
            return LoadingModList.get().getModFileById("vanillabackport") != null;
        }
        if (mixinClassName == "PaleMossCarpetMixin") {
            return LoadingModList.get().getModFileById("vanillabackport") != null;
        }
        if (mixinClassName == "CreateFluidTargetsMixin") {
            return LoadingModList.get().getModFileById("create") != null;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<String>();
        if (LoadingModList.get().getModFileById("vanillabackport") != null) {
            if (FMLEnvironment.dist.isClient()) {
                mixins.add("CamelHuskRendererMixin");
            }
            mixins.add("PaleMossCarpetMixin");
        }
        if (LoadingModList.get().getModFileById("create") != null) {
            mixins.add("CreateFluidTargetsMixin");
        }
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

}
