package samebutdifferent.ecologics.data;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class ModBlockLoot extends BlockLoot {
    @Override
    protected void addTables() {
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            dropSelf(block.get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<RegistryObject<Block>> col = new ArrayList<>();
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            col.add(block);
        }
        return col.stream().map(RegistryObject::get)::iterator;
    }
}
