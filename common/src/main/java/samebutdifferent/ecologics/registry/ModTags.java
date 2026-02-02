package samebutdifferent.ecologics.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> PENGUIN_FOOD = tag("penguin_food");
        public static final TagKey<Item> SQUIRREL_FOOD = tag("squirrel_food");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
    }

    public static class BlockTags {
        public static final TagKey<Block> COCONUT_PLANTABLE_ON = tag("coconut_plantable_on");
        public static final TagKey<Block> PENGUINS_SPAWNABLE_ON = tag("penguins_spawnable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
    }

    public static class EntityTypeTags {
        public static final TagKey<EntityType<?>> PENGUIN_HUNT_TARGETS = tag("penguin_hunt_targets");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
    }
}
