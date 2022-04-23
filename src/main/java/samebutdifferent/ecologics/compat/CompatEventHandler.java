package samebutdifferent.ecologics.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.ecologics.Ecologics;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class CompatEventHandler {

    private static final List<String> portedMods = List.of("azalea", "quarkologics", "quarkazalea", "macawsbridgesazalea");

    // Remaps any block ids to use "ecologics" namespace
    @SubscribeEvent
    public static void onMissingBlockMappings(RegistryEvent.MissingMappings<Block> e) {
        for (var mapping : e.getAllMappings()) {
            if (portedMods.contains(mapping.key.getNamespace())) {
                var remap = new ResourceLocation(Ecologics.MOD_ID, mapping.key.getPath());
                if (ForgeRegistries.BLOCKS.containsKey(remap)) {
                    mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
                } else {
                    mapping.warn();
                }
            }
        }
    }

    // Remaps any item ids to use "ecologics" namespace
    @SubscribeEvent
    public static void onMissingItemMappings(RegistryEvent.MissingMappings<Item> e) {
        for (var mapping : e.getAllMappings()) {
            if (portedMods.contains(mapping.key.getNamespace())) {
                var remap = new ResourceLocation(Ecologics.MOD_ID, mapping.key.getPath());
                if (ForgeRegistries.ITEMS.containsKey(remap)) {
                    mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
                } else {
                    mapping.warn();
                }
            }
        }
    }
}