package samebutdifferent.ecologics.fabric.registry;

import net.minecraft.world.entity.animal.camel.Camel;
import samebutdifferent.ecologics.entity.data.CamelChestDataHelper;

public class CamelChestDataHelperFabric implements CamelChestDataHelper.IPlatformHandler
{
	@Override
	public boolean hasChest(Camel camel) {
		return camel.getAttachedOrElse(ModDataAttachments.CAMEL_HAS_CHEST, false);
	}

	@Override
	public void setChest(Camel camel, boolean hasChest) {
		camel.setAttached(ModDataAttachments.CAMEL_HAS_CHEST, hasChest);
	}
}
