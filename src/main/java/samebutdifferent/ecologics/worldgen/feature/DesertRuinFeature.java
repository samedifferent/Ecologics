package samebutdifferent.ecologics.worldgen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import samebutdifferent.ecologics.Ecologics;

import java.util.Optional;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

// Thanks to TelelpathicGrunt for his help on this
public class DesertRuinFeature extends Feature<DefaultFeatureConfig> {
    private final BlockIgnoreStructureProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlacementData placementsettings = new StructurePlacementData().setMirror(BlockMirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);
    private final Identifier[] pieces = new Identifier[]{
            new Identifier(Ecologics.MOD_ID, "desert_ruin/chest_house"),
            new Identifier(Ecologics.MOD_ID, "desert_ruin/pillars1"),
            new Identifier(Ecologics.MOD_ID, "desert_ruin/pillars2"),
            new Identifier(Ecologics.MOD_ID, "desert_ruin/wall1"),
            new Identifier(Ecologics.MOD_ID, "desert_ruin/wall2"),
            new Identifier(Ecologics.MOD_ID, "desert_ruin/pit"),
    };

    public DesertRuinFeature(Codec<DefaultFeatureConfig> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> pContext) {
        StructureWorldAccess level = pContext.getWorld();
        BlockPos origin = pContext.getOrigin();
        Random random = pContext.getRandom();

        BlockPos.Mutable mutable = new BlockPos.Mutable().set(origin);
        for (mutable.move(Direction.UP); level.isAir(mutable) && mutable.getY() > 2;) {
            mutable.move(Direction.DOWN);
        }

        if (!level.getBlockState(mutable).isAir() && !level.isAir(mutable.down()) && !level.isAir(mutable.down(2))) {
            mutable.move(Direction.DOWN);
        } else {
            return false;
        }

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
        StructureManager templatemanager = level.toServerWorld().getServer().getStructureManager();
        Identifier nbtRL = pieces[random.nextInt(pieces.length)];
        Optional<Structure> template = templatemanager.getStructure(nbtRL);

        if (template.isEmpty()) {
            Ecologics.LOGGER.warn(nbtRL + " NTB does not exist!");
            return false;
        }

        for (int x = 0; x < template.get().getSize().getX(); x++) {
            for (int z = 0; z < template.get().getSize().getZ(); z++) {
                blockpos$Mutable.set(origin.down()).move(x, 0, z);
                if (!level.getBlockState(blockpos$Mutable).isIn(BlockTags.SAND)) {
                    return false;
                }
            }
        }

        int radius = template.get().getSize().getX() / 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) < radius * radius + 1) {
                    blockpos$Mutable.set(origin).move(x, 0, z);
                    if (!level.getFluidState(blockpos$Mutable).isEmpty()) {
                        return false;
                    } else if (level.getBlockState(blockpos$Mutable.move(Direction.UP)).isOpaque() || !level.getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).isOpaque()) {
                        return false;
                    } else if (!level.getBlockState(blockpos$Mutable).isIn(BlockTags.SAND)) {
                        return false;
                    }
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementsettings.setRotation(BlockRotation.random(random)).setPosition(halfLengths).setIgnoreEntities(false);
        blockpos$Mutable.set(origin);
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, nbtRL.getPath().contains("pit") ? -2 : 0, -template.get().getSize().getZ() / 2);
        template.get().place(level, blockpos$Mutable.add(offset), blockpos$Mutable.add(offset), placementsettings, random, Block.NOTIFY_LISTENERS);

        return true;
    }
}
