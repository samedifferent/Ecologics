package samebutdifferent.ecologics.platform.forge;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.ecologics.Ecologics;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientPlatformHelperImpl {
    public static final Set<Consumer<ColorHandlerEvent.Item>> ITEM_COLORS = ConcurrentHashMap.newKeySet();
    public static final Set<Consumer<ColorHandlerEvent.Block>> BLOCK_COLORS = ConcurrentHashMap.newKeySet();
    public static final Set<Consumer<EntityRenderersEvent.RegisterRenderers>> ENTITY_RENDERERS = ConcurrentHashMap.newKeySet();
    public static final Set<Consumer<EntityRenderersEvent.RegisterLayerDefinitions>> LAYER_DEFINITIONS = ConcurrentHashMap.newKeySet();

    public static <T extends Block> void setRenderLayer(Supplier<T> block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), type);
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        ENTITY_RENDERERS.add(event -> event.registerEntityRenderer(type.get(), renderProvider));
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ENTITY_RENDERERS.forEach(consumer -> consumer.accept(event));
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T> renderProvider) {
        ENTITY_RENDERERS.add(event -> event.registerBlockEntityRenderer(type.get(), renderProvider));
    }

    public static void addWoodType(WoodType woodType) {
        Sheets.addWoodType(woodType);
    }

    @SafeVarargs
    public static <T extends Block> void registerBlockColors(BlockColor blockColor, Supplier<T>... blocks) {
        ITEM_COLORS.add(event -> Arrays.stream(blocks).forEach(block -> event.getBlockColors().register(blockColor, block.get())));
    }

    @SafeVarargs
    public static <T extends ItemLike> void registerItemColors(ItemColor itemColor, Supplier<T>... items) {
        ITEM_COLORS.add(event -> Arrays.stream(items).forEach(item -> event.getItemColors().register(itemColor, item.get())));
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BLOCK_COLORS.forEach(consumer -> consumer.accept(event));
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ITEM_COLORS.forEach(consumer -> consumer.accept(event));
    }

    public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        LAYER_DEFINITIONS.add(event -> event.registerLayerDefinition(layerLocation, supplier));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LAYER_DEFINITIONS.forEach(consumer -> consumer.accept(event));
    }
}
