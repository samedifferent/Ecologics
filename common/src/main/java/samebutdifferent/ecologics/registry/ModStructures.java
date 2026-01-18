package samebutdifferent.ecologics.registry;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;
import samebutdifferent.ecologics.worldgen.structure.DesertRuinStructure;

public class ModStructures<S extends Structure>
{
    public static void init() {}
    
    public static final Supplier<StructureType> DESERT_RUIN = CommonPlatformHelper.registerStructure("desert_ruin", DesertRuinStructure.CODEC);
}
