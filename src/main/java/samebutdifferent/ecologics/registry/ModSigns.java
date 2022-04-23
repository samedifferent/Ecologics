package samebutdifferent.ecologics.registry;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.mixin.SignTypeAccessor;

import static samebutdifferent.ecologics.Ecologics.MOD_ID;

public class ModSigns {

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "azalea_sign"), AZALEA_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "azalea_wall_sign"), AZALEA_WALL_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "flowering_azalea_sign"), FLOWERING_AZALEA_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "flowering_azalea_wall_sign"), FLOWERING_AZALEA_WALL_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "coconut_sign"), COCONUT_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "coconut_wall_sign"), COCONUT_WALL_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "walnut_sign"), WALNUT_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "walnut_wall_sign"), WALNUT_WALL_SIGN);
    }


    public static final SignType AZALEA_SIGN_TYPE =  SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("azalea"));
    public static final Block AZALEA_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), AZALEA_SIGN_TYPE);
    public static final Block AZALEA_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(AZALEA_SIGN), AZALEA_SIGN_TYPE);
    public static final SignType FLOWERING_AZALEA_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("flowering_azalea"));
    public static final Block FLOWERING_AZALEA_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), FLOWERING_AZALEA_SIGN_TYPE);
    public static final Block FLOWERING_AZALEA_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(FLOWERING_AZALEA_SIGN), FLOWERING_AZALEA_SIGN_TYPE);
    public static final SignType WALNUT_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("walnut"));
    public static final Block WALNUT_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), WALNUT_SIGN_TYPE);
    public static final Block WALNUT_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(WALNUT_SIGN), WALNUT_SIGN_TYPE);
    public static final SignType COCONUT_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("coconut"));
    public static final Block COCONUT_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), COCONUT_SIGN_TYPE);
    public static final Block COCONUT_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(COCONUT_SIGN), COCONUT_SIGN_TYPE);
}
