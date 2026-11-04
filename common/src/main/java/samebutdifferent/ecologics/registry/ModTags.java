package samebutdifferent.ecologics.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> COCONUT_CRAB_TEMPT_ITEMS = tag("coconut_crab_tempt_items");
        public static final TagKey<Item> PENGUIN_TEMPT_ITEMS = tag("penguin_tempt_items");
        public static final TagKey<Item> SQUIRREL_TEMPT_ITEMS = tag("squirrel_tempt_items");
        public static final TagKey<Item> SHEARS = tag("c", "shears");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
        
        private static TagKey<Item> tag(String namespace, String path) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
        }
    }

    public static class BlockTags {
        public static final TagKey<Block> COCONUT_PLANTABLE_ON = tag("coconut_plantable_on");
        public static final TagKey<Block> PENGUINS_SPAWNABLE_ON = tag("penguins_spawnable_on");
        public static final TagKey<Block> SAPPY_MAPLE_LOGS = tag("sappy_maple_logs");
        public static final TagKey<Block> HOT_BLOCKS = tag("hot_blocks");
        public static final TagKey<Block> CONDITIONALLY_HOT_BLOCKS = tag("conditionally_hot_blocks");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
    }

    public static class EntityTypeTags {
        public static final TagKey<EntityType<?>> BREAKS_THIN_ICE = tag("breaks_thin_ice");
        public static final TagKey<EntityType<?>> PENGUIN_HUNT_TARGETS = tag("penguin_hunt_targets");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name));
        }
    }
}
