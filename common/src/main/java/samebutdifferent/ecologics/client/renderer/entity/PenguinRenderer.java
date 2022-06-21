package samebutdifferent.ecologics.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.renderer.entity.layers.PenguinHeldItemLayer;
import samebutdifferent.ecologics.entity.Penguin;

@Environment(EnvType.CLIENT)
public class PenguinRenderer extends MobRenderer<Penguin, PenguinModel> {

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new PenguinHeldItemLayer(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(Penguin entity) {
        if (entity.isBaby()) {
            return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/baby_penguin.png");
        }
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/penguin.png");
    }
}
