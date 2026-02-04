package samebutdifferent.ecologics.platform.fabric;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import samebutdifferent.ecologics.mixin.fabric.SpawnPlacementsAccessor;

public class CommonPlatformHelperImpl {
    
    public static <T extends Mob> void registerSpawnPlacement(EntityType<T> entityType, SpawnPlacementType decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacementsAccessor.invokeRegister(entityType, decoratorType, heightMapType, decoratorPredicate);
    }

}
