package samebutdifferent.ecologics.item;

import samebutdifferent.ecologics.entity.ModBoat;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ModBoatItem extends BoatItem {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::collides);
    private final String woodType;

    public ModBoatItem(String woodType, Settings pProperties) {
        super(null, pProperties);
        this.woodType = woodType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getStackInHand(pHand);
        HitResult hitresult = raycast(pLevel, pPlayer, RaycastContext.FluidHandling.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemstack);
        } else {
            Vec3d vec3 = pPlayer.getRotationVec(1.0F);
            double d0 = 5.0D;
            List<Entity> list = pLevel.getOtherEntities(pPlayer, pPlayer.getBoundingBox().stretch(vec3.multiply(5.0D)).expand(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3d vec31 = pPlayer.getEyePos();

                for(Entity entity : list) {
                    Box aabb = entity.getBoundingBox().expand((double)entity.getTargetingMargin());
                    if (aabb.contains(vec31)) {
                        return TypedActionResult.pass(itemstack);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                ModBoat boat = new ModBoat(pLevel, hitresult.getPos().x, hitresult.getPos().y, hitresult.getPos().z);
                boat.setWoodType(this.woodType);
                boat.setYaw(pPlayer.getYaw());
                if (!pLevel.isSpaceEmpty(boat, boat.getBoundingBox())) {
                    return TypedActionResult.fail(itemstack);
                } else {
                    if (!pLevel.isClient) {
                        pLevel.spawnEntity(boat);
                        pLevel.emitGameEvent(pPlayer, GameEvent.ENTITY_PLACE, new BlockPos(hitresult.getPos()));
                        if (!pPlayer.getAbilities().creativeMode) {
                            itemstack.decrement(1);
                        }
                    }

                    pPlayer.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemstack, pLevel.isClient());
                }
            } else {
                return TypedActionResult.pass(itemstack);
            }
        }
    }
}
