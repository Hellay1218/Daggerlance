package net.hellay.daggerlance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.hellay.daggerlance.client.particle.DaggerlanceSweepParticle;
import net.hellay.daggerlance.init.DaggerlanceParticles;

@Environment(EnvType.CLIENT)
public class DaggerlanceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.DAGGERLANCE_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROYALTY_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.MOON_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROSE_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROSE_LEAF_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.JADE_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.VANA_SWEEP_PARTICLE_TYPE , DaggerlanceSweepParticle.Factory::new);
    }
}
