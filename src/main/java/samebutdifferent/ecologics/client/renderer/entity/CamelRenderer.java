package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.entity.Camel;

@OnlyIn(Dist.CLIENT)
public class CamelRenderer extends MobRenderer<Camel, CamelModel<Camel>> {
    public CamelRenderer(EntityRendererProvider.Context context) {
        super(context, new CamelModel<>(context.bakeLayer(CamelModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Camel pEntity) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/camel.png");
    }
}
