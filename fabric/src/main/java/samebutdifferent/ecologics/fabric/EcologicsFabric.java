package samebutdifferent.ecologics.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import samebutdifferent.ecologics.Ecologics;
import net.fabricmc.api.ModInitializer;
import samebutdifferent.ecologics.registry.fabric.ModConfigFabric;

import java.util.HashMap;
import java.util.Map;

public class EcologicsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfigFabric.class, GsonConfigSerializer::new);
        Ecologics.init();

        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach(FabricDefaultAttributeRegistry::register);

        Ecologics.commonSetup();
    }
}