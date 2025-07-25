package net.hellay.daggerlance.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.hellay.daggerlance.Daggerlance;

import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DaggerlanceParticles {

    public static SimpleParticleType DAGGERLANCE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROYALTY_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType MOON_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROSE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType ROSE_LEAF_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType JADE_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static SimpleParticleType VANA_SWEEP_PARTICLE_TYPE = FabricParticleTypes.simple();

    public static void registerModParticles() {
        Daggerlance.LOGGER.info("Registering particles for" + Daggerlance.MOD_NAME + "...");
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "daggerlance_sweep_particle") , DAGGERLANCE_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "royalty_sweep_particle") , ROYALTY_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "moon_sweep_particle") , MOON_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "rose_sweep_particle") , ROSE_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "rose_leaf_sweep_particle") , ROSE_LEAF_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "jade_sweep_particle") , JADE_SWEEP_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE , Identifier.of(Daggerlance.MOD_ID , "vana_sweep_particle") , VANA_SWEEP_PARTICLE_TYPE);
    }

}
