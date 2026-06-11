package net.hellay.daggerlance.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.hellay.daggerlance.Daggerlance;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class DaggerlanceParticles {

    public static SimpleParticleType DAGGERLANCE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROYALTY_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType MOON_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROSE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROSE_LEAF_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType JADE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType VANA_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("daggerlance_sweep_particle"), DAGGERLANCE_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("royalty_sweep_particle"), ROYALTY_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("moon_sweep_particle"), MOON_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("rose_sweep_particle"), ROSE_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("rose_leaf_sweep_particle"), ROSE_LEAF_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("jade_sweep_particle"), JADE_SWEEP_PARTICLE_TYPE);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Daggerlance.id("vana_sweep_particle"), VANA_SWEEP_PARTICLE_TYPE);
    }

}
