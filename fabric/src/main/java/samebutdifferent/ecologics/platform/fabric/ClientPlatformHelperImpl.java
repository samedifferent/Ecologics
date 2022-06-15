package samebutdifferent.ecologics.platform.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;

import java.util.Arrays;
import java.util.function.Supplier;

public class ClientPlatformHelperImpl {
    public static <T extends Block> void setRenderLayer(Supplier<T> block, RenderType type) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.get(), type);
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        EntityRendererRegistry.register(type.get(), renderProvider);
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T> renderProvider) {
        BlockEntityRendererRegistry.register(type.get(), renderProvider);
    }

    public static void addWoodType(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, new ResourceLocation(Ecologics.MOD_ID, "entity/signs/" + woodType.name())));
    }

    @SafeVarargs
    public static <T extends Block> void registerBlockColors(BlockColor blockColor, Supplier<T>... blocks) {
        Arrays.stream(blocks).forEach(block -> ColorProviderRegistry.BLOCK.register(blockColor, block.get()));
    }

    @SafeVarargs
    public static <T extends ItemLike> void registerItemColors(ItemColor itemColor, Supplier<T>... items) {
        Arrays.stream(items).forEach(item -> ColorProviderRegistry.ITEM.register(itemColor, item.get()));
    }

    public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        EntityModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
    }
}
