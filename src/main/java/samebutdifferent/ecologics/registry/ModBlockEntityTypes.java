package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Ecologics.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new, ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get(), ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<PotBlockEntity>> POT = BLOCK_ENTITY_TYPES.register("pot", () -> BlockEntityType.Builder.of(PotBlockEntity::new, ModBlocks.POT.get()).build(null));
}
