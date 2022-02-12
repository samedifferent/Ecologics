package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public class BlockstateGenerator extends BlockStateProvider {
    public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Ecologics.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    private void crossModel(Block plant) {
        simpleBlock(plant, models().cross(plant.getRegistryName().getPath(), modLoc("block/" + plant.getRegistryName().getPath())));
    }
}
