package net.hellay.daggerlance.mixin;

import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.init.DaggerlanceParticles;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    //thank you medecoole <3
    @ModifyArg(method = "spawnSweepAttackParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"), index = 0)
    private ParticleEffect injectSpawnSweepAttackParticles(ParticleEffect original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getMainHandStack().isOf(DaggerlanceItems.DAGGERLANCE)) {
            ParticleEffect particleEffect = DaggerlanceParticles.DAGGERLANCE_SWEEP_PARTICLE_TYPE;
            String skin = player.getMainHandStack().getOrDefault(DataComponentTypes.CUSTOM_MODEL_DATA , new CustomModelDataComponent(List.of(), List.of(), List.of(DaggerlanceItem.Skin.DEFAULT.getSkinName()), List.of())).getString(0);
            assert skin != null;
            if(skin.equals(DaggerlanceItem.Skin.GOLD.getSkinName())){
                particleEffect = DaggerlanceParticles.ROYALTY_SWEEP_PARTICLE_TYPE;
            } else if (skin.equals(DaggerlanceItem.Skin.MOON.getSkinName())){
                particleEffect = DaggerlanceParticles.MOON_SWEEP_PARTICLE_TYPE;
            }else if (skin.equals(DaggerlanceItem.Skin.ROSE.getSkinName())){
                particleEffect = player.getRandom().nextBoolean() ? DaggerlanceParticles.ROSE_SWEEP_PARTICLE_TYPE : DaggerlanceParticles.ROSE_LEAF_SWEEP_PARTICLE_TYPE;
            }else if (skin.equals(DaggerlanceItem.Skin.JADE.getSkinName())){
                particleEffect = DaggerlanceParticles.JADE_SWEEP_PARTICLE_TYPE;
            }else if (skin.equals(DaggerlanceItem.Skin.VANA.getSkinName())){
                particleEffect = DaggerlanceParticles.VANA_SWEEP_PARTICLE_TYPE;
            }

            if (particleEffect != null) {
                return particleEffect;
            }
        }
        return original;
    }
}
