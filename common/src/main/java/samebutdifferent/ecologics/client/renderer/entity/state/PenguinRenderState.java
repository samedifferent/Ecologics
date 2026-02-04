package samebutdifferent.ecologics.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;

public class PenguinRenderState extends HoldingEntityRenderState
{
	public static final Identifier DEFAULT_TEXTURE = Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/penguin.png");
	public float slidingAnimationProgress = 0;
	public float swimmingAnimationProgress = 0;
	public boolean isPregnant;
}
