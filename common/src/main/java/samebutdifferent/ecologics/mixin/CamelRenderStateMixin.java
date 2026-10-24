package samebutdifferent.ecologics.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.renderer.entity.state.CamelRenderState;
import samebutdifferent.ecologics.entity.data.CamelChestDataHolder;

@Mixin(CamelRenderState.class)
public class CamelRenderStateMixin implements CamelChestDataHolder 
{
	@Unique
	public boolean hasChest;

	@Override
	public boolean hasChest() {
		return this.hasChest;
	}

	@Override
	public void setChest(boolean newValue) {
		this.hasChest = newValue;
	}
}
