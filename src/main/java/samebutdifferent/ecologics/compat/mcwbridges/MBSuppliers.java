package samebutdifferent.ecologics.compat.mcwbridges;

import com.mcwbridges.kikoz.MacawsBridges;
import com.mcwbridges.kikoz.objects.Log_Bridge;
import com.mcwbridges.kikoz.objects.Rope_Bridge;
import com.mcwbridges.kikoz.objects.Support_Pillar;
import com.mcwbridges.kikoz.objects.Iron_Stair;
import com.mcwbridges.kikoz.objects.Rail_Bridge;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import java.util.function.Supplier;

public class MBSuppliers {
    public static Supplier<Block> LOG_BRIDGE_MIDDLE = () -> new Log_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD));
    public static Supplier<Block> ROPE_BRIDGE = () -> new Rope_Bridge(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD));
    public static Supplier<Block> BRIDGE_PIER = () -> new Support_Pillar(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD));
    public static Supplier<Block> LOG_BRIDGE_STAIR = () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD));
    public static Supplier<Block> RAIL_BRIDGE = () -> new Rail_Bridge(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(0.5F, 2.5F).sound(SoundType.WOOD));
    public static Supplier<Block> ROPE_BRIDGE_STAIR = () -> new Iron_Stair(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD));
    public static Supplier<CreativeModeTab> MB_TAB = () -> MacawsBridges.BridgesItemGroup;
}