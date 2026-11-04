package samebutdifferent.ecologics.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import oshi.util.tuples.Pair;
import samebutdifferent.ecologics.Ecologics;

public class ModParticleTypes<P extends ParticleOptions>
{
    public static void init() {
        for (Pair<ResourceLocation, ParticleType<?>> registry : PARTICLES) {
            Registry.register(BuiltInRegistries.PARTICLE_TYPE, registry.getA(), registry.getB());
        }
    }
    
    public static <P extends ParticleOptions> ParticleType<P> registerParticleType(String name, ParticleType<P> particle) {
        PARTICLES.add(new Pair<>(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), particle));
        return particle;
    }
    
    public static SimpleParticleType registerParticleType(String name, boolean overrideLimiter) {
        SimpleParticleType particle = new SimpleParticleType(overrideLimiter);
        PARTICLES.add(new Pair<>(ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, name), particle));
        return particle;
    }
    
    public static final ArrayList<Pair<ResourceLocation, ParticleType<?>>> PARTICLES = new ArrayList<>();

    public static final SimpleParticleType DRIPPING_MAPLE_SAP = registerParticleType("dripping_maple_sap", false);
    public static final SimpleParticleType FALLING_MAPLE_SAP = registerParticleType("falling_maple_sap", false);

    public static final ResourceKey<ParticleType<?>> MAPLE_SAP = ResourceKey.create(Registries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, "maple_sap"));
}
