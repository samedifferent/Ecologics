package samebutdifferent.ecologics.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.properties.ModWoodType;
import samebutdifferent.ecologics.entity.CoconutCrab;
import samebutdifferent.ecologics.loot.AddItemModifier;
import samebutdifferent.ecologics.registry.*;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {
    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(ModWoodType.COCONUT);
            ModTrunkPlacerTypes.register();
            ModFoliagePlacerTypes.register();
            ModVegetationFeatures.register();
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_LOG.get())
                    .put(ModBlocks.COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get()).build();
            ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT_SLICE.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_HUSK.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_LEAVES.get().asItem(), 0.3F);
        });
    }

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.COCONUT_CRAB.get(), CoconutCrab.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new AddItemModifier.Serializer().setRegistryName(Ecologics.MOD_ID, "add_item"));
    }
}
