package samebutdifferent.ecologics.registry.forge;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.loot.forge.AddItemModifier;

public class ModGlobalLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Ecologics.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<AddItemModifier>> ADD_ITEM = GLM.register("add_item", AddItemModifier.Serializer::new);
}
