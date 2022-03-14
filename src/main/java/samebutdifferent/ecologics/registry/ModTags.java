package samebutdifferent.ecologics.registry;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {

    public static class ModBlockTags {

        public static final Tag.Identified<Block> COCONUT_LOGS =
                createTag("coconut_logs");

        public static final Tag.Identified<Block> POTS =
                createTag("pots");

        private static Tag.Identified<Block> createTag(String name) {
            return TagFactory.BLOCK.create(new Identifier(Ecologics.MOD_ID, name));
        }
    }

    public static class ModItemTags {
        public static final Tag.Identified<Item> COCONUT_LOGS =
                createTag("coconut_logs");

        public static final Tag.Identified<Item> POTS =
                createTag("pots");

        private static Tag.Identified<Item> createTag(String name) {
            return TagFactory.ITEM.create(new Identifier(Ecologics.MOD_ID, name));
        }
    }
}
