package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.renderer.entity.state.CoconutCrabRenderState;
import samebutdifferent.ecologics.entity.CoconutCrab;

@SuppressWarnings("deprecation")
public class CoconutCrabRenderer extends AgeableMobRenderer<CoconutCrab, CoconutCrabRenderState, CoconutCrabModel> {

    public CoconutCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CoconutCrabModel(context.bakeLayer(CoconutCrabModel.COCONUT_CRAB)), new CoconutCrabModel(context.bakeLayer(CoconutCrabModel.COCONUT_CRAB_BABY)), 0.6F);
    }

    @Override
    public Identifier getTextureLocation(CoconutCrabRenderState entity) {
        return Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
    }

	@Override
	public CoconutCrabRenderState createRenderState() {
		return new CoconutCrabRenderState();
	}
	
	public void extractRenderState(CoconutCrab $$0, CoconutCrabRenderState $$1, float $$2) {
		super.extractRenderState($$0, $$1, $$2);
		$$1.hasCoconut = $$0.hasCoconut();
	}
}
