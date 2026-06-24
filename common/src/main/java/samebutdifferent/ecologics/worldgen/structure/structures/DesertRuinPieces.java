package samebutdifferent.ecologics.worldgen.structure.structures;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.CappedProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosAlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.structure.DesertRuinStructure;
import samebutdifferent.ecologics.worldgen.structure.pieces.ModStructurePieces;

public class DesertRuinPieces {

    static final StructureProcessor DESERT_RUIN_PROCESSOR = DesertRuinPieces.archyRuleProcessor(Blocks.SAND, Blocks.SUSPICIOUS_SAND, BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY);
    private static final Identifier[] DESERT_RUINS = new Identifier[]{Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/chest_house"), Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/pit"), Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/pillars1"), Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/pillars2"), Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/wall1"), Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "desert_ruin/wall2")};

    private static StructureProcessor archyRuleProcessor(Block block, Block suspiciousBlock, ResourceKey<LootTable> lootTable) {
        return new CappedProcessor(new RuleProcessor(List.of(new ProcessorRule(new BlockMatchTest(block), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, suspiciousBlock.defaultBlockState(), new AppendLoot(lootTable)))), ConstantInt.of(5));
    }
    
    private static Identifier getRandomRuin(RandomSource random) {
        return Util.getRandom(DESERT_RUINS, random);
    }
    
    public static void addPieces(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor structurePieceAccessor, RandomSource random, DesertRuinStructure structure) {
        DesertRuinPieces.addPiece(structureTemplateManager, pos, rotation, structurePieceAccessor, random, structure, 0.96F);
        if (random.nextFloat() <= 0.4F) {
            DesertRuinPieces.addClusterRuins(structureTemplateManager, random, rotation, pos, structure, structurePieceAccessor);
        }
    }

    private static void addClusterRuins(StructureTemplateManager structureTemplateManager, RandomSource random, Rotation rotation, BlockPos pos, DesertRuinStructure structure, StructurePieceAccessor structurePieceAccessor) {
        BlockPos blockPos = new BlockPos(pos.getX(), 90, pos.getZ());
        BlockPos blockPos2 = StructureTemplate.transform(new BlockPos(15, 0, 15), Mirror.NONE, rotation, BlockPos.ZERO).offset(blockPos);
        BoundingBox boundingBox = BoundingBox.fromCorners(blockPos, blockPos2);
        BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), blockPos.getY(), Math.min(blockPos.getZ(), blockPos2.getZ()));
        List<BlockPos> list = DesertRuinPieces.allPositions(random, blockPos3);
        int i = Mth.nextInt(random, 4, 8);
        for (int j = 0; j < i; ++j) {
            Rotation rotation2;
            BlockPos blockPos4;
            if (list.isEmpty() || (BoundingBox.fromCorners(blockPos4 = list.remove(random.nextInt(list.size())), StructureTemplate.transform(new BlockPos(5, 0, 6), Mirror.NONE, rotation2 = Rotation.getRandom(random), BlockPos.ZERO).offset(blockPos4))).intersects(boundingBox)) {
            	continue;
            }
            DesertRuinPieces.addPiece(structureTemplateManager, blockPos4, rotation2, structurePieceAccessor, random, structure, 0.8f);
        }
    }

    private static List<BlockPos> allPositions(RandomSource random, BlockPos pos) {
        ArrayList<BlockPos> list = Lists.newArrayList();
        list.add(pos.offset(-16 + Mth.nextInt(random, 1, 8), 0, 16 + Mth.nextInt(random, 1, 7)));
        list.add(pos.offset(-16 + Mth.nextInt(random, 1, 8), 0, Mth.nextInt(random, 1, 7)));
        list.add(pos.offset(-16 + Mth.nextInt(random, 1, 8), 0, -16 + Mth.nextInt(random, 4, 8)));
        list.add(pos.offset(Mth.nextInt(random, 1, 7), 0, 16 + Mth.nextInt(random, 1, 7)));
        list.add(pos.offset(Mth.nextInt(random, 1, 7), 0, -16 + Mth.nextInt(random, 4, 6)));
        list.add(pos.offset(16 + Mth.nextInt(random, 1, 7), 0, 16 + Mth.nextInt(random, 3, 8)));
        list.add(pos.offset(16 + Mth.nextInt(random, 1, 7), 0, Mth.nextInt(random, 1, 7)));
        list.add(pos.offset(16 + Mth.nextInt(random, 1, 7), 0, -16 + Mth.nextInt(random, 4, 8)));
        return list;
    }

    private static void addPiece(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor structurePieceAccessor, RandomSource random, DesertRuinStructure structure, float integrity) {
        Identifier Identifier = DesertRuinPieces.getRandomRuin(random);
        structurePieceAccessor.addPiece(new DesertRuinPiece(structureTemplateManager, Identifier, pos, rotation, integrity));
    }
	
    public static class DesertRuinPiece extends TemplateStructurePiece {

        private final float integrity;
        
        public DesertRuinPiece(StructureTemplateManager structureTemplateManager, Identifier location, BlockPos pos, Rotation rotation, float integrity) {
            super(ModStructurePieces.DESERT_RUIN, 0, structureTemplateManager, location, location.toString(), DesertRuinPiece.makeSettings(rotation, integrity), pos);
            this.integrity = integrity;
        }
        
        private DesertRuinPiece(StructureTemplateManager structureTemplateManager, CompoundTag genDepth, Rotation rotation, float integrity) {
            super(ModStructurePieces.DESERT_RUIN, genDepth, structureTemplateManager, Identifier -> DesertRuinPiece.makeSettings(rotation, integrity));
            this.integrity = integrity;
        }
    	
        private static StructurePlaceSettings makeSettings(Rotation rotation, float integrity) {
            StructureProcessor structureProcessor = DESERT_RUIN_PROCESSOR;
            return new StructurePlaceSettings().setRotation(rotation).setMirror(Mirror.NONE).addProcessor(new BlockRotProcessor(integrity)).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(structureProcessor);
        }

        public static DesertRuinPiece create(StructureTemplateManager structureTemplateManager, CompoundTag tag) {
            Rotation rotation = Rotation.valueOf(tag.getString("Rot").get());
            float f = tag.getFloatOr("Integrity", 1.0F);
            return new DesertRuinPiece(structureTemplateManager, tag, rotation, f);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
            super.addAdditionalSaveData(context, tag);
            tag.putString("Rot", this.placeSettings.getRotation().name());
            tag.putFloat("Integrity", this.integrity);
        }

		@Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            Husk husk;
            if ("chest".equals(name)) {
                level.setBlock(pos, (BlockState)Blocks.CHEST.defaultBlockState().setValue(ChestBlock.WATERLOGGED, level.getFluidState(pos).is(FluidTags.WATER)), 2);
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockEntity).setLootTable(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON, random.nextLong());
                }
            } else if ("husk".equals(name) && (husk = EntityTypes.HUSK.create(level.getLevel(), EntitySpawnReason.STRUCTURE)) != null) {
            	husk.setPersistenceRequired();
            	husk.snapTo(pos, 0.0f, 0.0f);
            	husk.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.STRUCTURE, null);
                level.addFreshEntityWithPassengers((Entity)husk);
                if (pos.getY() > level.getSeaLevel()) {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                } else {
                    level.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
                }
            }
        }

        @Override
        public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
            int i = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ());
            this.templatePosition = new BlockPos(this.templatePosition.getX(), i, this.templatePosition.getZ());
            BlockPos blockPos = StructureTemplate.transform(new BlockPos(this.template.getSize().getX() - 1, 0, this.template.getSize().getZ() - 1), Mirror.NONE, this.placeSettings.getRotation(), BlockPos.ZERO).offset(this.templatePosition);
            this.templatePosition = new BlockPos(this.templatePosition.getX(), this.getHeight(this.templatePosition, (BlockGetter)((Object)level), blockPos), this.templatePosition.getZ());
            super.postProcess(level, structureManager, generator, random, box, chunkPos, pos);
        }

        private int getHeight(BlockPos templatePos, BlockGetter level, BlockPos pos) {
            int y = templatePos.getY();
            int j = 512;
            int k = y - 1;
            int count = 0;
            for (BlockPos blockPos : BlockPos.betweenClosed(templatePos, pos)) {
                int genX = blockPos.getX();
                int genZ = blockPos.getZ();
                int genY = templatePos.getY() - 1;
                BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(genX, genY, genZ);
                BlockState blockState = level.getBlockState(mutableBlockPos);
                FluidState fluidState = level.getFluidState(mutableBlockPos);
                while ((blockState.isAir() || fluidState.is(FluidTags.WATER) || blockState.is(BlockTags.ICE) || blockState.is(BlockTags.REPLACEABLE)) && genY > level.getMinY() + 1) {
                    mutableBlockPos.set(genX, --genY, genZ);
                    blockState = level.getBlockState(mutableBlockPos);
                    fluidState = level.getFluidState(mutableBlockPos);
                }
                j = Math.min(j, genY);
                if (genY >= k - 2) continue;
                ++count;
            }
            int p = Math.abs(templatePos.getX() - pos.getX());
            if (k - j > 2 && count > p - 2) {
                y = j;
            }
            y--; // Push it down one block so the ruins generate flush to the sand.
            return y;
        }
		
    }
}
