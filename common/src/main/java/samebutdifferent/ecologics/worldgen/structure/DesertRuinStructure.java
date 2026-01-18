package samebutdifferent.ecologics.worldgen.structure;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import samebutdifferent.ecologics.registry.ModStructures;
import samebutdifferent.ecologics.worldgen.structure.structures.DesertRuinPieces;

public class DesertRuinStructure extends Structure 
{
	public static final Codec<DesertRuinStructure> CODEC = DesertRuinStructure.simpleCodec(DesertRuinStructure::new);
	
    public DesertRuinStructure(Structure.StructureSettings structureSettings) {
        super(structureSettings);
    }

	@Override
	public StructureType<?> type() {
		return ModStructures.DESERT_RUIN.get();
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
		return DesertRuinStructure.onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, structurePiecesBuilder -> this.generatePieces((StructurePiecesBuilder)structurePiecesBuilder, context));
	}
	
    private void generatePieces(StructurePiecesBuilder builder, Structure.GenerationContext context) {
        BlockPos blockPos = new BlockPos(context.chunkPos().getMinBlockX(), 90, context.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(context.random());
        DesertRuinPieces.addPieces(context.structureTemplateManager(), blockPos, rotation, builder, context.random(), this);
    }
}
