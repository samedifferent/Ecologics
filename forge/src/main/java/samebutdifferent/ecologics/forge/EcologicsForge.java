package samebutdifferent.ecologics.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.RegisterEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.PotBlock;
import samebutdifferent.ecologics.platform.forge.CommonPlatformHelperImpl;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.forge.ModConfigForge;
import samebutdifferent.ecologics.registry.forge.ModGlobalLootModifiers;

import java.util.HashMap;
import java.util.Map;

@Mod(Ecologics.MOD_ID)
@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID)
public class EcologicsForge {
	private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Ecologics.MOD_ID, "tab"));
	
    public EcologicsForge() {
        Ecologics.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigForge.COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CommonPlatformHelperImpl.BLOCKS.register(bus);
        CommonPlatformHelperImpl.ITEMS.register(bus);
        CommonPlatformHelperImpl.SOUND_EVENTS.register(bus);
        CommonPlatformHelperImpl.ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.BLOCK_ENTITY_TYPES.register(bus);
        CommonPlatformHelperImpl.FEATURES.register(bus);
        CommonPlatformHelperImpl.TRUNK_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.FOLIAGE_PLACER_TYPES.register(bus);
        CommonPlatformHelperImpl.MOB_EFFECTS.register(bus);
        CommonPlatformHelperImpl.POTIONS.register(bus);
        ModGlobalLootModifiers.GLM.register(bus);

        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCreativeTabs);
        bus.addListener(this::setup);
        bus.addListener(this::assignItemsToTab);

    }

    public void registerEntityAttributes(EntityAttributeCreationEvent event) {
        Map<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> attributes = new HashMap<>();
        Ecologics.registerEntityAttributes(attributes);
        attributes.forEach((entity, builder) -> event.put(entity, builder.build()));
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ecologics.commonSetup();
        });
    }

    private void registerCreativeTabs(RegisterEvent event) {
    	event.register(Registries.CREATIVE_MODE_TAB, helper -> {
    		helper.register(TAB, CreativeModeTab.builder().title(Component.translatable("itemGroup.ecologics.tab")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(() -> { return new ItemStack(ModBlocks.COCONUT_LOG.get()); }).build());
    	});
    }

    private void assignItemsToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TAB) {
	        event.accept(ModBlocks.COCONUT_LOG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_WOOD.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.STRIPPED_COCONUT_LOG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.STRIPPED_COCONUT_WOOD.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_PLANKS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_STAIRS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_SLAB.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_FENCE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_FENCE_GATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_DOOR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_TRAPDOOR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_BUTTON.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_PRESSURE_PLATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	
	        event.accept(ModBlocks.WALNUT_LOG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_WOOD.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.STRIPPED_WALNUT_LOG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.STRIPPED_WALNUT_WOOD.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_PLANKS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_STAIRS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_SLAB.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_FENCE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_FENCE_GATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_DOOR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_TRAPDOOR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_BUTTON.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_PRESSURE_PLATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	
	        event.accept(ModItems.COCONUT_SIGN.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.COCONUT_HANGING_SIGN.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.WALNUT_SIGN.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.WALNUT_HANGING_SIGN.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

	        event.accept(ModItems.WALNUT_BOAT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.WALNUT_CHEST_BOAT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

	        event.accept(ModBlocks.COCONUT_LEAVES.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_LEAVES.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.COCONUT_SEEDLING.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.WALNUT_SAPLING.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	
	        event.accept(ModBlocks.COCONUT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL_BLOCK.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL_TILES.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL_TILE_STAIRS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL_TILE_SLAB.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SEASHELL_TILE_WALL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.POT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.THIN_ICE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.ICE_BRICKS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.ICE_BRICK_STAIRS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.ICE_BRICK_SLAB.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.ICE_BRICK_WALL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SNOW_BRICKS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SNOW_BRICK_STAIRS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SNOW_BRICK_SLAB.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModBlocks.SNOW_BRICK_WALL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	
	        event.accept(ModBlocks.SURFACE_MOSS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.COCONUT_SLICE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.COCONUT_HUSK.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.CRAB_CLAW.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.CRAB_MEAT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.TROPICAL_STEW.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.COCONUT_CRAB_SPAWN_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.PENGUIN_SPAWN_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.SQUIRREL_SPAWN_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.SANDCASTLE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.MUSIC_DISC_COCONUT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.PRICKLY_PEAR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.COOKED_PRICKLY_PEAR.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.PENGUIN_FEATHER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	        event.accept(ModItems.WALNUT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

    }

    @SubscribeEvent
    public static void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getLevel();
        BlockState state = event.getState();
        if (state.is(Blocks.CACTUS)) {
            if (level.getBlockState(pos.above()).is(Blocks.CACTUS) && level.getBlockState(pos.below()).is(Blocks.CACTUS)) {
                if (level.isEmptyBlock(pos.above(2)) && level.getRandom().nextFloat() <= ModConfigForge.PRICKLY_PEAR_GROWTH_CHANCE.get()) {
                    level.setBlock(pos.above(2), ModBlocks.PRICKLY_PEAR.get().defaultBlockState(), 2);
                    level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        InteractionHand hand = event.getHand();
        if (state.is(ModBlocks.POT.get()) && player.isCrouching()) {
            if (player.getMainHandItem().getItem() instanceof PickaxeItem && hand.equals(InteractionHand.MAIN_HAND)){
                level.setBlockAndUpdate(pos, state.cycle(PotBlock.CHISEL));
                level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swing(InteractionHand.MAIN_HAND);
                player.getMainHandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }
            if (player.getOffhandItem().getItem() instanceof PickaxeItem && !(player.getMainHandItem().getItem() instanceof PickaxeItem) && hand.equals(InteractionHand.OFF_HAND)){
                level.setBlockAndUpdate(pos, state.cycle(PotBlock.CHISEL));
                level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                player.swing(InteractionHand.OFF_HAND);
                player.getOffhandItem().hurtAndBreak(1, player, (plr) -> plr.broadcastBreakEvent(InteractionHand.OFF_HAND));
            }
        }
    }

    @SubscribeEvent
    public static void onMissingBlockMappings(MissingMappingsEvent event) {
        for (var mapping : event.getAllMappings(ForgeRegistries.BLOCKS.getRegistryKey())) {
            if (mapping.getKey().equals(new ResourceLocation(Ecologics.MOD_ID, "coconut_husk"))) {
                ResourceLocation remapped = new ResourceLocation(Ecologics.MOD_ID, "coconut_seedling");
                if (ForgeRegistries.BLOCKS.containsKey(remapped)) {
                    mapping.remap(ForgeRegistries.BLOCKS.getValue(remapped));
                } else {
                    mapping.warn();
                }
            }
            if (mapping.getKey().equals(new ResourceLocation(Ecologics.MOD_ID, "potted_coconut_husk"))) {
                ResourceLocation remapped = new ResourceLocation(Ecologics.MOD_ID, "potted_coconut_seedling");
                if (ForgeRegistries.BLOCKS.containsKey(remapped)) {
                    mapping.remap(ForgeRegistries.BLOCKS.getValue(remapped));
                } else {
                    mapping.warn();
                }
            }
        }
    }
}