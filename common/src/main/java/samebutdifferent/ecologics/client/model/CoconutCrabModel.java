package samebutdifferent.ecologics.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.CoconutCrab;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CoconutCrabModel extends AnimatedGeoModel<CoconutCrab> {

	@Override
	public ResourceLocation getModelResource(CoconutCrab object) {
		return new ResourceLocation(Ecologics.MOD_ID, "geo/coconut_crab.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CoconutCrab object) {
		return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/coconut_crab.png");
	}

	@Override
	public ResourceLocation getAnimationResource(CoconutCrab animatable) {
		return new ResourceLocation(Ecologics.MOD_ID, "animations/coconut_crab.animation.json");
	}

	@Override
	public void setLivingAnimations(CoconutCrab entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		if (customPredicate == null) return;

		List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

		IBone head = this.getAnimationProcessor().getBone("head");
		head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD);
		head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);

		IBone shell = this.getAnimationProcessor().getBone("shell");
		shell.setHidden(!entity.hasCoconut());
	}
}