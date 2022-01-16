package samebutdifferent.ecologics.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;

public class CoconutLeavesBlock extends LeavesBlock {

    public CoconutLeavesBlock() {
        super(Properties.copy(Blocks.OAK_LEAVES));
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, 7).setValue(PERSISTENT, true));
    }
}
