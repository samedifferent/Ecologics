package samebutdifferent.ecologics.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {

    public static class ModBlockTags {

        public static final TagKey<Block> COCONUT_LOGS = createTag("coconut_logs");
        public static final TagKey<Block> WALNUT_LOGS = createTag("walnut_logs");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }

    public static class ModItemTags {

        public static final TagKey<Item> COCONUT_LOGS = createTag("coconut_logs");
        public static final TagKey<Item> WALNUT_LOGS = createTag("walnut_logs");
        public static final TagKey<Item> AZALEA_LOGS = createTag("azalea_logs");
        public static final TagKey<Item> FLOWERING_AZALEA_LOGS = createTag("flowering_azalea_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }
}
