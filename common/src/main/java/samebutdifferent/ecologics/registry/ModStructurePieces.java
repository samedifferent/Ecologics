package samebutdifferent.ecologics.registry;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.StructureTemplateType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.structure.structures.DesertRuinPieces;

public class ModStructurePieces 
{
	public static void init() {}
	
    public static final Supplier<StructurePieceType> DESERT_RUIN = CommonPlatformHelper.registerStructurePiece("drp", () -> (StructureTemplateType)DesertRuinPieces.DesertRuinPiece::create);
}
