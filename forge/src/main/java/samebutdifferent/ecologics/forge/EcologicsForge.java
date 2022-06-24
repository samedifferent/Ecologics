package samebutdifferent.ecologics.forge;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.forge.CommonPlatformHelperImpl;
import samebutdifferent.ecologics.registry.forge.ModConfigForge;
import samebutdifferent.ecologics.registry.forge.ModGlobalLootModifiers;

import java.util.HashMap;
import java.util.Map;

@Mod(Ecologics.MOD_ID)
public class EcologicsForge {
    public EcologicsForge() {
        Ecologics.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigForge.COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CommonPlatformHelperImpl.BLOCKS.register(bus);
        CommonPlatformHelperImpl.ITEMS.register(bus);
        CommonPlatformHelperImpl.SOUND_EVENTS.register(bus);
        CommonPlatformHelperImpl.ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.BLOCK_ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.FEATURES.register(bus);
        CommonPlatformHelperImpl.TRUNK_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.FOLIAGE_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.MOB_EFFECTS.register(bus);
        CommonPlatformHelperImpl.POTIONS.register(bus);
        ModGlobalLootModifiers.GLM.register(bus);

        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::setup);
    }

    public void registerEntityAttributes(EntityAttributeCreationEvent event) {
        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach((entity, builder) -> event.put(entity, builder.build()));
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ecologics.commonSetup();
        });
    }
}