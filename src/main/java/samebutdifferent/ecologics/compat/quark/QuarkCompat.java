package samebutdifferent.ecologics.compat.quark;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;
import vazkii.quark.content.building.block.VerticalSlabBlock;
import vazkii.quark.content.building.module.VerticalSlabsModule;

public class QuarkCompat {
    public static final RegistryObject<Block> COCONUT_VERTICAL_SLAB;
    public static final RegistryObject<Block> WALNUT_VERTICAL_SLAB;
    public static final RegistryObject<Block> AZALEA_VERTICAL_SLAB;
    public static final RegistryObject<Block> FLOWERING_AZALEA_VERTICAL_SLAB;

    static {
        COCONUT_VERTICAL_SLAB = ModBlocks.registerBlock("coconut_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.COCONUT_SLAB.get(), new VerticalSlabsModule()));
        WALNUT_VERTICAL_SLAB = ModBlocks.registerBlock("walnut_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.COCONUT_SLAB.get(), new VerticalSlabsModule()));
        AZALEA_VERTICAL_SLAB = ModBlocks.registerBlock("azalea_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.COCONUT_SLAB.get(), new VerticalSlabsModule()));
        FLOWERING_AZALEA_VERTICAL_SLAB = ModBlocks.registerBlock("flowering_azalea_vertical_slab", () -> new VerticalSlabBlock(ModBlocks.COCONUT_SLAB.get(), new VerticalSlabsModule()));
    }

    public static void init() {
    }
}
