package samebutdifferent.ecologics.compat.mcwbridges;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class MBCompatClient {

    public static void registerRenderLayers(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(MBCompat.AZALEA_LOG_ROPE_BRIDGE_MIDDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.FLOWERING_AZALEA_LOG_ROPE_BRIDGE_MIDDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.COCONUT_LOG_ROPE_BRIDGE_MIDDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.WALNUT_LOG_ROPE_BRIDGE_MIDDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.AZALEA_ROPE_BRIDGE_STAIR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.FLOWERING_AZALEA_ROPE_BRIDGE_STAIR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.COCONUT_ROPE_BRIDGE_STAIR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MBCompat.WALNUT_ROPE_BRIDGE_STAIR.get(), RenderType.cutout());
    }
}
