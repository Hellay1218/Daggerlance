package net.hellay.daggerlance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.impl.client.indigo.renderer.helper.ColorHelper;
import net.fabricmc.fabric.impl.screenhandler.client.ClientNetworking;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.client.hud.screenflash.ColourFlash;
import net.hellay.daggerlance.client.hud.screenflash.Flashes;
import net.hellay.daggerlance.client.hud.screenflash.ImageFlash;
import net.hellay.daggerlance.client.particle.DaggerlanceSweepParticle;
import net.hellay.daggerlance.event.DaggerlanceToolTipEvent;
import net.hellay.daggerlance.init.DaggerlanceParticles;
import net.hellay.daggerlance.networking.ParryS2CPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;

@Environment(EnvType.CLIENT)
public class DaggerlanceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.DAGGERLANCE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROYALTY_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.MOON_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROSE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.ROSE_LEAF_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.JADE_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DaggerlanceParticles.VANA_SWEEP_PARTICLE_TYPE, DaggerlanceSweepParticle.Factory::new);
        DaggerlanceToolTipEvent.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> Flashes.instance().tick());
        ClientPlayNetworking.registerGlobalReceiver(ParryS2CPayload.TYPE,(payload,context) -> ParryS2CPayload.receive(payload));
    }
}
