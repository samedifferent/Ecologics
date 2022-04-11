package samebutdifferent.ecologics.data;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.datagen.DBItemModels;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.compat.decorative_blocks.DBCompatWoodTypes;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Ecologics.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    DBItemModels.ItemGenerationHelper generationHelper = new DBItemModels.ItemGenerationHelper(Ecologics.MOD_ID, this);
        DBCompatWoodTypes[] var2 = DBCompatWoodTypes.values();

        for (IWoodType wood : var2) {
            ((ItemModelBuilder) this.getBuilder(wood + "_beam")).parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + wood + "_beam_y")));
            ((ItemModelBuilder) this.getBuilder(wood + "_palisade")).parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + wood + "_palisade_inventory")));
            generationHelper.seatModel(wood);
            generationHelper.supportModel(wood);
        }
    }

    private void ofBlock(RegistryObject block) {
        withExistingParent(block.getId().getPath(), modLoc("block/" + block.getId().getPath()));
    }

    private ItemModelBuilder generated(RegistryObject item) {
        return getBuilder(item.getId().getPath()).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/" + item.getId().getPath());
    }

    private ItemModelBuilder blockToItemGenerated(RegistryObject block) {
        return getBuilder(block.getId().getPath()).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "block/" + block.getId().getPath());
    }

    private ItemModelBuilder fence (RegistryObject fence, RegistryObject planks) {
        return getBuilder(fence.getId().getPath()).parent(getExistingFile(mcLoc("block/fence_inventory"))).texture("texture", "block/" + planks.getId().getPath());
    }

    private void trapdoor (RegistryObject trapdoor) {
        withExistingParent(trapdoor.getId().getPath(), modLoc("block/" + trapdoor.getId().getPath() + "_bottom"));
    }
}
