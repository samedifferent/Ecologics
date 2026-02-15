package samebutdifferent.ecologics.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import samebutdifferent.ecologics.Ecologics;

public class ModLootTables 
{
	public static final ResourceKey<LootTable> PENGUIN_GROW = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "gameplay/penguin_grow"));
}
