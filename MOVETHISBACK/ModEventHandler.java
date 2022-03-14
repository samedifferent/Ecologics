package samebutdifferent.ecologics.event;

import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.loot.AddItemModifier;

public class ModEventHandler {
    public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new AddItemModifier.Serializer().setRegistryName(Ecologics.MOD_ID, "add_item"));
    }
}
