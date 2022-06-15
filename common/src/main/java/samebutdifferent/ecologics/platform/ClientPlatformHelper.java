package samebutdifferent.ecologics.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.Supplier;

public class ClientPlatformHelper {
    @ExpectPlatform
    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T> renderProvider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Block> void setRenderLayer(Supplier<T> block, RenderType type) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addWoodType(WoodType woodType) {
        throw new AssertionError();
    }

    @SafeVarargs
    @ExpectPlatform
    public static <T extends Block> void registerBlockColors(BlockColor blockColor, Supplier<T>... blocks) {
        throw new AssertionError();
    }

    @SafeVarargs
    @ExpectPlatform
    public static <T extends ItemLike> void registerItemColors(ItemColor itemColor, Supplier<T>... items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        throw new AssertionError();
    }
}
