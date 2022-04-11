package samebutdifferent.ecologics.data;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.core.DBBlocks;
import lilypuree.decorative_blocks.datagen.BlockStateGenerationHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompatWoodTypes;

public class BlockstateGenerator extends BlockStateProvider {
    public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Ecologics.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockStateGenerationHelper generationHelper = new BlockStateGenerationHelper(Ecologics.MOD_ID, this);
        DBCompatWoodTypes[] woodTypes = DBCompatWoodTypes.values();

        for (IWoodType wood : woodTypes) {
            generationHelper.beamBlock(DBBlocks.BEAMS.get(wood));
            generationHelper.palisadeBlock(DBBlocks.PALISADES.get(wood));
            generationHelper.seatBlock(DBBlocks.SEATS.get(wood));
            generationHelper.supportBlock(DBBlocks.SUPPORTS.get(wood));
        }
    }

    private void crossModel(Block plant) {
        simpleBlock(plant, models().cross(plant.getRegistryName().getPath(), modLoc("block/" + plant.getRegistryName().getPath())));
    }
}
