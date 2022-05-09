package samebutdifferent.ecologics.compat.decorative_blocks.block;

import net.minecraft.util.StringRepresentable;

public enum NoSupportFaceShape implements StringRepresentable {
    BIG("big"),
    SMALL("small"),
    HIDDEN("hidden");

    private final String name;

    NoSupportFaceShape(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
