package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import samebutdifferent.ecologics.Ecologics;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Ecologics.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags() {
/*        for (RegistryObject<Block> object : ModBlocks.BLOCKS.getEntries()) {
            Block block = object.get();
            if (block.getSoundType(block.defaultBlockState()) == SoundType.WOOD) {
                this.tag(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
        }
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.COCONUT_LOG.get())
                .add(ModBlocks.STRIPPED_COCONUT_LOG.get())
                .add(ModBlocks.COCONUT_WOOD.get())
                .add(ModBlocks.STRIPPED_COCONUT_WOOD.get())
                .add(ModBlocks.COCONUT_PLANKS.get())
                .add(ModBlocks.COCONUT_SLAB.get())
                .add(ModBlocks.COCONUT_STAIRS.get())
                .add(ModBlocks.COCONUT_FENCE.get())
                .add(ModBlocks.COCONUT_FENCE_GATE.get())
                .add(ModBlocks.COCONUT_DOOR.get())
                .add(ModBlocks.COCONUT_TRAPDOOR.get())
                .add(ModBlocks.COCONUT_BUTTON.get())
                .add(ModBlocks.COCONUT_PRESSURE_PLATE.get())
                .add(ModBlocks.COCONUT_HUSK.get())
                .add(ModBlocks.HANGING_COCONUT.get())
                .add(ModBlocks.COCONUT.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.COCONUT_PLANKS.get());
        this.tag(ModTags.Blocks.COCONUT_LOGS).add(ModBlocks.COCONUT_LOG.get()).add(ModBlocks.STRIPPED_COCONUT_LOG.get()).add(ModBlocks.COCONUT_WOOD.get()).add(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(ModTags.Blocks.COCONUT_LOGS);
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.COCONUT_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.COCONUT_SLAB.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.COCONUT_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.COCONUT_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.COCONUT_BUTTON.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.COCONUT_FENCE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.COCONUT_PRESSURE_PLATE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.COCONUT_FENCE_GATE.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.COCONUT_LEAVES.get());*/
/*        this.tag(Tags.Blocks.FENCES_WOODEN).add(ModBlocks.COCONUT_FENCE.get());
        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(ModBlocks.COCONUT_FENCE_GATE.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(ModBlocks.COCONUT_LEAVES.get());*/
/*        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SEASHELL_BLOCK.get())
                .add(ModBlocks.SEASHELL_TILES.get())
                .add(ModBlocks.SEASHELL_TILE_STAIRS.get())
                .add(ModBlocks.SEASHELL_TILE_SLAB.get())
                .add(ModBlocks.SEASHELL_TILE_WALL.get());
        this.tag(BlockTags.STAIRS).add(ModBlocks.SEASHELL_TILE_STAIRS.get());
        this.tag(BlockTags.SLABS).add(ModBlocks.SEASHELL_TILE_SLAB.get());
        this.tag(BlockTags.WALLS).add(ModBlocks.SEASHELL_TILE_WALL.get());*/
    }
}
