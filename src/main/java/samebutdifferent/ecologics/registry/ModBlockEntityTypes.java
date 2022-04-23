package samebutdifferent.ecologics.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;

public class ModBlockEntityTypes {

    public static void init(){}

    public static final BlockEntityType<PotBlockEntity> POT = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Ecologics.MOD_ID, "pot"), FabricBlockEntityTypeBuilder.create(PotBlockEntity::new, ModBlocks.POT).build(null));
}
