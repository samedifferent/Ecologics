package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.treedecorators.CoconutDecorator;

public class ModTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Ecologics.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<CoconutDecorator>> COCONUT_DECORATOR = TREE_DECORATOR_TYPES.register("coconut_decorator", () -> new TreeDecoratorType<>(CoconutDecorator.CODEC));
}
