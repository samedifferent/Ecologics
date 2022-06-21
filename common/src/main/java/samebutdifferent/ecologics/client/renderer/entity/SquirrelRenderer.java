package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.Squirrel;

@Environment(EnvType.CLIENT)
public class SquirrelRenderer extends MobRenderer<Squirrel, SquirrelModel> {

    public SquirrelRenderer(EntityRendererProvider.Context context) {
        super(context, new SquirrelModel(context.bakeLayer(SquirrelModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Squirrel entity) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/squirrel.png");
    }
}