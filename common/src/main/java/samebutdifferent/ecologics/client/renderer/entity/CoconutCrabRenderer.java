package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.entity.CoconutCrab;

@Environment(EnvType.CLIENT)
public class CoconutCrabRenderer extends MobRenderer<CoconutCrab, CoconutCrabModel> {

    public CoconutCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CoconutCrabModel(context.bakeLayer(CoconutCrabModel.LAYER_LOCATION)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(CoconutCrab entity) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
    }
}
