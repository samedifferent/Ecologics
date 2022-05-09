package samebutdifferent.ecologics.compat.mcwbridges;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class MBCompat {
    public static final String MB_ID = "mcwbridges";
    private static final boolean loaded = ModList.get().isLoaded(MB_ID);

    public static final RegistryObject<Block> AZALEA_LOG_BRIDGE_MIDDLE = registerLogBridgeMiddle("azalea_log_bridge_middle");
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_BRIDGE_MIDDLE = registerLogBridgeMiddle("flowering_azalea_log_bridge_middle");
    public static final RegistryObject<Block> COCONUT_LOG_BRIDGE_MIDDLE = registerLogBridgeMiddle("coconut_log_bridge_middle");
    public static final RegistryObject<Block> WALNUT_LOG_BRIDGE_MIDDLE = registerLogBridgeMiddle("walnut_log_bridge_middle");

    public static final RegistryObject<Block> AZALEA_LOG_ROPE_BRIDGE_MIDDLE = registerRopeBridge("rope_azalea_bridge");
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_ROPE_BRIDGE_MIDDLE = registerRopeBridge("rope_flowering_azalea_bridge");
    public static final RegistryObject<Block> COCONUT_LOG_ROPE_BRIDGE_MIDDLE = registerRopeBridge("rope_coconut_bridge");
    public static final RegistryObject<Block> WALNUT_LOG_ROPE_BRIDGE_MIDDLE = registerRopeBridge("rope_walnut_bridge");

    public static final RegistryObject<Block> AZALEA_BRIDGE_PIER = registerBridgePier("azalea_bridge_pier");
    public static final RegistryObject<Block> FLOWERING_AZALEA_BRIDGE_PIER = registerBridgePier("flowering_azalea_bridge_pier");
    public static final RegistryObject<Block> COCONUT_BRIDGE_PIER = registerBridgePier("coconut_bridge_pier");
    public static final RegistryObject<Block> WALNUT_BRIDGE_PIER = registerBridgePier("walnut_bridge_pier");

    public static final RegistryObject<Block> AZALEA_LOG_BRIDGE_STAIR = registerLogBridgeStair("azalea_log_bridge_stair");
    public static final RegistryObject<Block> FLOWERING_AZALEA_LOG_BRIDGE_STAIR = registerLogBridgeStair("flowering_azalea_log_bridge_stair");
    public static final RegistryObject<Block> COCONUT_LOG_BRIDGE_STAIR = registerLogBridgeStair("coconut_log_bridge_stair");
    public static final RegistryObject<Block> WALNUT_LOG_BRIDGE_STAIR = registerLogBridgeStair("walnut_log_bridge_stair");

    public static final RegistryObject<Block> AZALEA_RAIL_BRIDGE = registerRailBridge("azalea_rail_bridge");
    public static final RegistryObject<Block> FLOWERING_AZALEA_RAIL_BRIDGE = registerRailBridge("flowering_azalea_rail_bridge");
    public static final RegistryObject<Block> COCONUT_RAIL_BRIDGE = registerRailBridge("coconut_rail_bridge");
    public static final RegistryObject<Block> WALNUT_RAIL_BRIDGE = registerRailBridge("walnut_rail_bridge");

    public static final RegistryObject<Block> AZALEA_ROPE_BRIDGE_STAIR = registerRopeBridgeStair("azalea_rope_bridge_stair");
    public static final RegistryObject<Block> FLOWERING_AZALEA_ROPE_BRIDGE_STAIR = registerRopeBridgeStair("flowering_azalea_rope_bridge_stair");
    public static final RegistryObject<Block> COCONUT_ROPE_BRIDGE_STAIR = registerRopeBridgeStair("coconut_rope_bridge_stair");
    public static final RegistryObject<Block> WALNUT_ROPE_BRIDGE_STAIR = registerRopeBridgeStair("walnut_rope_bridge_stair");

    private static RegistryObject<Block> registerLogBridgeMiddle(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.LOG_BRIDGE_MIDDLE : NoLogBridgeMiddleBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerRopeBridge(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.ROPE_BRIDGE : NoRopeBridgeBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerBridgePier(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.BRIDGE_PIER : NoBridgePierBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerLogBridgeStair(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.LOG_BRIDGE_STAIR : NoLogBridgeStairBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerRailBridge(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.RAIL_BRIDGE : NoRailBridgeBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerRopeBridgeStair(String name) {
        var toReturn = ModBlocks.BLOCKS.register(name, loaded ? MBSuppliers.ROPE_BRIDGE_STAIR : NoRopeBridgeStairBlock::new);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> regObj) {
        ModItems.ITEMS.register(name, () -> new BlockItem(regObj.get(),
                loaded ? new Item.Properties().tab(MBSuppliers.MB_TAB.get()) : new Item.Properties()));
    }

    public static void init() {}
}