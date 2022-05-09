package samebutdifferent.ecologics.compat.decorative_blocks;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import net.minecraft.world.level.block.Block;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModBlocks;

public enum DBCompatWoodTypes implements IWoodType {
    COCONUT("coconut"),
    WALNUT("walnut"),
    AZALEA("azalea"),
    FLOWERING_AZALEA("flowering_azalea");

    private final String name;

    DBCompatWoodTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String namespace() {
        return Ecologics.MOD_ID;
    }

    @Override
    public Block getLog() {
        switch(this) {
            case WALNUT:
                return ModBlocks.WALNUT_LOG.get();
            case AZALEA:
                return ModBlocks.AZALEA_LOG.get();
            case FLOWERING_AZALEA:
                return ModBlocks.FLOWERING_AZALEA_LOG.get();
            default:
                return ModBlocks.COCONUT_LOG.get();
        }
    }

    @Override
    public Block getStrippedLog() {
        switch(this) {
            case WALNUT:
                return ModBlocks.STRIPPED_WALNUT_LOG.get();
            case AZALEA:
            case FLOWERING_AZALEA:
                return ModBlocks.STRIPPED_AZALEA_LOG.get();
            default:
                return ModBlocks.STRIPPED_COCONUT_LOG.get();
        }
    }

    @Override
    public Block getSlab() {
        switch(this) {
            case WALNUT:
                return ModBlocks.WALNUT_SLAB.get();
            case AZALEA:
                return ModBlocks.AZALEA_SLAB.get();
            case FLOWERING_AZALEA:
                return ModBlocks.FLOWERING_AZALEA_SLAB.get();
            default:
                return ModBlocks.COCONUT_SLAB.get();
        }
    }

    @Override
    public Block getFence() {
        switch(this) {
            case WALNUT:
                return ModBlocks.WALNUT_FENCE.get();
            case AZALEA:
                return ModBlocks.AZALEA_FENCE.get();
            case FLOWERING_AZALEA:
                return ModBlocks.FLOWERING_AZALEA_FENCE.get();
            default:
                return ModBlocks.COCONUT_FENCE.get();
        }
    }

    @Override
    public Block getPlanks() {
        switch(this) {
            case WALNUT:
                return ModBlocks.WALNUT_PLANKS.get();
            case AZALEA:
                return ModBlocks.AZALEA_PLANKS.get();
            case FLOWERING_AZALEA:
                return ModBlocks.FLOWERING_AZALEA_PLANKS.get();
            default:
                return ModBlocks.COCONUT_PLANKS.get();
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean isFlammable() {
        return true;
    }

    public static DBCompatWoodTypes withName(String name) {
        if (name.equalsIgnoreCase("walnut")) {
            return WALNUT;
        } else if (name.equalsIgnoreCase("azalea")) {
            return AZALEA;
        } else if (name.equalsIgnoreCase("flowering_azalea")) {
            return FLOWERING_AZALEA;
        } else {
            return COCONUT;
        }
    }
}
