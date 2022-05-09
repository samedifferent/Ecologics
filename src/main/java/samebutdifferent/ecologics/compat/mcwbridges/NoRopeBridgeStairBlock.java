package samebutdifferent.ecologics.compat.mcwbridges;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class NoRopeBridgeStairBlock extends NoBridgeBlock {
    public NoRopeBridgeStairBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.8F, 2.0F).sound(SoundType.WOOD));
    }
}