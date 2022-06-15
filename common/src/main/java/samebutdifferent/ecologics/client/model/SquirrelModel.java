package samebutdifferent.ecologics.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.Squirrel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SquirrelModel extends AnimatedGeoModel<Squirrel>  {

    @Override
    public ResourceLocation getModelResource(Squirrel object) {
        return new ResourceLocation(Ecologics.MOD_ID, "geo/squirrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Squirrel object) {
        return new ResourceLocation(Ecologics.MOD_ID, "textures/entity/squirrel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Squirrel animatable) {
        return new ResourceLocation(Ecologics.MOD_ID, "animations/squirrel.animation.json");
    }

    @Override
    public void setLivingAnimations(Squirrel entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("head");
        head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD);
        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}