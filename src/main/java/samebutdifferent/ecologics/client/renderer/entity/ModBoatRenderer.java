package samebutdifferent.ecologics.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.entity.ModBoat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<String, Pair<ResourceLocation, BoatModel>> boatResources;
    public static final List<String> woodTypes = List.of("coconut", "walnut", "azalea", "flowering_azalea");

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        this.shadowRadius = 0.8F;
        this.boatResources = woodTypes.stream().collect(ImmutableMap.toImmutableMap((type) -> type,
                (type) -> Pair.of(new ResourceLocation(getTextureLocation(type, hasChest)), this.createBoatModel(context, type, hasChest))));
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, String woodType, boolean hasChest) {
        ModelLayerLocation modelLayerLocation = hasChest ? createChestBoatModelName(woodType) : createBoatModelName(woodType);
        return new BoatModel(context.bakeLayer(modelLayerLocation), hasChest);
    }

    public static ModelLayerLocation createBoatModelName(String pType) {
        return createLocation("boat/" + pType, "main");
    }

    public static ModelLayerLocation createChestBoatModelName(String pType) {
        return createLocation("chest_boat/" + pType, "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(Ecologics.MOD_ID, pPath), pModel);
    }

    private static String getTextureLocation(String woodType, boolean hasChest) {
        return hasChest ? "textures/entity/chest_boat/" + woodType + ".png" : "textures/entity/boat/" + woodType + ".png";
    }
    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        return boatResources.get(((ModBoat)boat).getWoodType());
    }
}
