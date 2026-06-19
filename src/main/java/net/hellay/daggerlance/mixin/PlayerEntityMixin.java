package net.hellay.daggerlance.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

    @Unique
    private int daggerlance$parryTimer = 0;

    @Inject(method = "actuallyHurt",at =@At("HEAD"))
    private void daggerlance$triggerParry(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfo ci) {
        Player player = (Player) (Object)this;
        boolean a = daggerlance$itemCheckForParry(InteractionHand.MAIN_HAND,player);
        boolean b = daggerlance$itemCheckForParry(InteractionHand.OFF_HAND,player);
        if (a || b) {
            ItemStack stack = a ? player.getItemInHand(InteractionHand.MAIN_HAND) : b ? player.getItemInHand(InteractionHand.OFF_HAND) : ItemStack.EMPTY;
            if (!stack.isEmpty()) {
                daggerlance$parryTimer = 15;
                player.playSound(SoundEvents.TRIDENT_THUNDER.value(),1,1);
                player.getCooldowns().addCooldown(stack,20 * 8);
                player.stopUsingItem();
            }

        }
    }

    @Inject(method = "tick",at =@At("HEAD"))
    private void daggerlance$tickParry(CallbackInfo ci) {
        Player player = (Player) (Object)this;
        if (daggerlance$parryTimer > 0) {
            if (player.level() instanceof ServerLevel server) {
                Vec3 vec3 = player.getViewVector(1.0f);
                AABB baseBox = player.getBoundingBox().expandTowards(vec3.scale(2)).inflate(1.0, 1.0, 1.0);
                List<LivingEntity> entities = server.getEntitiesOfClass(LivingEntity.class,
                        baseBox,
                        entity -> entity != player && entity.isAlive() && !entity.isSpectator() && entity instanceof LivingEntity);

                for (LivingEntity entity : entities) {
                    entity.hurt(player.damageSources().source(DamageTypes.PLAYER_ATTACK,player), 1);
                    server.sendParticles(ParticleTypes.SWEEP_ATTACK,entity.getX(),entity.getY() + 0.5,entity.getZ(),0,0,0,1,1);
                    server.playSeededSound(null,player,SoundEvents.LUNGE_2,SoundSource.PLAYERS,1,1,server.getSeed());
                }
            }
            player.setDeltaMovement(0,0,0);
            daggerlance$parryTimer--;
            if (!player.swinging) {
                player.swing(InteractionHand.MAIN_HAND);
            }
        }
    }

    @Unique
    private static boolean daggerlance$itemCheckForParry(InteractionHand hand, Player player) {
        return player.getItemInHand(hand).getItem() instanceof DaggerlanceItem && player.isUsingItem() && player.getUsedItemHand() == hand;
    }

    @WrapOperation(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
    private int daggerlance$modifySweepParticles(ServerLevel instance, ParticleOptions particleOptions, double d, double e, double f, int i, double g, double h, double j, double k, Operation<Integer> original) {
        Player player = (Player) (Object) this;
        ItemStack weapon = player.getWeaponItem();
        if (player.getWeaponItem().getItem() instanceof DaggerlanceItem) {
            particleOptions = DaggerlanceItem.getSkin(weapon).getSweepParticle();
        }
        return original.call(instance,particleOptions,d,e,f,i,g,h,j,k);
    }
}
