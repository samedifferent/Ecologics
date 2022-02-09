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

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new, ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<PotBlockEntity>> POT = BLOCK_ENTITY_TYPES.register("pot", () -> BlockEntityType.Builder.of(PotBlockEntity::new, ModBlocks.POT.get(), ModBlocks.BLACK_POT.get(), ModBlocks.BLUE_POT.get(), ModBlocks.BROWN_POT.get(), ModBlocks.CYAN_POT.get(), ModBlocks.GRAY_POT.get(), ModBlocks.GREEN_POT.get(), ModBlocks.LIGHT_BLUE_POT.get(), ModBlocks.LIGHT_GRAY_POT.get(), ModBlocks.LIME_POT.get(), ModBlocks.MAGENTA_POT.get(), ModBlocks.ORANGE_POT.get(), ModBlocks.PINK_POT.get(), ModBlocks.PURPLE_POT.get(), ModBlocks.RED_POT.get(), ModBlocks.WHITE_POT.get(), ModBlocks.YELLOW_POT.get()).build(null));
}
