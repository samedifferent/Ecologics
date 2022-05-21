package samebutdifferent.ecologics;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.ecologics.compat.ModCompat;
import samebutdifferent.ecologics.registry.*;
import software.bernie.geckolib3.GeckoLib;

@Mod(Ecologics.MOD_ID)
public class Ecologics {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ecologics";
    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.COCONUT_LOG.get());
        }
    };

    public Ecologics() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_CONFIG);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSoundEvents.SOUND_EVENTS.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModTrunkPlacerTypes.TRUNK_PLACER_TYPES.register(bus);
        ModFoliagePlacerTypes.FOLIAGE_PLACER_TYPES.register(bus);
        ModPlacementModifierTypes.PLACEMENT_MODIFIER_TYPES.register(bus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        ModPlacedFeatures.PLACED_FEATURES.register(bus);
        ModMobEffects.MOB_EFFECTS.register(bus);
        ModPotions.POTIONS.register(bus);
        GeckoLib.initialize();
        ModCompat.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

}
