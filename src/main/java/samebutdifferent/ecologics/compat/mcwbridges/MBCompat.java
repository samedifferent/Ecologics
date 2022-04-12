package samebutdifferent.ecologics.compat.mcwbridges;

import com.mcwbridges.kikoz.MacawsBridges;
import com.mcwbridges.kikoz.objects.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class MBCompat {
    public static final RegistryObject<Block> AZALEA_LOG_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> COCONUT_LOG_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> WALNUT_LOG_BRIDGE_MIDDLE;

    public static final RegistryObject<Block> AZALEA_LOG_ROPE_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_ROPE_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> COCONUT_LOG_ROPE_BRIDGE_MIDDLE;
    public static final RegistryObject<Block> WALNUT_LOG_ROPE_BRIDGE_MIDDLE;

    public static final RegistryObject<Block> AZALEA_BRIDGE_PIER;
    public static final RegistryObject<Block> FLOWERING_AZALEA_BRIDGE_PIER;
    public static final RegistryObject<Block> COCONUT_BRIDGE_PIER;
    public static final RegistryObject<Block> WALNUT_BRIDGE_PIER;

    public static final RegistryObject<Block> AZALEA_LOG_BRIDGE_STAIR;
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_BRIDGE_STAIR;
    public static final RegistryObject<Block> COCONUT_LOG_BRIDGE_STAIR;
    public static final RegistryObject<Block> WALNUT_LOG_BRIDGE_STAIR;

    public static final RegistryObject<Block> AZALEA_RAIL_BRIDGE;
    public static final RegistryObject<Block> FLOWERING_AZALEA_RAIL_BRIDGE;
    public static final RegistryObject<Block> COCONUT_RAIL_BRIDGE;
    public static final RegistryObject<Block> WALNUT_RAIL_BRIDGE;

    public static final RegistryObject<Block> AZALEA_ROPE_BRIDGE_STAIR;
    public static final RegistryObject<Block> FLOWERING_AZALEA_ROPE_BRIDGE_STAIR;
    public static final RegistryObject<Block> COCONUT_ROPE_BRIDGE_STAIR;
    public static final RegistryObject<Block> WALNUT_ROPE_BRIDGE_STAIR;

    static {
        AZALEA_LOG_BRIDGE_MIDDLE = registerBlock("azalea_log_bridge_middle", () -> new Log_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_LOG_BRIDGE_MIDDLE = registerBlock("flowering_azalea_log_bridge_middle", () -> new Log_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        COCONUT_LOG_BRIDGE_MIDDLE = registerBlock("coconut_log_bridge_middle", () -> new Log_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        WALNUT_LOG_BRIDGE_MIDDLE = registerBlock("walnut_log_bridge_middle", () -> new Log_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));

        AZALEA_LOG_ROPE_BRIDGE_MIDDLE = registerBlock("rope_azalea_bridge", () -> new Rope_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_LOG_ROPE_BRIDGE_MIDDLE = registerBlock("rope_flowering_azalea_bridge", () -> new Rope_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        COCONUT_LOG_ROPE_BRIDGE_MIDDLE = registerBlock("rope_coconut_bridge", () -> new Rope_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        WALNUT_LOG_ROPE_BRIDGE_MIDDLE = registerBlock("rope_walnut_bridge", () -> new Rope_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));

        AZALEA_BRIDGE_PIER = registerBlock("azalea_bridge_pier", () -> new Support_Pillar(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_BRIDGE_PIER = registerBlock("flowering_azalea_bridge_pier", () -> new Support_Pillar(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        COCONUT_BRIDGE_PIER = registerBlock("coconut_bridge_pier", () -> new Support_Pillar(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        WALNUT_BRIDGE_PIER = registerBlock("walnut_bridge_pier", () -> new Support_Pillar(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));

        AZALEA_LOG_BRIDGE_STAIR = registerBlock("azalea_log_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_LOG_BRIDGE_STAIR = registerBlock("flowering_azalea_log_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        COCONUT_LOG_BRIDGE_STAIR = registerBlock("coconut_log_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        WALNUT_LOG_BRIDGE_STAIR = registerBlock("walnut_log_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD)));

        AZALEA_RAIL_BRIDGE = registerBlock("azalea_rail_bridge", () -> new Rail_Bridge(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_RAIL_BRIDGE = registerBlock("flowering_azalea_rail_bridge", () -> new Rail_Bridge(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        COCONUT_RAIL_BRIDGE = registerBlock("coconut_rail_bridge", () -> new Rail_Bridge(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD)));
        WALNUT_RAIL_BRIDGE = registerBlock("walnut_rail_bridge", () -> new Rail_Bridge(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD)));

        AZALEA_ROPE_BRIDGE_STAIR = registerBlock("azalea_rope_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD)));
        FLOWERING_AZALEA_ROPE_BRIDGE_STAIR = registerBlock("flowering_azalea_rope_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD)));
        COCONUT_ROPE_BRIDGE_STAIR = registerBlock("coconut_rope_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD)));
        WALNUT_ROPE_BRIDGE_STAIR = registerBlock("walnut_rope_bridge_stair", () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD)));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(MacawsBridges.BridgesItemGroup)));
        return toReturn;
    }

    public static void init() {
    }
}
