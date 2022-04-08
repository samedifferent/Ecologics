package samebutdifferent.ecologics.compat.decorative_blocks;

import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.registry.ModBlocks;

public class DBCompat {
    public static final RegistryObject<Block> COCONUT_BEAM;
    public static final RegistryObject<Block> COCONUT_SUPPORT;
    public static final RegistryObject<Block> COCONUT_PALISADE;
    public static final RegistryObject<Block> COCONUT_SEAT;

    public static final RegistryObject<Block> WALNUT_BEAM;
    public static final RegistryObject<Block> WALNUT_SUPPORT;
    public static final RegistryObject<Block> WALNUT_PALISADE;
    public static final RegistryObject<Block> WALNUT_SEAT;

    public static final RegistryObject<Block> AZALEA_BEAM;
    public static final RegistryObject<Block> AZALEA_SUPPORT;
    public static final RegistryObject<Block> AZALEA_PALISADE;
    public static final RegistryObject<Block> AZALEA_SEAT;

    public static final RegistryObject<Block> FLOWERING_AZALEA_BEAM;
    public static final RegistryObject<Block> FLOWERING_AZALEA_SUPPORT;
    public static final RegistryObject<Block> FLOWERING_AZALEA_PALISADE;
    public static final RegistryObject<Block> FLOWERING_AZALEA_SEAT;

    static {
        COCONUT_BEAM = ModBlocks.registerBlock("coconut_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.BEAM));
        COCONUT_SUPPORT = ModBlocks.registerBlock("coconut_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.SUPPORT));
        COCONUT_PALISADE = ModBlocks.registerBlock("coconut_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.PALISADE));
        COCONUT_SEAT = ModBlocks.registerBlock("coconut_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.COCONUT, WoodDecorativeBlockTypes.SEAT));

        WALNUT_BEAM = ModBlocks.registerBlock("walnut_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.BEAM));
        WALNUT_SUPPORT = ModBlocks.registerBlock("walnut_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.SUPPORT));
        WALNUT_PALISADE = ModBlocks.registerBlock("walnut_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.PALISADE));
        WALNUT_SEAT = ModBlocks.registerBlock("walnut_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.WALNUT, WoodDecorativeBlockTypes.SEAT));

        AZALEA_BEAM = ModBlocks.registerBlock("azalea_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.BEAM));
        AZALEA_SUPPORT = ModBlocks.registerBlock("azalea_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.SUPPORT));
        AZALEA_PALISADE = ModBlocks.registerBlock("azalea_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.PALISADE));
        AZALEA_SEAT = ModBlocks.registerBlock("azalea_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.AZALEA, WoodDecorativeBlockTypes.SEAT));

        FLOWERING_AZALEA_BEAM = ModBlocks.registerBlock("flowering_azalea_beam", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.BEAM));
        FLOWERING_AZALEA_SUPPORT = ModBlocks.registerBlock("flowering_azalea_support", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.SUPPORT));
        FLOWERING_AZALEA_PALISADE = ModBlocks.registerBlock("flowering_azalea_palisade", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.PALISADE));
        FLOWERING_AZALEA_SEAT = ModBlocks.registerBlock("flowering_azalea_seat", () -> DBBlocks.createDecorativeBlock(DBCompatWoodTypes.FLOWERING_AZALEA, WoodDecorativeBlockTypes.SEAT));
    }

    public static void init() {
    }

}
