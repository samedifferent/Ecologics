package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.entity.CoconutCrab;

public class CoconutCrabRenderer extends MobRenderer<CoconutCrab, CoconutCrabModel<CoconutCrab>> {

    public CoconutCrabRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CoconutCrabModel<>(pContext.bakeLayer(CoconutCrabModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(CoconutCrab pEntity) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
    }
}
