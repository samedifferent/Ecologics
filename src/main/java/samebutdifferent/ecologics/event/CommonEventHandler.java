package samebutdifferent.ecologics.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEventHandler {

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
/*        AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                .put(ModBlocks.COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_LOG.get())
                .put(ModBlocks.COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get()).build();*/
    }
}
