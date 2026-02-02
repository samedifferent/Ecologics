package samebutdifferent.ecologics.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;

public class CoconutCrabRenderState extends LivingEntityRenderState
{
	public static final Identifier DEFAULT_TEXTURE = Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
	public float limbSwing;
	public float limbSwingAmount = 1.0F;
	public boolean hasCoconut;
}
