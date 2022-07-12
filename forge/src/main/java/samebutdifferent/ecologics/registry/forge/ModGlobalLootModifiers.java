package samebutdifferent.ecologics.registry.forge;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.loot.forge.AddItemModifier;

public class ModGlobalLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Ecologics.MOD_ID);

    public static final RegistryObject<Codec<AddItemModifier>> ADD_ITEM = GLM.register("add_item", AddItemModifier.CODEC);
}
