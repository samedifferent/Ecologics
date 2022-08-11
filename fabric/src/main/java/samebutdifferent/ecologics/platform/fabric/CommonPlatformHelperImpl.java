package samebutdifferent.ecologics.platform.fabric;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.mixin.fabric.RecordItemAccessor;
import samebutdifferent.ecologics.mixin.fabric.PotionBrewingAccessor;
import samebutdifferent.ecologics.mixin.fabric.SpawnPlacementsAccessor;
import samebutdifferent.ecologics.mixin.fabric.WoodTypeAccessor;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.Map;
import java.util.function.Supplier;

public class CommonPlatformHelperImpl {
    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        T registry = Registry.register(Registry.BLOCK, new ResourceLocation(Ecologics.MOD_ID, name), block.get());
        return () -> registry;
    }

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registry = Registry.register(Registry.ITEM, new ResourceLocation(Ecologics.MOD_ID, name), item.get());
        return () -> registry;
    }

    public static <T extends Item, M extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(String name, Supplier<EntityType<M>> entityType, int backgroundColor, int highlightColor) {
        return registerItem(name, () -> new SpawnEggItem(entityType.get(), backgroundColor, highlightColor, new Item.Properties().tab(Ecologics.TAB)));
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        T registry = Registry.register(Registry.SOUND_EVENT, new ResourceLocation(Ecologics.MOD_ID, name), soundEvent.get());
        return () -> registry;
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange) {
        EntityType<T> registry = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Ecologics.MOD_ID, name), FabricEntityTypeBuilder.create(category, factory).dimensions(EntityDimensions.scalable(width, height)).trackRangeChunks(clientTrackingRange).build());
        return () -> registry;
    }

    public static CreativeModeTab registerCreativeModeTab(ResourceLocation name, Supplier<ItemStack> icon) {
        return FabricItemGroupBuilder.build(name, icon);
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        T registry = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Ecologics.MOD_ID, name), blockEntity.get());
        return () -> registry;
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(CommonPlatformHelper.BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return FabricBlockEntityTypeBuilder.create(blockEntitySupplier::create, validBlocks).build();
    }

    public static <T extends Potion> Supplier<T> registerPotion(String name, Supplier<T> potion) {
        T registry = Registry.register(Registry.POTION, new ResourceLocation(Ecologics.MOD_ID, name), potion.get());
        return () -> registry;
    }

    public static void registerBrewingRecipe(Potion input, Item ingredient, Potion output) {
        PotionBrewingAccessor.invokeAddMix(input, ingredient, output);
    }

    public static <T extends FoliagePlacer> Supplier<FoliagePlacerType<T>> registerFoliagePlacerType(String name, Supplier<FoliagePlacerType<T>> foliagePlacerType) {
        FoliagePlacerType<T> registry = Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(Ecologics.MOD_ID, name), foliagePlacerType.get());
        return () -> registry;
    }

    public static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> registerTrunkPlacerType(String name, Supplier<TrunkPlacerType<T>> trunkPlacerType) {
        TrunkPlacerType<T> registry = Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(Ecologics.MOD_ID, name), trunkPlacerType.get());
        return () -> registry;
    }

    public static <T extends Block> void setFlammable(Block fireBlock, Supplier<T> block, int encouragement, int flammability) {
        FlammableBlockRegistry.getInstance(fireBlock).add(block.get(), encouragement, flammability);
    }

    public static <T extends MobEffect> Supplier<T> registerMobEffect(String name, Supplier<T> mobEffect) {
        T registry = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(Ecologics.MOD_ID, name), mobEffect.get());
        return () -> registry;
    }

    public static <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        T registry = Registry.register(Registry.FEATURE, new ResourceLocation(Ecologics.MOD_ID, name), feature.get());
        return () -> registry;
    }

    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacementsAccessor.invokeRegister(entityType, decoratorType, heightMapType, decoratorPredicate);
    }

    public static WoodType createWoodType(String name) {
        return WoodTypeAccessor.invokeConstructor(name);
    }

    public static WoodType registerWoodType(WoodType woodType) {
        return WoodTypeAccessor.invokeRegister(woodType);
    }

    public static void registerCompostable(float chance, ItemLike item) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }

    public static void registerStrippables(Map<Block, Block> blockMap) {
        blockMap.forEach(StrippableBlockRegistry::register);
    }

    public static Supplier<RecordItem> registerRecordItem(String name, int comparatorValue, Supplier<SoundEvent> soundSupplier, Item.Properties properties) {
        return registerItem(name, () -> RecordItemAccessor.invokeConstructor(comparatorValue, soundSupplier.get(), properties, 108));
    }
}
