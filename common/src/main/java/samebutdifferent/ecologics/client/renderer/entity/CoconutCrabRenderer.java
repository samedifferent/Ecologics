package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.renderer.entity.state.CoconutCrabRenderState;
import samebutdifferent.ecologics.entity.CoconutCrab;

public class CoconutCrabRenderer extends MobRenderer<CoconutCrab, CoconutCrabRenderState, CoconutCrabModel> {

    public CoconutCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CoconutCrabModel(context.bakeLayer(CoconutCrabModel.LAYER_LOCATION)), 0.6F);
    }

    @Override
    public Identifier getTextureLocation(CoconutCrabRenderState entity) {
        return Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
    }

	@Override
	public CoconutCrabRenderState createRenderState() {
		return new CoconutCrabRenderState();
	}
}
