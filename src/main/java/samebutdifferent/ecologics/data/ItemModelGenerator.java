package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Ecologics.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

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
