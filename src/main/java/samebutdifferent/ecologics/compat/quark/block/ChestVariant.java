package samebutdifferent.ecologics.compat.quark.block;

import net.minecraft.resources.ResourceLocation;
import samebutdifferent.ecologics.Ecologics;

// Credit to SnappyDragon18 for this helper class https://github.com/SnappyDragon18/Habitat/blob/1.18/src/main/java/mod/schnappdragon/habitat/common/block/ChestVariant.java
public enum ChestVariant {
    COCONUT("coconut"),
    COCONUT_TRAPPED("coconut", true),
    WALNUT("walnut"),
    WALNUT_TRAPPED("walnut", true),
    AZALEA("azalea"),
    AZALEA_TRAPPED("azalea", true),
    FLOWERING_AZALEA("flowering_azalea"),
    FLOWERING_AZALEA_TRAPPED("flowering_azalea", true);

    private final String location;

    ChestVariant(String name, boolean trapped) {
        this.location = name + "/" + name + (trapped ? "_trapped" : "");
    }

    ChestVariant(String name) {
        this(name, false);
    }

    public ResourceLocation getSingle() {
        return new ResourceLocation(Ecologics.MOD_ID, "entity/chest/" + this.location);
    }

    public ResourceLocation getRight() {
        return new ResourceLocation(Ecologics.MOD_ID, "entity/chest/" + this.location + "_right");
    }

    public ResourceLocation getLeft() {
        return new ResourceLocation(Ecologics.MOD_ID, "entity/chest/" + this.location + "_left");
    }
}
