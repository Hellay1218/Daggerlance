package net.hellay.daggerlance.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

    //thank you medecoole <3

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
