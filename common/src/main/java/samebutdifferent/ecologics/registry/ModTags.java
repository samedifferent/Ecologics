package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> PENGUIN_TEMPT_ITEMS = tag("penguin_tempt_items");
        public static final TagKey<Item> SQUIRREL_TEMPT_ITEMS = tag("squirrel_tempt_items");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }

    public static class BlockTags {
        public static final TagKey<Block> PENGUINS_SPAWNABLE_ON = tag("penguins_spawnable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }

    public static class EntityTypeTags {
        public static final TagKey<EntityType<?>> PENGUIN_HUNT_TARGETS = tag("penguin_hunt_targets");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(Ecologics.MOD_ID, name));
        }
    }
}
