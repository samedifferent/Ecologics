package samebutdifferent.ecologics.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;

import java.util.Map;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoat.Type, Pair<ResourceLocation, BoatModel>> boatResources = Maps.newHashMap();

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        for(ModBoat.Type type : ModBoat.Type.values()) {
            boatResources.put(type, Pair.of(type.getTexture(hasChest), new BoatModel(context.bakeLayer(new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, hasChest ? type.getChestModelLocation() : type.getModelLocation()), "main")), hasChest)));
        }
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        return boatResources.get(((ModBoat)boat).getWoodType());
    }
}
