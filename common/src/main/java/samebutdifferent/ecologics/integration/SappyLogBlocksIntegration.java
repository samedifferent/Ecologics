package samebutdifferent.ecologics.integration;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class SappyLogBlocksIntegration 
{
    public static final ResourceKey<Block> AUTUMNITY_SAPPY_MAPLE_LOG = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("autumnity", "sappy_maple_log"));
    public static final ResourceKey<Block> AUTUMNITY_SAPPY_MAPLE_WOOD = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("autumnity", "sappy_maple_wood"));
    public static final ResourceKey<Block> AUTUMNITY_STRIPPED_MAPLE_LOG = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("autumnity", "stripped_maple_log"));
    public static final ResourceKey<Block> AUTUMNITY_STRIPPED_MAPLE_WOOD = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("autumnity", "stripped_maple_wood"));
}
