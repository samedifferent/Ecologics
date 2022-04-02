package samebutdifferent.ecologics.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {

    public static class ModBlockTags {

        public static final TagKey<Block> COCONUT_LOGS = createTag("coconut_logs");
        public static final TagKey<Block> WALNUT_LOGS = createTag("walnut_logs");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.register(new Identifier(Ecologics.MOD_ID, name).toString());
        }
    }

    public static class ModItemTags {

        public static final TagKey<Item> COCONUT_LOGS = createTag("coconut_logs");
        public static final TagKey<Item> WALNUT_LOGS = createTag("walnut_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.register(new Identifier(Ecologics.MOD_ID, name).toString());
        }
    }
}
