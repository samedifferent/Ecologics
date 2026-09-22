package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.feature.treedecorators.SappyLogDecorator;

public class ModTreeDecoratorTypes<P extends TreeDecorator>
{
    public static void init() {
    	for (Pair<Identifier, TreeDecoratorType<?>> registry : TREE_DECORATORS) {
    		Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, registry.getA(), registry.getB());
    	}
    }

    public static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecoratorType(String name, TreeDecoratorType<P> decorator) {
    	TREE_DECORATORS.add(new Pair<>(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), decorator));
    	return decorator;
    }
    
    public static final ArrayList<Pair<Identifier, TreeDecoratorType<?>>> TREE_DECORATORS = new ArrayList<>();
    
    public static final TreeDecoratorType<SappyLogDecorator> SAPPY_LOG_DECORATOR = registerTreeDecoratorType("sappy_log_decorator", new TreeDecoratorType<>(SappyLogDecorator.CODEC));
}
