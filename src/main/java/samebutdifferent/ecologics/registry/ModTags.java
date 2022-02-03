package samebutdifferent.ecologics.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {

    public static class ModBlockTags {

        public static final Tags.IOptionalNamedTag<Block> COCONUT_LOGS =
                createTag("coconut_logs");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }

    public static class ModItemTags {
        public static final Tags.IOptionalNamedTag<Item> COCONUT_LOGS =
                createTag("coconut_logs");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }
}
