package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.worldgen.structure.DesertRuinStructure;

public class ModStructures<S extends Structure>
{
    public static void init() {}
    
    public static <S extends Structure> StructureType<S> registerStructure(String name, MapCodec<S> codec) {
    	return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, name), () -> codec);
    }
    
    public static final ArrayList<Pair<Identifier, StructureType<?>>> STRUCTURES = new ArrayList<>();
    
    public static final StructureType<DesertRuinStructure> DESERT_RUIN = registerStructure("desert_ruin", DesertRuinStructure.CODEC);
}
