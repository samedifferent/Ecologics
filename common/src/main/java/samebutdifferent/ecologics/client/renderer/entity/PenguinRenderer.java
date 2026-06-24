package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.renderer.entity.layers.PenguinHeldItemLayer;
import samebutdifferent.ecologics.client.renderer.entity.state.PenguinRenderState;
import samebutdifferent.ecologics.entity.Penguin;

@SuppressWarnings("deprecation")
public class PenguinRenderer extends AgeableMobRenderer<Penguin, PenguinRenderState, PenguinModel> {
	
	private static final Identifier PENGUIN_LOCATION = Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/penguin.png");
	private static final Identifier PENGUIN_BABY_LOCATION = Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/penguin_baby.png");

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.PENGUIN)), new PenguinModel(context.bakeLayer(PenguinModel.PENGUIN_BABY)), 0.4F);
        this.addLayer(new PenguinHeldItemLayer(this));
    }

    @Override
    public Identifier getTextureLocation(PenguinRenderState entity) {
    	return entity.isBaby ? PENGUIN_BABY_LOCATION : PENGUIN_LOCATION;
    }

	@Override
	public PenguinRenderState createRenderState() {
		return new PenguinRenderState();
	}
	
	public void extractRenderState(Penguin $$0, PenguinRenderState $$1, float $$2) {
		super.extractRenderState($$0, $$1, $$2);
		$$1.swimmingAnimationProgress = $$0.getSwimmingAnimationProgress($$2);
		$$1.slidingAnimationProgress = $$0.getSlidingAnimationProgress($$2);
		$$1.isPregnant = $$0.isPregnant();
	}
}
