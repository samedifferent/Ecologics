package samebutdifferent.ecologics.neoforge.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Flammable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Transformable;
import samebutdifferent.ecologics.registry.ModBlocks;

public class ModDataMapProvider extends DataMapProvider 
{
    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void gather(Provider provider) {
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.AZALEA_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.AZALEA_WOOD.builtInRegistryHolder(), Transformable.stripping(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.FLOWERING_AZALEA_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.FLOWERING_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.FLOWERING_AZALEA_WOOD.builtInRegistryHolder(), Transformable.stripping(ModBlocks.FLOWERING_AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.COCONUT_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.COCONUT_WOOD.builtInRegistryHolder(), Transformable.stripping(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.MAPLE_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.SAPPY_MAPLE_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.SAPPY_MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.MAPLE_WOOD.builtInRegistryHolder(), Transformable.stripping(ModBlocks.MAPLE_WOOD, ModBlocks.STRIPPED_MAPLE_WOOD), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.WALNUT_LOG.builtInRegistryHolder(), Transformable.stripping(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG), false);
        this.builder(NeoForgeDataMaps.TRANSFORMABLES).replace(false).add(ModBlocks.WALNUT_WOOD.builtInRegistryHolder(), Transformable.stripping(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_AZALEA_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_AZALEA_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_PLANKS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_STAIRS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_SLAB.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_FENCE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_FENCE_GATE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.AZALEA_SHELF.builtInRegistryHolder(), new Flammable(30, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_PLANKS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_STAIRS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_SLAB.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_FENCE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.FLOWERING_AZALEA_FENCE_GATE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_COCONUT_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_COCONUT_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_PLANKS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_STAIRS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_SLAB.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_FENCE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_FENCE_GATE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_SHELF.builtInRegistryHolder(), new Flammable(30, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.COCONUT_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.SAPPY_MAPLE_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_MAPLE_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_MAPLE_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_PLANKS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_STAIRS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_SLAB.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_FENCE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_FENCE_GATE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.MAPLE_SHELF.builtInRegistryHolder(), new Flammable(30, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.RED_MAPLE_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.ORANGE_MAPLE_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.YELLOW_MAPLE_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.GREEN_MAPLE_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_WALNUT_LOG.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.STRIPPED_WALNUT_WOOD.builtInRegistryHolder(), new Flammable(5, 5), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_PLANKS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_STAIRS.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_SLAB.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_FENCE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_FENCE_GATE.builtInRegistryHolder(), new Flammable(5, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_SHELF.builtInRegistryHolder(), new Flammable(30, 20), false);
        this.builder(NeoForgeDataMaps.FLAMMABLE).replace(false).add(ModBlocks.WALNUT_LEAVES.builtInRegistryHolder(), new Flammable(30, 60), false);

    }
}