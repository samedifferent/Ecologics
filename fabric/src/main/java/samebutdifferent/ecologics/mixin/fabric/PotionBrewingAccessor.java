package samebutdifferent.ecologics.mixin.fabric;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;

@Mixin(PotionBrewing.Builder.class)
public interface PotionBrewingAccessor 
{
	//TODO: Update or remove this.
    /*@Invoker("addMix")
    static void invokeAddMix(Holder<Potion> potionEntry, Item potionIngredient, Holder<Potion> potionResult) {
        throw new AssertionError();
    }*/
}
