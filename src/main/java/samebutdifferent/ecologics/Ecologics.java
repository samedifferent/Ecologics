package samebutdifferent.ecologics;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import samebutdifferent.ecologics.registry.*;

@Mod(Ecologics.MOD_ID)
public class Ecologics {

    public static final String MOD_ID = "ecologics";
    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.COCONUT_LOG.get());
        }
    };

    public Ecologics() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSoundEvents.SOUND_EVENTS.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}
