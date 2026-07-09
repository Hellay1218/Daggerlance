package net.hellay.daggerlance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.hellay.daggerlance.client.particle.DaggerlanceSweepParticle;
import net.hellay.daggerlance.event.DaggerlanceToolTipEvent;
import net.hellay.daggerlance.init.DaggerlanceParticles;
import net.hellay.daggerlance.networking.ParryS2CPayload;

@Environment(EnvType.CLIENT)
public class DaggerlanceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.DAGGERLANCE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.ROYALTY_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.MOON_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.ROSE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.ROSE_LEAF_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.JADE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(DaggerlanceParticles.VANA_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        DaggerlanceToolTipEvent.init();

        ClientPlayNetworking.registerGlobalReceiver(ParryS2CPayload.TYPE,(payload,context) -> ParryS2CPayload.receive(payload));
    }
}
