package samebutdifferent.ecologics.compat.decorative_blocks;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.BeamBlock;
import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.blocks.SeatBlock;
import lilypuree.decorative_blocks.blocks.SupportBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class DBSuppliers {

    public static Supplier<Block> createBeamBlock(DBCompatWoodTypes woodType) {
        return () -> new BeamBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), woodType);
    }

    public static Supplier<Block> createSupportBlock(DBCompatWoodTypes woodType) {
        return () -> new SupportBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), woodType);
    }

    public static Supplier<Block> createPalisadeBlock(DBCompatWoodTypes woodType) {
        return () -> new PalisadeBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 4.0F).sound(SoundType.WOOD), woodType);
    }

    public static Supplier<Block> createSeatBlock(DBCompatWoodTypes woodType) {
        return () -> new SeatBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD), woodType);
    }

    public static Supplier<CreativeModeTab> DB_TAB = () -> Constants.ITEM_GROUP;

}
