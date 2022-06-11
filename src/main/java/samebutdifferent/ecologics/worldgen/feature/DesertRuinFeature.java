package samebutdifferent.ecologics.worldgen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import samebutdifferent.ecologics.Ecologics;

import java.util.Optional;
import java.util.Random;

// Thanks to TelelpathicGrunt for his help on this
public class DesertRuinFeature extends Feature<NoneFeatureConfiguration> {
    private final BlockIgnoreProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlaceSettings placementsettings = new StructurePlaceSettings().setMirror(Mirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);
    private final ResourceLocation[] pieces = new ResourceLocation[]{
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/chest_house"),
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/pillars1"),
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/pillars2"),
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/wall1"),
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/wall2"),
            new ResourceLocation(Ecologics.MOD_ID, "desert_ruin/pit"),
    };

    public DesertRuinFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel level = pContext.level();
        BlockPos origin = pContext.origin();
        RandomSource random = pContext.random();

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(origin);
        for (mutable.move(Direction.UP); level.isEmptyBlock(mutable) && mutable.getY() > 2;) {
            mutable.move(Direction.DOWN);
        }

        if (!level.getBlockState(mutable).isAir() && !level.isEmptyBlock(mutable.below()) && !level.isEmptyBlock(mutable.below(2))) {
            mutable.move(Direction.DOWN);
        } else {
            return false;
        }

        BlockPos.MutableBlockPos blockpos$Mutable = new BlockPos.MutableBlockPos();
        StructureTemplateManager templatemanager = level.getLevel().getServer().getStructureManager();
        ResourceLocation nbtRL = pieces[random.nextInt(pieces.length)];
        Optional<StructureTemplate> template = templatemanager.get(nbtRL);

        if (template.isEmpty()) {
            Ecologics.LOGGER.warn(nbtRL + " NTB does not exist!");
            return false;
        }

        for (int x = 0; x < template.get().getSize().getX(); x++) {
            for (int z = 0; z < template.get().getSize().getZ(); z++) {
                blockpos$Mutable.set(origin.below()).move(x, 0, z);
                if (!level.getBlockState(blockpos$Mutable).is(BlockTags.SAND)) {
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
                    } else if (level.getBlockState(blockpos$Mutable.move(Direction.UP)).canOcclude() || !level.getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).canOcclude()) {
                        return false;
                    } else if (!level.getBlockState(blockpos$Mutable).is(BlockTags.SAND)) {
                        return false;
                    }
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementsettings.setRotation(Rotation.getRandom(random)).setRotationPivot(halfLengths).setIgnoreEntities(false);
        blockpos$Mutable.set(origin);
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, nbtRL.getPath().contains("pit") ? -2 : 0, -template.get().getSize().getZ() / 2);
        template.get().placeInWorld(level, blockpos$Mutable.offset(offset), blockpos$Mutable.offset(offset), placementsettings, random, Block.UPDATE_CLIENTS);

        return true;
    }
}
