package samebutdifferent.ecologics.neoforge.registry;

import net.minecraft.world.entity.animal.camel.Camel;
import samebutdifferent.ecologics.entity.data.CamelChestDataHelper;

public class CamelChestDataHelperNeoForge implements CamelChestDataHelper.IPlatformHandler
{
	@Override
	public boolean hasChest(Camel camel) {
		return camel.getData(ModDataAttachments.CAMEL_HAS_CHEST);
	}

	@Override
	public void setChest(Camel camel, boolean hasChest) {
		camel.setData(ModDataAttachments.CAMEL_HAS_CHEST, hasChest);
	}

}
