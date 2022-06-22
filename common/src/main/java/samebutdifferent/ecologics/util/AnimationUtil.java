package samebutdifferent.ecologics.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;

@Environment(EnvType.CLIENT)
public class AnimationUtil {
    public static void setInitialValue(ModelPart modelPart) {
        modelPart.setPos(0, 0,0);
        modelPart.setRotation(0, 0, 0);
    }

    public static void setInitialValue(ModelPart modelPart, float x, float y, float z) {
        modelPart.setPos(x, y, z);
        modelPart.setRotation(0, 0, 0);
    }

    public static void setInitialValue(ModelPart modelPart, float x, float y, float z, float xRot, float yRot, float zRot) {
        modelPart.setPos(x, y, z);
        modelPart.setRotation(xRot, yRot, zRot);
    }
}
