package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.entity.ModHangingSignBlockEntity;
import samebutdifferent.ecologics.block.entity.ModShelfBlockEntity;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;
import java.util.ArrayList;
import java.util.Set;

public class ModBlockEntityTypes 
{
    public static void init() {
    	for (Pair<Identifier, BlockEntityType<?>> registry : BLOCK_ENTITY_TYPES) {
    		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, registry.getA(), registry.getB());
    	}
    }
    
    public static <T extends BlockEntityType<?>> T registerBlockEntityType(String name, T blockentity) {
    	BLOCK_ENTITY_TYPES.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), blockentity));
    	return blockentity;
    }
    
    public static final ArrayList<Pair<Identifier, BlockEntityType<?>>> BLOCK_ENTITY_TYPES = new ArrayList<>();

    public static final BlockEntityType<ModSignBlockEntity> SIGN = registerBlockEntityType("sign", new BlockEntityType<>(ModSignBlockEntity::new,
        Set.of(ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN,
        ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN,
        ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN,
        ModBlocks.FLOWERING_AZALEA_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_SIGN)
    ));
    public static final BlockEntityType<ModHangingSignBlockEntity> HANGING_SIGN = registerBlockEntityType("hanging_sign", new BlockEntityType<>(ModHangingSignBlockEntity::new,
		Set.of(ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN,
        ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN,
        ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN,
        ModBlocks.FLOWERING_AZALEA_HANGING_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN)
    ));
    public static final BlockEntityType<ModShelfBlockEntity> SHELF = registerBlockEntityType("shelf", new BlockEntityType<>(ModShelfBlockEntity::new,Set.of(ModBlocks.AZALEA_SHELF, ModBlocks.COCONUT_SHELF, ModBlocks.WALNUT_SHELF)));
    public static final BlockEntityType<PotBlockEntity> POT = registerBlockEntityType("pot", new BlockEntityType<>(PotBlockEntity::new, Set.of(ModBlocks.POT)));
}
