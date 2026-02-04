package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.renderer.entity.layers.PenguinHeldItemLayer;
import samebutdifferent.ecologics.client.renderer.entity.state.PenguinRenderState;
import samebutdifferent.ecologics.entity.Penguin;

public class PenguinRenderer extends MobRenderer<Penguin, PenguinRenderState, PenguinModel> {

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new PenguinHeldItemLayer(this));
    }

    @Override
    public Identifier getTextureLocation(PenguinRenderState entity) {
        if (entity.isBaby) {
            return Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/baby_penguin.png");
        }
        return Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/penguin.png");
    }

	@Override
	public PenguinRenderState createRenderState() {
		return new PenguinRenderState();
	}
	
	public void extractRenderState(Penguin $$0, PenguinRenderState $$1, float $$2) {
		super.extractRenderState($$0, $$1, $$2);
		$$1.isPregnant = $$0.isPregnant();
	}
}
