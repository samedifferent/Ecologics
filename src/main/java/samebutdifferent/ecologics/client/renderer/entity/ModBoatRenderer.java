package samebutdifferent.ecologics.client.renderer.entity;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;

public class ModBoatRenderer<T extends BoatEntity> extends EntityRenderer<T> {
    public static final EntityModelLayer COCONUT_LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "boat/coconut"), "main");
    public static final EntityModelLayer WALNUT_LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "boat/walnut"), "main");
    public static final EntityModelLayer AZALEA_LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "boat/azalea"), "main");
    public static final EntityModelLayer FLOWERING_AZALEA_LAYER_LOCATION = new EntityModelLayer(new Identifier(Ecologics.MOD_ID, "boat/flowering_azalea"), "main");

    private final Pair<Identifier, BoatEntityModel> coconut;
    private final Pair<Identifier, BoatEntityModel> walnut;
    private final Pair<Identifier, BoatEntityModel> azalea;
    private final Pair<Identifier, BoatEntityModel> flowering_azalea;

    public ModBoatRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.8F;

        coconut = new Pair<>(new Identifier(Ecologics.MOD_ID, "textures/entity/boat/coconut.png"), new BoatEntityModel(context.getPart(COCONUT_LAYER_LOCATION)));
        walnut = new Pair<>(new Identifier(Ecologics.MOD_ID, "textures/entity/boat/walnut.png"), new BoatEntityModel(context.getPart(WALNUT_LAYER_LOCATION)));
        azalea = new Pair<>(new Identifier(Ecologics.MOD_ID, "textures/entity/boat/azalea.png"), new BoatEntityModel(context.getPart(AZALEA_LAYER_LOCATION)));
        flowering_azalea = new Pair<>(new Identifier(Ecologics.MOD_ID, "textures/entity/boat/flowering_azalea.png"), new BoatEntityModel(context.getPart(FLOWERING_AZALEA_LAYER_LOCATION)));

    }

    @Override
    public void render(T boatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));
        float h = (float)boatEntity.getDamageWobbleTicks() - g;
        float j = boatEntity.getDamageWobbleStrength() - g;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10.0F * (float)boatEntity.getDamageWobbleSide()));
        }

        float k = boatEntity.interpolateBubbleWobble(g);
        if (!MathHelper.approximatelyEquals(k, 0.0F)) {
            matrixStack.multiply(new Quaternion(new Vec3f(1.0F, 0.0F, 1.0F), boatEntity.interpolateBubbleWobble(g), true));
        }

        Pair<Identifier, BoatEntityModel> pair = getBoatType((ModBoat) boatEntity);
        Identifier identifier = pair.getFirst();
        BoatEntityModel boatEntityModel = pair.getSecond();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        boatEntityModel.setAngles(boatEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(boatEntityModel.getLayer(identifier));
        boatEntityModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boatEntity.isSubmergedInWater()) {
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
            boatEntityModel.getWaterPatch().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
        }

        matrixStack.pop();
        super.render(boatEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(T boatEntity) {
        return getBoatType((ModBoat) boatEntity).getFirst();
    }

    public Pair<Identifier, BoatEntityModel> getBoatType(ModBoat boat) {
        switch (boat.getWoodType()) {
            case "walnut":
                return walnut;
            case "azalea":
                return azalea;
            case "flowering_azalea":
                return flowering_azalea;
            default:
                return coconut;
        }
    }
}
