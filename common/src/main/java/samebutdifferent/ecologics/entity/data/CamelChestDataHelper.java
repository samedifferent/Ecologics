package samebutdifferent.ecologics.entity.data;

import net.minecraft.world.entity.animal.camel.Camel;

public abstract class CamelChestDataHelper {
	
	private static IPlatformHandler handler;

    public static void registerHandler(IPlatformHandler platformHandler) {
        handler = platformHandler;
    }

    public static boolean hasChest(Camel camel) {
        return handler.hasChest(camel);
    }

    public static void setChest(Camel camel, boolean hasChest) {
        handler.setChest(camel, hasChest);
    }

    public interface IPlatformHandler {
        boolean hasChest(Camel camel);
        void setChest(Camel camel, boolean hasChest);
    }
}
