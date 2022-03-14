package samebutdifferent.ecologics.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.Penguin;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PenguinModel extends AnimatedGeoModel<Penguin> {
    
    @Override
    public Identifier getModelLocation(Penguin penguin) {
        if (penguin.isBaby()) {
            return new Identifier(Ecologics.MOD_ID, "geo/baby_penguin.geo.json");
        } else {
            return new Identifier(Ecologics.MOD_ID, "geo/penguin.geo.json");
        }
    }

    @Override
    public Identifier getTextureLocation(Penguin penguin) {
        if (penguin.isBaby()) {
            return new Identifier(Ecologics.MOD_ID, "textures/entity/baby_penguin.png");
        } else {
            return new Identifier(Ecologics.MOD_ID, "textures/entity/penguin.png");
        }
    }

    @Override
    public Identifier getAnimationFileLocation(Penguin penguin) {
        return new Identifier(Ecologics.MOD_ID, "animations/penguin.animation.json");
    }

    @Override
    public void setLivingAnimations(Penguin entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("head");
        head.setRotationX(extraDataOfType.get(0).headPitch * MathHelper.RADIANS_PER_DEGREE);
        head.setRotationY(extraDataOfType.get(0).netHeadYaw * MathHelper.RADIANS_PER_DEGREE);

        if (!entity.isBaby()) {
            IBone egg = this.getAnimationProcessor().getBone("egg");
            egg.setHidden(!entity.isPregnant());
        }
    }
}
