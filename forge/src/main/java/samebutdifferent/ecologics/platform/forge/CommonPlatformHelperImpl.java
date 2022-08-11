package samebutdifferent.ecologics.platform.forge;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.mixin.forge.AxeItemAccessor;
import samebutdifferent.ecologics.mixin.forge.FireBlockAccessor;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.util.forge.ModBrewingRecipe;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonPlatformHelperImpl {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ecologics.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ecologics.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Ecologics.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ecologics.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ecologics.MOD_ID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Ecologics.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, Ecologics.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Ecologics.MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Ecologics.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Ecologics.MOD_ID);

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static <T extends Item, M extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(String name, Supplier<EntityType<M>> entityType, int backgroundColor, int highlightColor) {
        return ITEMS.register(name, () -> new ForgeSpawnEggItem(entityType, backgroundColor, highlightColor, new Item.Properties().tab(Ecologics.TAB)));
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        return SOUND_EVENTS.register(name, soundEvent);
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(name));
    }

    public static CreativeModeTab registerCreativeModeTab(ResourceLocation name, Supplier<ItemStack> icon) {
        return new CreativeModeTab(name.toLanguageKey()) {
            @Override
            public ItemStack makeIcon() {
                return icon.get();
            }
        };
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        return BLOCK_ENTITY_TYPES.register(name, blockEntity);
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(CommonPlatformHelper.BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return BlockEntityType.Builder.of(blockEntitySupplier::create, validBlocks).build(null);
    }

    public static <T extends Potion> Supplier<T> registerPotion(String name, Supplier<T> potion) {
        return POTIONS.register(name, potion);
    }

    public static void registerBrewingRecipe(Potion input, Item ingredient, Potion output) {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(input, ingredient, output));
    }

    public static <T extends FoliagePlacer> Supplier<FoliagePlacerType<T>> registerFoliagePlacerType(String name, Supplier<FoliagePlacerType<T>> foliagePlacerType) {
        return FOLIAGE_PLACER_TYPES.register(name, foliagePlacerType);
    }

    public static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> registerTrunkPlacerType(String name, Supplier<TrunkPlacerType<T>> trunkPlacerType) {
        return TRUNK_PLACER_TYPES.register(name, trunkPlacerType);
    }

    public static <T extends Block> void setFlammable(Block fireBlock, Supplier<T> block, int encouragement, int flammability) {
        ((FireBlockAccessor)fireBlock).invokeSetFlammable(block.get(), encouragement, flammability);
    }

    public static <T extends MobEffect> Supplier<T> registerMobEffect(String name, Supplier<T> mobEffect) {
        return MOB_EFFECTS.register(name, mobEffect);
    }

    public static <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        return FEATURES.register(name, feature);
    }

    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacements.register(entityType, decoratorType, heightMapType, decoratorPredicate);
    }

    public static WoodType createWoodType(String name) {
        return WoodType.create(new ResourceLocation(Ecologics.MOD_ID, name).toString());
    }

    public static WoodType registerWoodType(WoodType woodType) {
        return WoodType.register(woodType);
    }

    public static void registerCompostable(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }

    public static void registerStrippables(Map<Block, Block> blockMap) {
        Map<Block, Block> strippables = new ImmutableMap.Builder<Block, Block>().putAll(AxeItemAccessor.getStrippables()).putAll(blockMap).build();
        AxeItemAccessor.setStrippables(strippables);
    }

    public static Supplier<RecordItem> registerRecordItem(String name, int comparatorValue, Supplier<SoundEvent> soundSupplier, Item.Properties properties) {
        return ITEMS.register(name, () -> new RecordItem(comparatorValue, soundSupplier, properties, 2160));
    }
}
