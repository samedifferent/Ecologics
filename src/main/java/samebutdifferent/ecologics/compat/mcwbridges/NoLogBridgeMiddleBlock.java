package samebutdifferent.ecologics.compat.mcwbridges;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class NoLogBridgeMiddleBlock extends NoBridgeBlock {
    public NoLogBridgeMiddleBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).sound(SoundType.WOOD));
    }
}