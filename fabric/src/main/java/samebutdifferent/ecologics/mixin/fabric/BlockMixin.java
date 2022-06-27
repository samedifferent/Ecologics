package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(Block.class)
public class BlockMixin {
    @Redirect(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootContext$Builder;withParameter(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;Ljava/lang/Object;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;"))
    private static <T> LootContext.Builder onGetDrops(LootContext.Builder instance, LootContextParam<T> parameter, T value) {
        if (value instanceof ItemStack stack && stack.is(ModItems.CRAB_CLAW.get())) {
            return instance.withParameter(parameter, (T)new ItemStack(Items.SHEARS));
        }
        return instance.withParameter(parameter, value);
    }
}
