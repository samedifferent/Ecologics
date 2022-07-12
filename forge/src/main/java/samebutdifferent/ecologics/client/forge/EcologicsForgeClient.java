package samebutdifferent.ecologics.client.forge;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.client.EcologicsClient;
import samebutdifferent.ecologics.client.model.CamelModel;
import samebutdifferent.ecologics.client.model.CoconutCrabModel;
import samebutdifferent.ecologics.client.model.PenguinModel;
import samebutdifferent.ecologics.client.model.SquirrelModel;
import samebutdifferent.ecologics.entity.ModBoat;
import samebutdifferent.ecologics.registry.ModBlocks;

@Mod.EventBusSubscriber(modid = Ecologics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EcologicsForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EcologicsClient.init();
        event.enqueueWork(() -> {
            EcologicsClient.addWoodTypes();
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), ModBlocks.COCONUT_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, ModBlocks.COCONUT_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CamelModel.LAYER_LOCATION, CamelModel::createBodyLayer);
        event.registerLayerDefinition(CoconutCrabModel.LAYER_LOCATION, CoconutCrabModel::createBodyLayer);
        event.registerLayerDefinition(SquirrelModel.LAYER_LOCATION, SquirrelModel::createBodyLayer);
        event.registerLayerDefinition(PenguinModel.LAYER_LOCATION, PenguinModel::createBodyLayer);
        for (ModBoat.Type type : ModBoat.Type.values()) {
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, type.getModelLocation()), "main"), () -> BoatModel.createBodyModel(false));
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, type.getChestModelLocation()), "main"), () -> BoatModel.createBodyModel(true));
        }
    }
}
