package samebutdifferent.ecologics.neoforge.integration.vanillabackport;

import com.blackgear.vanillabackport.client.level.renderer.block_entity.ShelfRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import samebutdifferent.ecologics.Ecologics;

public class VanillaBackportClient 
{
    public static void registerRenderers() {
        BlockEntityRenderers.register(((VanillaBackportCompat)Ecologics.vanillaBackport).SHELF_BLOCK_ENTITY, ShelfRenderer::new);
    }
}
