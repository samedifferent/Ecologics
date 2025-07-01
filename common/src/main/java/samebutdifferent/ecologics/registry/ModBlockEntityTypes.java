package samebutdifferent.ecologics.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.entity.ModHangingSignBlockEntity;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlockEntityTypes 
{
    public static void init() {
    	for (Pair<ResourceLocation, BlockEntityType<?>> registry : BLOCK_ENTITY_TYPES) {
    		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, registry.getA(), registry.getB());
    	}
    }
    
    public static <T extends BlockEntityType> T registerBlockEntityType(String name, T blockentity) {
    	BLOCK_ENTITY_TYPES.add(new Pair(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), blockentity));
    	return blockentity;
    }
    
    public static final ArrayList<Pair<ResourceLocation, BlockEntityType<?>>> BLOCK_ENTITY_TYPES = new ArrayList();

    public static final BlockEntityType<ModSignBlockEntity> SIGN = registerBlockEntityType("sign", BlockEntityType.Builder.of(ModSignBlockEntity::new,
            ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN,
            ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN,
            ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN,
            ModBlocks.FLOWERING_AZALEA_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_SIGN
    ).build(null));
    public static final BlockEntityType<ModHangingSignBlockEntity> HANGING_SIGN = registerBlockEntityType("hanging_sign", BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
            ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN,
            ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN,
            ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN,
            ModBlocks.FLOWERING_AZALEA_HANGING_SIGN, ModBlocks.FLOWERING_AZALEA_WALL_HANGING_SIGN
    ).build(null));
    public static final BlockEntityType<PotBlockEntity> POT = registerBlockEntityType("pot", BlockEntityType.Builder.of(PotBlockEntity::new, ModBlocks.POT).build(null));
    

}
