package samebutdifferent.ecologics.platform.fabric;

import java.util.Map;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import samebutdifferent.ecologics.mixin.fabric.SpawnPlacementsAccessor;
import samebutdifferent.ecologics.mixin.fabric.WoodTypeAccessor;

public class CommonPlatformHelperImpl {
    /*public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
    	Ecologics.LOGGER.info("This shouldn't be called.");
        T registry = Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), block.get());
        return () -> registry;
    }

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registry = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), item.get());
        return () -> registry;
    }

    public static <T extends Item, M extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(String name, Supplier<EntityType<M>> entityType, int backgroundColor, int highlightColor) {
        return registerItem(name, () -> new SpawnEggItem(entityType.get(), backgroundColor, highlightColor, new Item.Properties()));
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        T registry = Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), soundEvent.get());
        return () -> registry;
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange) {
        EntityType<T> registry = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), FabricEntityTypeBuilder.create(category, factory).dimensions(EntityDimensions.scalable(width, height)).trackRangeChunks(clientTrackingRange).build());
        return () -> registry;
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        T registry = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), blockEntity.get());
        return () -> registry;
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(CommonPlatformHelper.BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return FabricBlockEntityTypeBuilder.create(blockEntitySupplier::create, validBlocks).build();
    }

    public static <T extends Potion> Supplier<T> registerPotion(String name, Supplier<T> potion) {
        T registry = Registry.register(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), potion.get());
        return () -> registry;
    }

    public static <T extends FoliagePlacer> Supplier<FoliagePlacerType<T>> registerFoliagePlacerType(String name, Supplier<FoliagePlacerType<T>> foliagePlacerType) {
        FoliagePlacerType<T> registry = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), foliagePlacerType.get());
        return () -> registry;
    }

    public static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> registerTrunkPlacerType(String name, Supplier<TrunkPlacerType<T>> trunkPlacerType) {
        TrunkPlacerType<T> registry = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), trunkPlacerType.get());
        return () -> registry;
    }*/

    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
        FlammableBlockRegistry.getInstance(fireBlock).add(block, encouragement, flammability);
    }

    /*public static <T extends MobEffect> Supplier<T> registerMobEffect(String name, Supplier<T> mobEffect) {
        T registry = Registry.register(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), mobEffect.get());
        return () -> registry;
    }

    public static <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        T registry = Registry.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), feature.get());
        return () -> registry;
    }*/
    
    public static void registerBrewingRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
    	FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(input, Ingredient.of(ingredient), output));
    }
    
    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacementType decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacementsAccessor.invokeRegister(entityType, decoratorType, heightMapType, decoratorPredicate);
    }

    public static WoodType createWoodType(String name, BlockSetType setType) {
        return new WoodType(name, setType);
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

    //TODO: Remove this.
    /*public static Supplier<JukeboxPlayable> registerRecordItem(String name, int comparatorValue, Supplier<SoundEvent> soundSupplier, Item.Properties properties) {
        return registerItem(name, () -> RecordItemAccessor.invokeConstructor(comparatorValue, soundSupplier.get(), properties, 108));
    }*/
}
