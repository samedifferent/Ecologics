package samebutdifferent.ecologics.client.renderer.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.client.renderer.entity.state.SquirrelRenderState;
import samebutdifferent.ecologics.entity.Squirrel;

public class SquirrelRenderer extends AgeableMobRenderer<Squirrel, SquirrelRenderState, SquirrelModel> {

    public SquirrelRenderer(EntityRendererProvider.Context context) {
        super(context, new SquirrelModel(context.bakeLayer(SquirrelModel.SQUIRREL)), new SquirrelModel(context.bakeLayer(SquirrelModel.SQUIRREL_BABY)), 0.4F);
    }

    @Override
    public Identifier getTextureLocation(SquirrelRenderState entity) {
        return Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/squirrel.png");
    }

	@Override
	public SquirrelRenderState createRenderState() {
		return new SquirrelRenderState();
	}
}