package samebutdifferent.ecologics.compat.quark;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class QuarkCompatClient {
    public static void registerRenderLayers(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.COCONUT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.WALNUT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.COCONUT_LADDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.WALNUT_LADDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.AZALEA_LADDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.FLOWERING_AZALEA_LADDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.COCONUT_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(QuarkCompat.WALNUT_LEAF_CARPET.get(), RenderType.cutoutMipped());
    }

    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), QuarkCompat.COCONUT_HEDGE.get(), QuarkCompat.COCONUT_LEAF_CARPET.get());
    }

    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, QuarkCompat.COCONUT_HEDGE.get(), QuarkCompat.COCONUT_LEAF_CARPET.get());
    }
}
