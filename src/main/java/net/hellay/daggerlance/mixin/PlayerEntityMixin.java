package net.hellay.daggerlance.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hellay.daggerlance.config.DaggerlanceConfigMenu;
import net.hellay.daggerlance.init.DaggerlanceDamageTypes;
import net.hellay.daggerlance.init.DaggerlanceTags;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.hellay.daggerlance.networking.ParryS2CPayload;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

    @Unique
    private int daggerlance$parryTimer = 0;

    @WrapOperation(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setHealth(F)V"))
    private void daggerlance$modifyDamageCal(Player player, float v, Operation<Void> original,final ServerLevel level, final DamageSource source, float dmg) {
        if (daggerlance$itemCheckForParry(InteractionHand.MAIN_HAND, player, source) || daggerlance$itemCheckForParry(InteractionHand.OFF_HAND, player, source)) {
            float damageModifer = (float) DaggerlanceConfigMenu.parry_damage_modifer;
            original.call(player, player.getHealth() - dmg * damageModifer);
        }
    }

    @Inject(method = "actuallyHurt",at =@At("TAIL"))
    private void daggerlance$triggerParry(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfo ci) {
        Player player = (Player) (Object)this;
        boolean a = daggerlance$itemCheckForParry(InteractionHand.MAIN_HAND,player,damageSource);
        boolean b = daggerlance$itemCheckForParry(InteractionHand.OFF_HAND,player,damageSource);

        if (a || b) {
            ItemStack stack = a ? player.getItemInHand(InteractionHand.MAIN_HAND) : b ? player.getItemInHand(InteractionHand.OFF_HAND) : ItemStack.EMPTY;
            if (!stack.isEmpty()) {
                daggerlance$parryTimer = DaggerlanceConfigMenu.parry_timer_length;
                serverLevel.playSeededSound(null,player,SoundEvents.TRIDENT_THUNDER,SoundSource.PLAYERS,4,1,serverLevel.getSeed());
                player.getCooldowns().addCooldown(stack,20 * 12);
                player.stopUsingItem();
                if (player instanceof ServerPlayer serverPlayer) {
                    ServerPlayNetworking.send(serverPlayer,new ParryS2CPayload());
                }
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
                    entity.hurt(player.damageSources().source(DaggerlanceDamageTypes.PARRY,player), (float) DaggerlanceConfigMenu.parry_damage_per_tick);
                    server.sendParticles(ParticleTypes.SWEEP_ATTACK,entity.getX(),entity.getY() + 0.5,entity.getZ(),0,0,0,1,1);
                    server.playSeededSound(null,player,SoundEvents.LUNGE_2,SoundSource.PLAYERS,1,1,server.getSeed());
                }
            }
            player.setDeltaMovement(0,0,0);
            daggerlance$parryTimer--;
            player.swing(InteractionHand.MAIN_HAND);
        }
    }

    @Unique
    private static boolean daggerlance$itemCheckForParry(InteractionHand hand, Player player, DamageSource damageSource) {
        ItemStack stack = player.getItemInHand(hand);
        Entity entity = damageSource.getEntity();
        TagKey<DamageType> tag = DaggerlanceConfigMenu.parryable == DaggerlanceConfigMenu.ParryableSteps.WHITELIST ? DaggerlanceTags.PARRYABLE : DaggerlanceConfigMenu.parryable == DaggerlanceConfigMenu.ParryableSteps.BLACKLIST ? DaggerlanceTags.UNPARRYABLE : null;
        boolean canParry = DaggerlanceConfigMenu.parryable == DaggerlanceConfigMenu.ParryableSteps.ALL || (tag != null && damageSource.is(tag));

        if (entity == null) {return false;}
        Vec3 playerViewVector = player.getViewVector(1.0f).normalize();
        Vec3 toEntity = player.position().vectorTo(entity.position());
        double dot = playerViewVector.dot(toEntity);
        boolean inRange = dot > 0.0;

        return canParry && inRange && (stack.getItem() instanceof DaggerlanceItem && player.isUsingItem() && player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() >= stack.getItem().getUseDuration(stack,player) / 4);
    }
}
