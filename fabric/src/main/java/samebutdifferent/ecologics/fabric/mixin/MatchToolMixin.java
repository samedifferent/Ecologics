package samebutdifferent.ecologics.fabric.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import samebutdifferent.ecologics.registry.ModItems;

@Mixin(MatchTool.class)
public class MatchToolMixin {

	@Shadow @Final
	private Optional<ItemPredicate> predicate;
	
	@Inject(at = @At("RETURN"), method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z", cancellable = true)
	private void injectTest(LootContext context, CallbackInfoReturnable<Boolean> cir) {
		ItemInstance item = context.getOptionalParameter(LootContextParams.TOOL);
		if (item != null && item.is(ModItems.CRAB_CLAW)) {
			cir.setReturnValue(this.predicate.get().test(Items.SHEARS.getDefaultInstance()));
		}
	}
	
}
