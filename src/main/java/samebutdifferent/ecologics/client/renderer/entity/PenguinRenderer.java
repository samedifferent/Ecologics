package samebutdifferent.ecologics.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class PenguinRenderer extends GeoEntityRenderer<Penguin> {

    public PenguinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PenguinModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void render(Penguin entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        if (entity.isBaby()) {
            stack.scale(0.6F, 0.6F, 0.6F);
        }
        if (entity.isOnGround() && entity.getFeetBlockState().is(Blocks.SNOW)) {
            stack.translate(0.0F, 0.126F, 0.0F);
        }
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}
