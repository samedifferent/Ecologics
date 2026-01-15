package samebutdifferent.ecologics.worldgen.structure.pieces;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.StructureTemplateType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.structure.structures.DesertRuinPieces;

public class ModStructurePieces 
{
	public static void init() {}
	
    public static final StructurePieceType DESERT_RUIN = Registry.register(BuiltInRegistries.STRUCTURE_PIECE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "drp"), (StructureTemplateType)DesertRuinPieces.DesertRuinPiece::create);
}
